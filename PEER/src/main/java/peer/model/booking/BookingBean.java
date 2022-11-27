package peer.model.booking;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingBean {

    private int book_num;
    private int house_num;
    private int house_price;
    
    // 게스트 회원 번호
    private int user_num;
    
    private Date checkin;
    private Date checkout;
    private int total_price;
    
//호스트번호
//    private int USER_EMAIL;
//    private Date CHECKIN;
//    private Date CHECKOUT;
//    private int HOUSE_PRICE;
}
