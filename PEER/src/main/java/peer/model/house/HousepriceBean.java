package peer.model.house;

import lombok.Data;

@Data
public class HousepriceBean {

	private int house_num;
	private String daterange;
	private int hprice_bweek;
	private int hprice_bweekend;
	private int hprice_sweek;
	private int hprice_sweekend;
}
