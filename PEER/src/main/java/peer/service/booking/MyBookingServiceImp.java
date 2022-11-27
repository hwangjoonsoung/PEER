package peer.service.booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import peer.dao.booking.MyBookingMapper;
import peer.model.booking.MyBookingBean;
import peer.model.booking.MyShareBean;

@Service
public class MyBookingServiceImp implements MyBookingService {
	
	@Autowired
	private MyBookingMapper myBookingMapper;

	@Override
	public List<MyShareBean> getMyShareBooking(int id) throws Exception {
		return myBookingMapper.getMyShareBooking(id);
	}

	@Override
	public List<MyBookingBean> getMyBooking(int id) throws Exception {
		return myBookingMapper.getMyBooking(id);
	}
}