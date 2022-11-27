package peer.service.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.booking.ShareMapper;
import peer.model.booking.ShareBean;
import peer.model.booking.TotalBean;
import peer.model.member.MemberBean;

@Service
public class ShareServiceImp implements ShareService {
	
	@Autowired
	private ShareMapper shareMapper;
	
	/* 나의 예약내역 갸져오기 */
	public List<ShareBean> getMyBooking(int id) throws Exception {
		/* 진입확인 */
		System.out.println("Service - getMyBooking");
		
		return shareMapper.getMyBooking(id);
	}

	/* 쉐어 대기중인 대기열 리스트 구현 */
	public List<TotalBean> getQueList(int id, int book_num) throws Exception {
		/* 진입확인 */
		System.out.println("Service - getQueList");
		
		/* Session ID 확인 */
		System.out.println("Service ID DATA - " + id);
		
		/* 예약정보 불러오기 */
		ShareBean info = shareMapper.getShareInfo(id, book_num);
		System.out.println("Service INFO DATA - " + info);
		
		/* 성별확인 */
		String gender = shareMapper.getGender(id);
		System.out.println("Service Gender DATA - " + gender);
		
		return shareMapper.getQueList(info, gender);
	}
	
	/* 쉐어 신청자 데이터 INSERT */
	public int sign(int book_num, int user_num_2) throws Exception {
		/* 진입확인 */
		System.out.println("Service - Sign");
		
		/* USER_NUM_2 INSERT 결과 확인 */
		int result = shareMapper.sign(book_num, user_num_2);
		System.out.println("Service Result DATA - " + result);
		
		return result;
	}
	
	/* 쉐어 신청자 확인 */
	public String proposer(int book_num) throws Exception{
		/* 진입확인 */
		System.out.println("Service - proposer");
		
		return shareMapper.proposer(book_num);
	}
	
	/* 쉐어 신청 확인 & 신청이력 정보 */
	public TotalBean propose(int id, int book_num) throws Exception {
		/* 진입확인 */
		System.out.println("Service - propose");
		
		return shareMapper.propose(id, book_num);
	}
	
	/* 쉐어신청 취소 */
	public int proposeCancle(int book_num) throws Exception {
		/* 진입확인 */
		System.out.println("Service - proposeCancle");
		
		return shareMapper.refuse(book_num);
	}
	
	/* 쉐어 신청자 회원 정보 가져오기 */
	public MemberBean getMemberInfo(int proposer) throws Exception {
		/* 진입확인 */
		System.out.println("Service - getMemberInfo");
		
		MemberBean proposerInfo = shareMapper.getMemberInfo(proposer);
		System.out.println("Service PROPOSERINFO DATA - " + proposerInfo);
		
		return proposerInfo;
	}
	
	/* 쉐어요청 결과처리 */
	public int signResult(String type, int proposer, int book_num) throws Exception {
		/* 진입확인 */
		System.out.println("Service - signResult");
		int result = 0;
		
		/* 요청승인 */
		if(type.equals("Y")) {
			shareMapper.approve(book_num);
			shareMapper.requestApprove(proposer, book_num);
			
			result = 1004;
			return result;
		}
		
		/* 요청거절 */
		if(type.equals("N")) {
			result = shareMapper.refuse(book_num);
		}
		
		return result;
	}
	
	/* 상대방 핸드폰 번호 불러오기 */
	public MemberBean getPhoneNumber(int book_num) throws Exception{
		/* 진입확인 */
		System.out.println("Service - getPhoneNumber");
		
		return shareMapper.getPhoneNumber(book_num);
	}
	
	/* 쉐어 매칭확인 */
	public int matching(int book_num) throws Exception {
		/* 진입확인 */
		System.out.println("Service - matching");
		
		return shareMapper.matching(book_num);
	}

	/* 최종결정 - 상대방 취소확인 */
	public Integer confirmCancle(int book_num) throws Exception {
		/* 진입확인 */
		System.out.println("Service - confirmCancle");
		
		return shareMapper.confirmCancle(book_num);
	}

	/* 최종결정 - 승인 */
	public int success(int book_num) throws Exception{
		/* 진입확인 */
		System.out.println("Service - success");
		
		return shareMapper.success(book_num);
	}
	
	/* 최종결정 - 취소 */
	public int cancle(int book_num) throws Exception {
		return shareMapper.cancle(book_num);
	}
}
