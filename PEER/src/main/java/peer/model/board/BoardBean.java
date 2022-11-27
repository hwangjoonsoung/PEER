package peer.model.board;

import java.util.Date;

import lombok.Data;

@Data
public class BoardBean {
	
	private int board_num;
	private String board_title;
	private String board_content;
	private String board_photo;
	private Date board_date;
	private int board_likecount;
	private int board_readcount;
	private int board_authority;
	private int user_num;
	private String user_nickname;
}
