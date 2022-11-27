package peer.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.admin.AdminMapper;
import peer.model.member.MemberBean;

@Service
public class AdminServiceImp implements AdminService {
	@Autowired
	private AdminMapper AdminMapper;
	
	/* 유저목록 */
	public List<MemberBean> getUsers() throws Exception {
		return AdminMapper.getUsers();
	}
	
	/* 계정정지 */
	public int suspension(int user_num) throws Exception {
		return AdminMapper.suspension(user_num);
	}

	/* 계정 활성화 */
	public int access(int user_num) throws Exception {
		return AdminMapper.access(user_num);
	}

}
