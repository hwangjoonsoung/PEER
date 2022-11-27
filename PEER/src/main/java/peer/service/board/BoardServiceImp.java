package peer.service.board;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import peer.dao.board.BoardMapper;
import peer.model.board.BoardBean;
import peer.model.board.PageBean;

@Service
public class BoardServiceImp implements BoardService{
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardBean> listAll() throws Exception {
		return boardMapper.listAll();
	}

	@Override
	public List<BoardBean> listAll(PageBean pageBean) throws Exception {
		return boardMapper.listAll(pageBean);
	}

	@Override
	public int count() throws Exception {
		return boardMapper.count();
	}
	
	@Override
	public int write(BoardBean boardBean, MultipartFile file) throws Exception {
		
		System.out.println("file - " + file);
		
		if(!file.getOriginalFilename().equals("")) {
			
		String path = System.getProperty("user.dir") + "/src/main/resources/static/boardfiles";
		UUID uuid = UUID.randomUUID();
		String fileName = uuid + "_" + file.getOriginalFilename();
		System.out.println("fileName - " + fileName);
		
		File saveFile = new File(path, fileName);
		file.transferTo(saveFile);
		boardBean.setBoard_photo("/boardfiles/" + fileName);
		System.out.println(boardBean.getBoard_photo());
		
		}else {
			boardBean.setBoard_photo("");
		}
		
		return boardMapper.write(boardBean);
	}
	
	@Override
	public BoardBean view(int board_num) throws Exception {
			   boardMapper.readup(board_num);
		return boardMapper.view(board_num);
	}
	
	@Override
	public int modify(BoardBean boardBean, int board_num) throws Exception {
		return boardMapper.modify(boardBean, board_num);
	}
	
	@Override
	public int delete(int board_num) throws Exception {
		return 0;
	}
}
