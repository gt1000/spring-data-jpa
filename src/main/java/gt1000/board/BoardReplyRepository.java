package gt1000.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

    @Modifying
    @Query("delete from BoardReply r where r.board.id = :boardId")
    void deleteByBoardId(Long id);
}
