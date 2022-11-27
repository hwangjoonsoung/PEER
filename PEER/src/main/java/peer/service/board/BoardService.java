package peer.service.board;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import peer.model.board.BoardBean;
import peer.model.board.PageBean;

public interface BoardService {
	
	public List<BoardBean> listAll() throws Exception;
	public List<BoardBean> listAll(PageBean pageBean) throws Exception;
	public int count() throws Exception;
	public int write(BoardBean boardBean, MultipartFile file) throws Exception;
	public BoardBean view(int board_num) throws Exception; 
	public int modify(BoardBean boardBean, int board_num) throws Exception;
	public int delete(int board_num) throws Exception;
}
