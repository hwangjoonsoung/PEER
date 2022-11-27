package peer.service.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.booking.BookingDao;
import peer.model.booking.BookingBean;
import peer.model.house.HouseBean;

@Service
public interface BookingService {

	public int bookingInsert(BookingBean bookingbean);
	
	public List<BookingBean> getBookinginfo(int user_num);
	
	public HouseBean getHousename(BookingBean bookingbean);
	
}
