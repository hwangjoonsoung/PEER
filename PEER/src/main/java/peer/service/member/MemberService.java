package peer.service.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.member.MemberDAO;
import peer.model.member.Chart;
import peer.model.member.MemberBean;
import peer.model.member.UserLog;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mR;

	public int createMember(MemberBean member) {
		return mR.insert(member);
	}

	public MemberBean searchinfo(String user_email) {
		return mR.searchinfo(user_email);
	}

	public MemberBean nicknamecheck(String user_nickname) {
		return mR.nicknamecheck(user_nickname);
	}

	public int updateuser(MemberBean member) {
		return mR.updateuser(member);
	}

	public int deleteuser(int user_num) {
		return mR.deleteuser(user_num);
	}

	public ArrayList<MemberBean> searchid(String user_phone){
		System.out.println(user_phone);
		return mR.searchid(user_phone);
	}
	
	public int insertLog(UserLog log) {
		System.out.println("service : "+log);
		return mR.insertLog(log);
	}
	
	public ArrayList<String> getUser() {
		return mR.getUser();
	}
	
// ----------------admin method------------------------	
	public ArrayList<UserLog> getlog(int user_num){
		return mR.getlog(user_num);
	}
	
	public int totalLog(int user_num) {
		return mR.totalLog(user_num);
	}
	
	public ArrayList<Chart> getUserChartInfo(int user_num){
		return mR.getUserChartInfo(user_num);
	}
	
	public MemberBean admininfo(String user_email) {
		return mR.admininfo(user_email);
	}

	
}
