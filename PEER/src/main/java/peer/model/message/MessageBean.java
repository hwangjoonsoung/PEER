package peer.model.message;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageBean {
	private int message_num;
	private int message_sender_num;
	private String message_sender_nick;
	private int message_receiver_num;
	private String message_receiver_nick;
	private Date message_date;
	private String message_content;
}
