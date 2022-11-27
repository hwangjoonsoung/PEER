package peer.controller.booking;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import peer.model.booking.BookingBean;
import peer.model.booking.HouseViewBean;
import peer.model.house.HouseBean;
import peer.model.house.HousepriceBean;
import peer.model.member.MemberBean;
import peer.service.booking.HouseViewService;
import peer.service.house.HouseService;
import peer.service.member.MemberService;

@Controller
public class HouseViewController {

	//계정정보 불러오기, 예약한것 데베에 삽입하기, 예약한것 예약내역에 나타내기
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private HouseViewService hvservice;
	
	@Autowired
	private MemberService ms;

	// 숙소 상세보기 (상세보기)
		@RequestMapping(value = "/HouseView", method = RequestMethod.GET)
		public String house_cont(@RequestParam("house_num") int house_num, 
//								@RequestParam("page") String page,
								@RequestParam("state") String state, 
								HttpSession session,
								Model model) throws Exception {			
			
			HouseBean house = houseService.house_cont(house_num); // house 상세정보 구하기
			MemberBean member = (MemberBean)session.getAttribute("MemberBean");
			HouseViewBean hostname = hvservice.getHostname(house_num);
			
			System.out.println("house_num:"+house.getHouse_num());
			System.out.println("hostname:"+hostname.getUser_name());
			System.out.println("house:"+house);
			
			HousepriceBean hprice = houseService.hprice_cont(house_num); // houseprice 상세정보 구하기
		    
			String details =  house.getHouse_detail();
		    String[] detail = details.split(",");
		      
		    String Pho = house.getHouse_photo();
		    System.out.println("Pho - " + Pho);
			String[] PhotoList = Pho.split(",");
			String[] Photos = new String[PhotoList.length-1];
			System.out.println("PhotoList - " + PhotoList.toString());
			
			String FirstPhoto = PhotoList[0];
			System.out.println("FirstPho - " + FirstPhoto);
			System.out.println("PhotoList Length - " + PhotoList.length);
			System.out.println("Photos Length - " + Photos.length);
			
			if(PhotoList.length > 1) {
				for(int i = 1; i < PhotoList.length; i++) {
					Photos[i-1] = PhotoList[i];
				}
			}
			
			System.out.println("Photos - " + Photos[0]);
			
			model.addAttribute("hcont", house);
			model.addAttribute("house_num", house.getHouse_num());
			model.addAttribute("hostname", hostname);
		    model.addAttribute("detail", detail);
			model.addAttribute("FirstPhoto", FirstPhoto);
			model.addAttribute("Photos", Photos);
			model.addAttribute("hpcont", hprice);
			model.addAttribute("member", member);
//			model.addAttribute("page", page);
			
			if (state.equals("cont")) { // 상세폼

				return "booking/HouseView";
			}
			return null;
		}

}
