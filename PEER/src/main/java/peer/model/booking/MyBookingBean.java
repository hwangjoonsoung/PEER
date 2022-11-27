package peer.model.booking;

import java.util.Date;

import lombok.Data;

@Data
public class MyBookingBean {

    private int book_num;
    private int house_num;
    private int house_price;
    private int user_num;
    private Date checkin;
    private Date checkout;
    private int total_price;
    private String house_name;
}