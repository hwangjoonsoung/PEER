package peer.controller.message;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import peer.model.member.MemberBean;
import peer.model.message.MessageBean;
import peer.model.message.Pagination;
import peer.service.message.MessageService;

@Controller
public class MessageController {
	@Autowired(required=false)
	private MessageService ms;
	
	// 쪽지 메인 페이지
	@RequestMapping("/message.Intercept")
	public String message(@RequestParam(value="page", required = false, defaultValue="1") int page, Model model, HttpSession session) throws Exception {
		// 세션 불러오기
		MemberBean user_info = new MemberBean();
		user_info = (MemberBean)session.getAttribute("MemberBean");
		
		// 세션에서 user_num 추출
		int user_num = user_info.getUser_num();

		// 리스트에 쪽지 전체 담기
		// 전체 쪽지수
		int msgTotal = ms.msgTotal(user_num);
			
		Pagination pg = new Pagination(page, msgTotal);
		int sp = pg.getStartPage();
		int ep = pg.getEndPage();
		
		List<MessageBean> msglist = ms.msgList(user_num, sp, ep);
		
		model.addAttribute("msglist", msglist);
		model.addAttribute("pg", pg);
	
		return "message/messagehome";
	}
	
	// 쪽지 상세보기
	@RequestMapping("/msgopen.Intercept")
	public String msgOpen(int page, int message_num, Model model) throws Exception {
		MessageBean msg = new MessageBean();
		msg = ms.msgOpen(message_num);
		String temp = msg.getMessage_content();
		temp.replace("\n", "<br>");
		msg.setMessage_content(temp);
		
		model.addAttribute("page", page);
		model.addAttribute("msg", msg);
		
		return "message/messageview";
	}
	
	// 쪽지 보내기 폼
	@RequestMapping("/msgwrite.Intercept")
	public String msgWrite(int message_sender_num, Model model, HttpSession session) throws Exception {
		
		// 탈퇴한 회원인지 체크
		int usercheck = ms.userCheck(message_sender_num);
		if (usercheck == 1) {	// 1 : 탈퇴안함
			// 보낸 사람 닉네임 가져오기
			String message_sender_nick= ms.nickFromNum(message_sender_num);
			
			model.addAttribute("message_sender_nick", message_sender_nick);
			model.addAttribute("message_sender_num", message_sender_num);
			
			return "message/messagewrite";
		}else {					// 2 : 탈퇴함
			return "message/messagewrite";
		}
		
	}
	
	
	
	// 쪽지 보내기 & 답장하기
	@RequestMapping("/msgsend.Intercept")
	public String msgSend(int message_receiver_num, String message_content, HttpSession session) throws Exception {
		
		// 세션 불러오기
		MemberBean user_info = new MemberBean();
		user_info = (MemberBean)session.getAttribute("MemberBean");
		
		// MessageBean 생성
		MessageBean msg = new MessageBean();
		
		// 세션에서 user_num, user_nickname 추출해서 MessageBean에 담기
		msg.setMessage_sender_num(user_info.getUser_num());
		msg.setMessage_sender_nick(user_info.getUser_nickname());
		
		msg.setMessage_receiver_num(message_receiver_num);
		// receiver_num에서 nickname 추출하기
		String message_receiver_nick = ms.nickFromNum(message_receiver_num);
		msg.setMessage_receiver_nick(message_receiver_nick);
		msg.setMessage_content(message_content);
		
		// 쪽지 전송
		ms.msgSend(msg);
		
		return "message/msgsuccess";
	}
	
	
	
	// 쪽지 삭제
	@RequestMapping("/msgdel")
	public String msgDel(String checklist, int page, HttpSession session) throws Exception {

		// '/'로 묶어둔 message_num 분리
		String[] msg_num = checklist.split("/");
		
		// 삭제 처리
		if (msg_num.length != 0) {
			for(int i=0;i<msg_num.length;i++) {
				ms.msgDel(Integer.parseInt(msg_num[i]));
			}
		}
		
		// 세션 불러오기
		MemberBean user_info = new MemberBean();
		user_info = (MemberBean)session.getAttribute("MemberBean");
		
		// 세션에서 user_num 추출
		int user_num = user_info.getUser_num();
		
		// 리스트에 쪽지 전체 담기
		
		// 전체 쪽지수
		int msgTotal = ms.msgTotal(user_num);
		
		Pagination pg = new Pagination(msgTotal);
		
		// 삭제한 후 전체 페이지수가 바뀌었다면 돌아갈 페이지 변경
		if (page > pg.getPageTotal()) page -= 1;
		
		
		return "redirect:/message.Intercept?page="+page;
	}
}
