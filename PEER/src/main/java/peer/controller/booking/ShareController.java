package peer.controller.booking;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import peer.model.booking.TotalBean;
import peer.model.member.MemberBean;
import peer.service.booking.ShareServiceImp;

@Controller
public class ShareController {
	
	@Autowired
	private ShareServiceImp shareService;
	
	/* 대기열 리스트 */
	@RequestMapping(value = "Share.Intercept", method = RequestMethod.POST)
	public String getQueList(int id,
							 int book_num,
							 Model model) throws Exception{
		/* 진입확인 */
		System.out.println("Controller - GetQueList.Intercept");
		
		/* Session ID + View Data 확인 */
		System.out.println("Controller USER_NUM DATA - " + id);
		System.out.println("Controller BOOK_NUM DATA - " + book_num);          
		
		/* 대기열 인원 정보출력 */
		List<TotalBean> QueMember = shareService.getQueList(id, book_num);
		System.out.println("Controller QUEMEMBER DATA - " + QueMember);
		
		/* 쉐어 신청자 확인 */
		String proposer = shareService.proposer(book_num);
		System.out.println("Controller PROPOSER DATA - " + proposer);
		
		/* 쉐어 신청 확인 */
		TotalBean propose = shareService.propose(id, book_num);

		int propose_shareCheck = 0;
		if(propose != null) {
			propose_shareCheck = propose.getShare_check();
		}

		System.out.println("Controller PROPOSE DATA - " + propose);
		
		/* 쉐어 매칭 확인*/
		int matching = shareService.matching(book_num);
		System.out.println("Controller MATCHING DATA - " + matching);
		
		
		/* 신청이력 확인 */
		if(propose != null && matching == 1 && propose_shareCheck == 1) {
			model.addAttribute("propose", propose);
			return "share/match_proposer";
		}
		
		/* 쉐어를 진행하지 않은 상태 */
		if(matching == 1 && proposer == null) {
			
			model.addAttribute("id", id);
			model.addAttribute("book_num", book_num);
			model.addAttribute("QueMember", QueMember);
			return "share/queue_list";
		}
		
		/* 매칭확인 */
		if(matching == 2 && proposer != null) {

			Integer confirm = shareService.confirmCancle(book_num);
			System.out.println("Controller CONFIRM DATA - " + confirm);
			
			/* 상대방 취소 확인 */
			if(confirm == null || confirm != id) {
				shareService.cancle(book_num);
				return "share/match_reset"; 
			}
			
			MemberBean Number = shareService.getPhoneNumber(book_num);
			System.out.println("Controller NUMBER DATA - " + Number);
			
			model.addAttribute("book_num", book_num);
			model.addAttribute("Number", Number);
			
			return "share/match_approve";
			
		} else if(matching == 3) {
			return "share/result";
		}
		
		/* 신청자 확인 */ 
		if(proposer != null && matching == 1) {

			/* 쉐어 신청자 정보 가져오기 */
			MemberBean proposerInfo = shareService.getMemberInfo(Integer.parseInt(proposer));
			System.out.println("Controller PROPOSERINFO DATA - " + proposerInfo);
			
			model.addAttribute("id", id);
			model.addAttribute("book_num", book_num);
			model.addAttribute("proposer", proposer);
			model.addAttribute("proposerInfo", proposerInfo);
			return "share/match_propose";
		}

		return "index";

	}
	
	
	/* PRG Pattern: 쉐어신청 - POST */
	@PostMapping(value = "Sign.Intercept")
	public String sign(int user_num_1,
					   int user_num_2,
					   int book_num,
					   Model model) throws Exception{
		/* 진입확인 */
		System.out.println("Controller - Sign.Intercept");
		System.out.println("Controller USER_NUM_1 DATA - " + user_num_1);
		System.out.println("Controller USER_NUM_2 DATA - " + user_num_2);
		System.out.println("Controller BOOK_NUM DATA - " + book_num);
		
		/* 쉐어 신청자 데이터 INSERT */
		int result = shareService.sign(book_num, user_num_2);
		System.out.println("Sign Result - " + result);
		
		
		return "redirect:ShareSign.Intercept";
	}
	
	
	/* PRG Pattern: 쉐어신청 - GET */
	@GetMapping(value = "ShareSign.Intercept")
	public String shareSign(Model model) {
		System.out.println("Controller - ShareSign.Intercept");
		return "share/queue_sign";
	}
	
	/* 쉐어신청 취소 */
	@PostMapping("ProposeCancle.Intercept")
	public String proposeCancle(int book_num) throws Exception{
		/* 진입확인 */
		System.out.println("Controller - ProposeCancle.Intercept");
		
		int result = shareService.proposeCancle(book_num);
		System.out.println("Service CANCLE-RESULT DATA - " + result);
		
		return "share/match_cancle";
	}
	
	
	/* 쉐어신청 수락 & 거절 */
	@GetMapping(value = "SignResult.Intercept")
	public String signResult(String type,
							 int proposer,
							 int book_num,
							 Model model) throws Exception{
		/* 진입확인 */
		System.out.println("Controller - SignResult.Intercept");
		
		/* 요청 결과처리 */
		int result = shareService.signResult(type, proposer, book_num);
		System.out.println("Controller RESULT DATA - " + result);
		if(result == 1004) {
			/* 핸드폰 번호 불러오기 */
			MemberBean Number = shareService.getPhoneNumber(book_num);
			System.out.println("Controller NUMBER DATA - " + Number);
			
			model.addAttribute("book_num", book_num);
			model.addAttribute("Number", Number);
			return "share/match_approve";
		}
		
		return "share/match_cancle";

	}
	
	
	/* 쉐어 최종결정 */
	@GetMapping("ShareResult.Intercept")

	public String shareSuccess(String type, 
							   int book_num) throws Exception{

		/* 진입확인 */
		System.out.println("Controller - ShareResult.Intercept");
		System.out.println("Controller SHARERESULT TYPE DATA - " + type);
		
		/* 승인 */
		if(type.equals("Y")) {
			shareService.success(book_num);
			
			return "share/result";
		}
		
		/* 취소 */
		if(type.equals("N")) {
			shareService.cancle(book_num);
			
			return "share/match_cancle";
		}
		
		return "index";
	}
}
