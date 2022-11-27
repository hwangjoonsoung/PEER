package peer.service.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.booking.BookingDao;
import peer.model.booking.BookingBean;
import peer.model.house.HouseBean;

@Service
public class BookingServiceImp implements BookingService{

	@Autowired
	private BookingDao bdao;

	@Override
	public int bookingInsert(BookingBean bookingbean) {
		// TODO Auto-generated method stub
		return bdao.bookingInsert(bookingbean);
	}

	@Override
	public List<BookingBean> getBookinginfo(int user_num) {
		// TODO Auto-generated method stub
		return bdao.getBookinginfo(user_num);
	}

	@Override
	public HouseBean getHousename(BookingBean bookingbean) {
		// TODO Auto-generated method stub
		return bdao.getHousename(bookingbean);
	}
}
