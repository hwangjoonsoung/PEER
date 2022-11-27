package peer.model.member;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberBean {

	private int user_num;
	private String user_email;
	private String user_pass;
	private String user_name;
	private String user_gender;
	private int user_age;
	private String user_phone;
	private String user_nickname;
	private Date user_rege;
	private String user_status;
	private int user_authority;
	private Date user_birth;
	
}
