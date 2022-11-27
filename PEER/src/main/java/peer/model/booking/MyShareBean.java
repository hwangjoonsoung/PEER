package peer.model.booking;

import java.util.Date;

import lombok.Data;

@Data
public class MyShareBean {
	private int book_num;
	private int house_num;
	private Date checkin;
	private Date checkout;
	private int user_num_1;
	private int user_num_2;
	private int share_check;
	private String house_name;
}