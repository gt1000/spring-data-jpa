package gt1000.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    @Query("SELECT b, m, count(r) " +
            " FROM Board b left JOIN b.member m " +
            " LEFT OUTER join BoardReply r on r.board = b" +
            " WHERE b.id = :boardId")
    Object getBoardById(@Param("boardId") Long boardId);

    @Query("select b, m from Board b left join b.member m where b.id = :boardId")
    Object getBoardWithMember(@Param("boardId") Long boardId);

    @Query("select b, r from Board b left join BoardReply r ON r.board = b where b.id = :boardId")
    List<Object[]> getBoardWithReply(@Param("boardId") Long boardId);

    @Query(value = "SELECT b, m, count(r) " +
            " FROM Board b " +
            " LEFT JOIN b.member m " +
            " LEFT JOIN BoardReply r ON r.board = b " +
            " GROUP BY b",
            countQuery = "SELECT count(b) FROM Board  b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
}
