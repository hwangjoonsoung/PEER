package peer.service.house;

import java.util.List;

import peer.model.house.HouseBean;
import peer.model.house.HousepriceBean;

public interface HouseService {

	// 숙소등록
	public int insert(HouseBean h, HousepriceBean hp) throws Exception;
	
	// 숙소 상세
	public HouseBean house_cont(int house_num) throws Exception;
	public HousepriceBean hprice_cont(int house_num) throws Exception;
	
	// 호스트의 등록된 숙소 갯수
	public int getListCount(int user_num) throws Exception;
	
	// 호스트 등록된 숙소 목록
	public List getHosthouseList(int page,int user_num) throws Exception;
	
	// 등록된 숙소 삭제
	public void del_ok(int house_num) throws Exception;
}
