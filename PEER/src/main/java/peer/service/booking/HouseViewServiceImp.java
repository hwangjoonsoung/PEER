package peer.service.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.booking.HouseViewDao;
import peer.dao.house.HouseDao;
import peer.dao.member.MemberDAO;
import peer.model.booking.HouseViewBean;
import peer.model.house.HouseBean;
import peer.model.house.HousepriceBean;
import peer.model.member.MemberBean;

@Service
public class HouseViewServiceImp implements HouseViewService {

	@Autowired
	private HouseViewDao hvdao;	
	
	@Autowired
	private HouseDao houseDao;

	@Autowired
	private MemberDAO mR;
	
	// 숙소 가격 불러오기
	public HousepriceBean hprice_cont(int house_num) throws Exception {
		HousepriceBean hprice = houseDao.getHpriceCont(house_num);
		return hprice;
	}

	// 호스트 이름 불러오기
	@Override
	public HouseViewBean getHostname(int house_num) throws Exception {
		// TODO Auto-generated method stub
		return hvdao.getHostname(house_num);
	}

	@Override
	public List gethouseList(int page) throws Exception {
		// TODO Auto-generated method stub
		return hvdao.gethouseList(page);
	}
	// 형식 / 함수 / 형식 / 변수
}
