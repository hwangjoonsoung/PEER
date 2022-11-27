package peer.model.booking;

import java.util.Date;

import lombok.Data;

@Data
public class TotalBean {
	/* ShareBean */
	private int book_num;
	private int house_num;
	private Date checkin;
	private Date checkout;
	private int user_num_1;
	private int user_num_2;
	private int share_check;
	
	/* MemberBean */
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
	private String user_authority;
	private Date user_birth;
}
