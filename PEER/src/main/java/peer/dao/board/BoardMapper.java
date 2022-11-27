package peer.dao.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import peer.model.board.BoardBean;
import peer.model.board.PageBean;


@Mapper
public interface BoardMapper {
	
	/* 게시글 목록 불러오기 */
	public List<BoardBean> listAll() throws Exception;
	public List<BoardBean> listAll(PageBean pageBean) throws Exception;
	
	/* 게시글 총 데이터 개수 */
	public int count() throws Exception;

	/* 게시글 작성 */
	public int write(BoardBean boardBean) throws Exception;
	
	/* 게시글 상세보기 */
	public BoardBean view(int board_num) throws Exception; 
	public int readup(int board_num) throws Exception;
	
	/* 게시글 수정 */
	public int modify(@Param("boardBean") BoardBean boardBean,
					  @Param("board_num") int board_num) throws Exception;
	
	/* 게시글 삭제 */
	public int delete(int board_num) throws Exception;
	

}
