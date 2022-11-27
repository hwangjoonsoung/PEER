package peer.dao.booking;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import peer.model.booking.ShareBean;
import peer.model.booking.TotalBean;
import peer.model.member.MemberBean;

@Mapper
public interface ShareMapper {
	
	/* 나의 예약내역 가져오기 */
	List<ShareBean> getMyBooking(int id) throws Exception;
	
	/* 예약정보 불러오기 */
	ShareBean getShareInfo(
			@Param("id") int id, 
			@Param("book_num") int book_num) throws Exception;
	
	/* 회원성별 가져오기 */
	public String getGender(int id) throws Exception;

	/* 대기열 리스트 */
	List<TotalBean> getQueList(
			@Param("info") ShareBean info,
			@Param("gender") String gender) throws Exception;

	/* 쉐어신청 */
	public int sign(
			@Param("book_num") int book_num,
			@Param("user_num_2") int user_num_2) throws Exception;
	
	/* 쉐어 신청자 확인 */
	public String proposer(int book_num) throws Exception;
	
	/* 쉐어 신청 확인 */
	public TotalBean propose(
			@Param("id") int id, 
			@Param("book_num") int book_num) throws Exception;
	
	/* 회원정보 가져오기 */
	public MemberBean getMemberInfo(int proposer) throws Exception;
	
	/* 쉐어 요청승인 */
	public int approve(int book_num) throws Exception;
	
	/* 쉐어 요청승인 응답 */
	public int requestApprove(
			@Param("proposer") int proposer, 
			@Param("book_num") int book_num) throws Exception;
	
	/* 상대방 핸드폰 번호 불러오기 */
	public MemberBean getPhoneNumber(int book_num) throws Exception;
	
	/* 쉐어 요청거절 */
	public int refuse(int book_num) throws Exception;
	
	/* 쉐어 매칭확인 */
	public int matching(int book_num) throws Exception;
	
	/* 최종결정 - 쉐어확정 */
	public int success(int book_num) throws Exception;

	/* 최종결정 - 쉐어취소 */
	public int cancle(int book_num) throws Exception;

	/* 최종결정 - 상대방 취소확인 */
	public Integer confirmCancle(int book_num) throws Exception;
}
