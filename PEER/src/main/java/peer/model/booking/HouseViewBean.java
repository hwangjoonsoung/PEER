package peer.model.booking;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import peer.model.house.HouseBean;
import peer.model.house.HousepriceBean;
import peer.model.member.MemberBean;


@Getter
@Setter
public class HouseViewBean {

	// 숙소 정보
    private int house_num;
    private int user_num;		//호스트유저번호
    private String user_name;	//호스트이름
    private String house_name;
    private String house_address;
    private String house_photo;
    private int house_form;
    private String house_detail;
    
    
    // 가격
	private int hprice_bweek;
	private int hprice_bweekend;
	private int hprice_sweek;
	private int hprice_sweekend;

}
