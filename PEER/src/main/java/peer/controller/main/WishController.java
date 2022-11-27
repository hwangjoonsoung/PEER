package peer.controller.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import peer.model.member.MemberBean;
import peer.model.wishlist.WishlistBean;
import peer.model.house.HouseBean;
import peer.service.wishlist.WishlistService;

@Controller
public class WishController {
	@Autowired
	private WishlistService ws;

	// 테스트 코드
	@RequestMapping("/wishtest")
	public String wishtest(Model model, HttpSession session) throws Exception {
		return "/wishlist/mywishlist";
	}
	
	// 위시리스트 저장
	@RequestMapping("/addwishlist.Intercept")
	public String addWishlist(int house_num, Model model, HttpSession session) throws Exception {
		
		// 세션 불러오기
		MemberBean user_info = new MemberBean();
		user_info = (MemberBean)session.getAttribute("MemberBean");
		
		// 세션에서 user_num 추출
		int user_num = user_info.getUser_num();
		
		WishlistBean wishTemp = new WishlistBean();
		wishTemp.setHouse_num(house_num);
		wishTemp.setUser_num(user_num);
		
		ws.addWl(wishTemp);
		
		
		return "/wishlist/";
	}
/*	
	// 위시 리스트 불러오기
	@RequestMapping("/wishlist.Intercept")
	public String wishlist(Model model, HttpSession session) throws Exception {
		
		// 세션 불러오기
		MemberBean user_info = new MemberBean();
		user_info = (MemberBean)session.getAttribute("MemberBean");
		
		// 세션에서 user_num 추출
		int user_num = user_info.getUser_num();
		
		// user_num에 맞는 위시리스트 추출
		List<WishlistBean> userlist = ws.getWl(user_num);
		
		// 담을 리스트 선언
		List<HouseBean> wishlist = new ArrayList();
		while (userlist.size() > 0) {
			for (int i=0;i<userlist.size();i++) {
				int house_num = userlist.get(i);
				wishlist = ws.getHnum(house_num);
			}
			
		}
		
		
		model.addAttribute("wishlist", wishlist);

		return "/wishlist/mywishlist";
	}
*/	
}
