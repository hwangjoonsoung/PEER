package peer.controller.booking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import peer.model.booking.BookingBean;
import peer.model.house.HouseBean;
import peer.model.member.MemberBean;
import peer.service.booking.BookingService;
import peer.service.member.MemberService;


@Controller
public class BookingController {

	@Autowired
	private MemberService ms;
	
	@Autowired
	private BookingService bservice;
	
	
	@RequestMapping(value="/paying")
	public String paying() {
		return "booking/paying";
	}

	@RequestMapping("bookingInsert.do")
	public String bookingInsert(BookingBean bookingbean, @RequestParam("house_num") int house_num, Model model, HttpSession session)throws Exception {
		
		System.out.println("결제 성공 payingInsert");
		MemberBean member = (MemberBean)session.getAttribute("MemberBean");	// 로그인된 유저정보 불러오기
		
		int user_num = member.getUser_num();
		
		bookingbean.setHouse_num(house_num);
		bookingbean.setUser_num(user_num);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");	// checkin, checkout 날짜형 보기 편하게 하는 용
		
		System.out.println("book_num:" + bookingbean.getBook_num());
		System.out.println("house_num:" + bookingbean.getHouse_num());
		System.out.println("user_num:" + bookingbean.getUser_num());
		System.out.println("checkin:" + simpleDateFormat.format(bookingbean.getCheckin()));
		System.out.println("checkout:" + simpleDateFormat.format(bookingbean.getCheckout()));

		System.out.println("house_price:" + bookingbean.getHouse_price());
		System.out.println("total_price:" + bookingbean.getTotal_price());
		
		
		int result=0;
		
//		String user_email = (String)session.getAttribute("user_email");	// 해당 유저의 예약
		
		result = bservice.bookingInsert(bookingbean);
		if(result == 1) System.out.println("예약완료");
		
		model.addAttribute("result", result);
				
		return "booking/bookingInsert";
	}
	
	@RequestMapping("/payresult")
	public String payresult(Model model, HttpSession session) throws Exception {
		
		System.out.println("결제 결과");		
		// book_num, house_name, checkin, checkout, totalprice
		
		MemberBean member = (MemberBean)session.getAttribute("MemberBean");	// 로그인된 유저정보 불러오기
		int user_num = member.getUser_num();
		
		List<BookingBean> getBookinginfo = bservice.getBookinginfo(user_num);
//		HouseBean gethousename = bservice.getHousename(bookingbean);
		
		BookingBean Info = getBookinginfo.get(0);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
		
//		System.out.println("housename:"+gethousename.getHouse_name());
		System.out.print("Info BookNum - " + Info.getBook_num());
		System.out.print("Info BookNum - " + simpleDateFormat.format(Info.getCheckin()));
		System.out.print("Info BookNum - " + simpleDateFormat.format(Info.getCheckout()));
		System.out.print("Info BookNum - " + Info.getTotal_price());
		
		model.addAttribute("Info", Info);									// 예약번호
//		model.addAttribute("gethousename", gethousename.getHouse_name());	// 숙소이름
		
		return "booking/payresult";
	}	
	
 
}
