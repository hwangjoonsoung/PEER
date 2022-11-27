package peer.dao.message;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import peer.model.message.MessageBean;

@Mapper
public interface MessageMapper {
	// 쪽지 리스트
	public List<MessageBean> msgList(@Param("user_num") int user_num, 
			@Param("startPage") int startPage, @Param("endPage") int endPage) throws Exception;
	
	// 쪽지 전체 개수
	public int msgTotal(int user_num) throws Exception;
	
	// 쪽지 보내기&답장하기
	public void msgSend(MessageBean msg) throws Exception;
	
	// 회원 권한(상태) 체크
	public int userCheck(int user_num) throws Exception;
	
	// 회원번호에서 닉네임 추출하기
	public String nickFromNum(int message_receiver_num) throws Exception;
	
	// 쪽지 상세보기
	public MessageBean msgOpen(int message_num) throws Exception;
	
	// 쪽지 삭제
	public void msgDel(int message_num) throws Exception;
}
