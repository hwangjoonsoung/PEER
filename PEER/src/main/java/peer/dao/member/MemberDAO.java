package peer.dao.member;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import peer.model.member.Chart;
import peer.model.member.MemberBean;
import peer.model.member.UserLog;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSession ss;

	public int insert(MemberBean member) {
		int a = ss.insert("insert", member);
		return a;
	}

	public MemberBean searchinfo(String user_email) {
		MemberBean member;
		member = ss.selectOne("searchinfo", user_email);
		return member;
	}

	public MemberBean nicknamecheck(String user_nickname) {
		MemberBean member = ss.selectOne("nicknamecheck", user_nickname);
		return member;
	}

	public int updateuser(MemberBean member) {
		int result = ss.update("updateuser", member);
		return result;
	}

	public int deleteuser(int user_num) {
		int result = ss.update("deleteuser", user_num);
		return result;
	}

	public ArrayList<MemberBean> searchid(String user_phone) {
		ArrayList<MemberBean> list = (ArrayList) ss.selectList("searchid", user_phone);
		return list;
	}

// ----------------admin method------------------------
	public int insertLog(UserLog log) {
		System.out.println("dao : " + log);
		int result = ss.insert("insertLog", log);
		return result;
	}

	public ArrayList<String> getUser() {
		List<String> userList = ss.selectList("getUser");
		return (ArrayList<String>) userList;
	}

	public ArrayList<UserLog> getlog(int user_num) {
		List<UserLog> userloglist = ss.selectList("getlog", user_num);
		return (ArrayList<UserLog>) userloglist;
	}

	public int totalLog(int user_num) {
		return ss.selectOne("totalLog", user_num);
	}

	public ArrayList<Chart> getUserChartInfo(int user_num) {
		List<Chart> userChartInfo = ss.selectList("getUserChartInfo", user_num);
		return (ArrayList<Chart>) userChartInfo;
	}
	
	public MemberBean admininfo(String user_email) {
		MemberBean member;
		member = ss.selectOne("admininfo", user_email);
		return member;
	}

}
