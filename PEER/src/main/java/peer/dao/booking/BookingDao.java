package peer.dao.booking;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import peer.model.booking.BookingBean;
import peer.model.house.HouseBean;

public interface BookingDao {

	public int bookingInsert(BookingBean bookingbean);
	
	public List<BookingBean> getBookinginfo(int user_num);
	
	public HouseBean getHousename(BookingBean bookingbean);
}
