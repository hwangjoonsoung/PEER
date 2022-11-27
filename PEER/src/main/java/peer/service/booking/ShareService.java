package peer.service.booking;

import java.util.List;

import peer.model.booking.ShareBean;
import peer.model.booking.TotalBean;
import peer.model.member.MemberBean;

public interface ShareService {
	
	/* 나의 예약정보 가져오기 */
	public List<ShareBean> getMyBooking(int id) throws Exception;
	
	/* 대기열 */
	public List<TotalBean> getQueList(int id, int book_num) throws Exception;
	
	/* 쉐어신청 */
 	public int sign(int book_num, int user_num_2) throws Exception;
 	
 	/* 쉐어 신청자 확인 */
 	public String proposer(int book_num) throws Exception;
 	
 	/* 쉐어 신청 확인 */
 	public TotalBean propose(int id, int book_num) throws Exception;
 	
 	/* 쉐어 신청 취소 */
 	public int proposeCancle(int book_num) throws Exception;
 	
 	/* 회원정보 가져오기 */
 	public MemberBean getMemberInfo(int proposer) throws Exception;
 	
 	/* 쉐어요청 결과처리 */
 	public int signResult(String type, int proposer, int book_num) throws Exception;
 	
 	/* 상대방 핸드폰 번호 불러오기 */
 	public MemberBean getPhoneNumber(int book_num) throws Exception;
 	
 	/* 쉐어 매칭확인 */
 	public int matching(int book_num) throws Exception;
	
	/* 최종결정 - 상대방 취소확인 */
	public Integer confirmCancle(int book_num) throws Exception;
	
	/* 최종결정 - 승인 */
	public int success(int book_num) throws Exception;
	
	/* 최종결정 - 취소 */
	public int cancle(int book_num) throws Exception;
}
