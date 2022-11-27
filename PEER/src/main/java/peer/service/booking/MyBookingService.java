package peer.service.booking;

import java.util.List;

import peer.model.booking.MyBookingBean;
import peer.model.booking.MyShareBean;

public interface MyBookingService {
	
	/* 예약내역 불러오기 */
	List<MyShareBean> getMyShareBooking(int id) throws Exception;
	List<MyBookingBean> getMyBooking(int id) throws Exception;
}
