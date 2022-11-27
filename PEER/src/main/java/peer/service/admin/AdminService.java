package peer.service.admin;

import java.util.List;

import peer.model.member.MemberBean;

public interface AdminService {
	
	/* 유저목록 */
	public List<MemberBean> getUsers() throws Exception;
	
	/* 계정정지 */
	public int suspension(int user_num) throws Exception;
	
	/* 계정복구 */
	public int access(int user_num) throws Exception;
}
