package peer.controller.main;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import peer.model.main.MainBean;
import peer.service.main.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService MainService;
	
	@RequestMapping("/")
	public String index() {
		return "main";
	}
	
		@RequestMapping("search")
	   public String search(String loc, Model model, HttpServletRequest request) throws Exception{
	
	      
	      // 메인 검색
	      System.out.println("loc:"+loc);
	      List<MainBean> houselist = MainService.search(loc); 
	            
	      model.addAttribute("hosthouselist", houselist);
	      
	      return "search";
	   }
	
	/* 인터셉터 구현 - 김동민 */
	@RequestMapping("Interceptor")
	public String interceptor() {
		System.out.println("******* None Login Session *******");
		return "Interceptor";
	}
	
	/* 접근제한 구현 - 김동민 */
	@RequestMapping("Suspension")
	public String suspension() {
		System.out.println("******* None Login access *******");
		return "Suspension";
	}
}