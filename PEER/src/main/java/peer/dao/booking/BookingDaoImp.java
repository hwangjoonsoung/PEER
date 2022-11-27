package peer.dao.booking;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import peer.model.booking.BookingBean;
import peer.model.house.HouseBean;

@Repository
public class BookingDaoImp implements BookingDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public int bookingInsert(BookingBean bookingbean) {
		// TODO Auto-generated method stub
		return session.insert("peer.dao.booking.HouseViewDao.bookingInsert", bookingbean);
	}

	@Override
	public List<BookingBean> getBookinginfo(int user_num) {
		// TODO Auto-generated method stub
		return session.selectList("peer.dao.booking.HouseViewDao.getBookinginfo", user_num);
	}

	@Override
	public HouseBean getHousename(BookingBean bookingbean) {
		// TODO Auto-generated method stub
		return session.selectOne("peer.dao.booking.HouseViewDao.getHousename", bookingbean);
	}
	
	
	
}
