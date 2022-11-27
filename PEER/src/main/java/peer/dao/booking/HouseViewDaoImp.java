package peer.dao.booking;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import peer.model.booking.HouseViewBean;
import peer.model.house.HouseBean;
import peer.model.house.HousepriceBean;
import peer.model.member.MemberBean;


@Repository
public class HouseViewDaoImp implements HouseViewDao{

	@Autowired
	private SqlSession session;

	// 숙소 가격 불러오기
	@Override
	public HousepriceBean getHpriceCont(int house_num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// 호스트 이름 불러오기
	@Override
	public HouseViewBean getHostname(int house_num) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne("peer.dao.booking.HouseViewDao.getHostname", house_num);
	}

	@Override
	public List gethouseList(int page) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList("peer.dao.booking.HouseViewDao.gethouselist", page);
	}


}
