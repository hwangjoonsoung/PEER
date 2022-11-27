package peer.service.booking;

import java.util.List;

import peer.model.booking.HouseViewBean;
import peer.model.house.HouseBean;
import peer.model.house.HousepriceBean;
import peer.model.member.MemberBean;

public interface HouseViewService {

	// 숙소 가격 불러오기
	public HousepriceBean hprice_cont(int house_num) throws Exception;

	// 호스트 이름 불러오기
	public HouseViewBean getHostname(int house_num) throws Exception;

	//
	public List gethouseList(int page) throws Exception;
}
