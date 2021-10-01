package gt1000.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

    List<BoardReply> getBoardRepliesByBoardOrderById(Board board);

    @Modifying
    @Query("delete from BoardReply r where r.board.id = :boardId")
    void deleteByBoardId(Long id);
}
