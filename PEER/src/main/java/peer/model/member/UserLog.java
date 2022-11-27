package peer.model.member;


import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("log")
public class UserLog {

	private int user_lognum;
	private String user_name;
	private int user_num;
	private String user_do;
	private Date user_date;

}
