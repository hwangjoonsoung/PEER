package peer.controller.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import peer.model.board.BoardBean;
import peer.model.board.PageBean;
import peer.model.member.MemberBean;
import peer.service.board.BoardServiceImp;

@Controller
public class BoardController {
	
	@Autowired
	private BoardServiceImp boardService;
	
	/* 게시글 목록 출력 */
	@RequestMapping(value = "BoardList.do")
	public String boardList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model,
			Map<String, Object> map) throws Exception{
		
		int count = boardService.count();
		PageBean pageBean = new PageBean(page);
		System.out.println("count - " + count);
		System.out.println("PageBean - " + pageBean);
		
		pageBean.setRecordCount(count);
		List<BoardBean> lists = boardService.listAll(pageBean);
		System.out.println("lists - " + lists);
		
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("count", count);
		model.addAttribute("lists", lists);
		
		return "board/board";
	}
	
	
	/* 글 작성 페이지 */
	@RequestMapping(value = "BoardWrite.Intercept")
	public String boardWrite(HttpSession session,
							 Model model) {
		MemberBean memberBean = (MemberBean)session.getAttribute("MemberBean");
		int user_num = memberBean.getUser_num();
		String user_nickname = memberBean.getUser_nickname();
		
		model.addAttribute("user_num", user_num);
		model.addAttribute("user_nickname", user_nickname);
		
		return "board/write";
	}
	
	
	/* 글 작성 메소드 */
	@RequestMapping(value = "Write.Intercept")
	public String write(BoardBean boardBean,
						MultipartFile file) throws Exception{
		
		System.out.println("boardBean - " + boardBean);
		boardService.write(boardBean, file);
		
		return "redirect:BoardList.do";
	}
	
	
	/* 글 상세보기 페이지 */
	@RequestMapping(value = "BoardView.do")
	public String boardView(int page,
							int board_num,
							Model model) throws Exception{
		
		BoardBean boardBean = boardService.view(board_num);
		model.addAttribute("board", boardBean);
		
		return "board/view";
	}
	
	/* 글 수정 페이지*/
	@RequestMapping(value = "BoardModify.Intercept")
	public String boardModify() {
		return "";
	}
	
	/* 글 삭제 */
	@RequestMapping(value = "BoardDelete.Intercept")
	public String boardDelete() {
		return "";
	}
}
