package peer.dao.booking;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import peer.model.booking.MyBookingBean;
import peer.model.booking.MyShareBean;

@Mapper
public interface MyBookingMapper {

	List<MyShareBean> getMyShareBooking(int id) throws Exception;
	List<MyBookingBean> getMyBooking(int id) throws Exception;
}
