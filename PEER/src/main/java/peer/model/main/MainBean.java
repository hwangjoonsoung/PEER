package peer.model.main;

import lombok.Data;

@Data
public class MainBean {	
	/* 숙소정보 불러오기 */
	private int house_num;
    private String house_name;
    private String house_address;
    private String house_photo;
	private Double house_x;
	private Double house_y;
	private int house_share;
}
