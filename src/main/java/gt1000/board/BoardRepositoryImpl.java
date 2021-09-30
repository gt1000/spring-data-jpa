package gt1000.board;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import gt1000.member.QMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    public Board search() {
        log.info("search test ------------------------------- ");

        QBoard board = QBoard.board;
        QBoardReply reply = QBoardReply.boardReply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
        jpqlQuery.leftJoin(member).on(board.member.eq(member));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());
        tuple.groupBy(board);

        log.info("--- jpqlquery = {}", jpqlQuery);

        List<Tuple> result = tuple.fetch();

        return null;
    }

    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("--------------- searchPage -------------------");

        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QBoardReply boardReply = QBoardReply.boardReply;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.member.eq(member));
        jpqlQuery.leftJoin(boardReply).on(boardReply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, boardReply.count());

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = board.id.gt(100L);

        builder.and(expression);

        if(type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            for(String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            builder.and(conditionBuilder);
        }

        tuple.where(builder);

        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC: Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderbyExpress = new PathBuilder(Board.class, "board");
            tuple.orderBy(new OrderSpecifier(direction, orderbyExpress.get(prop)));
        });

        tuple.groupBy(board);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        log.info("------- result = {}", result);

        long count = tuple.fetchCount();
        log.info("count = {}", count);

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}
