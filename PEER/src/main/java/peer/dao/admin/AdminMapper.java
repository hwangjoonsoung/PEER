package peer.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import peer.model.member.MemberBean;

@Mapper
public interface AdminMapper {
	
	public List<MemberBean> getUsers() throws Exception;

	public int suspension(int user_num) throws Exception;

	public int access(int user_num) throws Exception;

}
