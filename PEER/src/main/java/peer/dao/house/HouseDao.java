package peer.dao.house;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import peer.model.house.HouseBean;
import peer.model.house.HousepriceBean;

@Mapper
public interface HouseDao {
	
	// 숙소 등록
	public int saveHouse(HouseBean h) throws Exception;
	public int savePrice(HousepriceBean hp) throws Exception;
	
	// 숙소 상세 정보 구하기
	public HouseBean getHouseCont(int house_num) throws Exception;
	public HousepriceBean getHpriceCont(int house_num) throws Exception;
	
	// 호스트의 등록된 숙소 리스트
	public List<HouseBean> getHosthouseList(@Param("page") int page, @Param("user_num") int user_num) throws Exception;
	
	// 호스트의 등록된 숙소 갯수
	public int getListCount(int user_num) throws Exception;
	
	// 등록된 숙소 삭제
	public void houseDelete(int house_num) throws Exception;
	public void hpriceDelete(int house_num) throws Exception;
	
	
}
