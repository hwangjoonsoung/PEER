package peer.controller.house;

import java.io.File;

import java.util.ArrayList;

import java.util.List;

import java.util.UUID;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import peer.model.house.HouseBean;
import peer.model.house.HousepriceBean;
import peer.model.member.MemberBean;
import peer.service.house.HouseServiceImpl;

@Controller
public class HouseController {

	@Autowired
	private HouseServiceImpl houseService;

	// 숙소 등록 폼
	@RequestMapping("house_insert.Interceptor")
	public String house_insert() {
		return "house/house_insert";
	}

	// 숙소 등록
	@RequestMapping("house_insert_ok.Interceptor")
	public String house_insert_ok(@RequestParam("myfile") List<MultipartFile> mpf,HttpSession session, MultipartHttpServletRequest request, 
			HouseBean h, HousepriceBean hp,String address, String tbox3, double house_x, double house_y)
			throws Exception {
		
		MemberBean member = (MemberBean)session.getAttribute("MemberBean");
	    int user_num = member.getUser_num();
		
	    h.setUser_num(user_num);
	    
		// 주소값+상세주소
		address += (" " + tbox3);
		h.setHouse_address(address);

		// x, y 좌표
		System.out.println("x,y: " + house_x + "," + house_y);

		// 파일 업로드
		if(mpf.size()!=0) {
			
			String path = System.getProperty("user.dir")+"/src/main/resources/static/housefiles";
			// fileList에 중복제거를 위해 uuid를 통해 새로운 파일네임 부여
			List<String> fileList = new ArrayList<>();
			for (int i = 0; i < mpf.size(); i++) {
				String originFile = mpf.get(i).getOriginalFilename();
				String ext = originFile.substring(originFile.lastIndexOf("."));
				String changeFile = UUID.randomUUID().toString() + ext;

				fileList.add(changeFile);

			}
			System.out.println("fileList:" + fileList);
			
			// 파일 업로드
			String fileName = "";
			for (int i = 0; i < mpf.size(); i++) {
				File saveFile = new File(path, fileList.get(i));
				mpf.get(i).transferTo(saveFile);
				
				fileName += "/housefiles/"+fileList.get(i) + ",";
			}
			h.setHouse_photo(fileName);
			System.out.println("house_photo:"+h.getHouse_photo());
		}
		
		houseService.insert(h, hp);
		System.out.println("house_insert_ok");
		return "redirect:/host_house_list.Interceptor";
	}

	// 호스트 숙소 목록
	@RequestMapping("host_house_list.Interceptor")
	public String list(Model model,
					   HttpServletRequest request,
					   HttpSession session) throws Exception {
		// 세션
		MemberBean memberBean = (MemberBean)session.getAttribute("MemberBean");
		String status = memberBean.getUser_status();
		int user_num = memberBean.getUser_num();
		
		// 호스트 게스트 판별
		if(!status.equals("호스트")) {
			return "house/status";
		}
		
		// 호스트 숙소 리스트
		List<HouseBean> hosthouselist = new ArrayList<HouseBean>();

		int page = 1;
		int limit = 10; // 한 화면에 출력할 레코드수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// user_num의 총 리스트 수를 받아옴.
		int listcount = houseService.getListCount(user_num);
		System.out.println("listcount:" + listcount);

		// 페이지 번호(page)와 user_num을 DAO클래스에게 전달한다.
		hosthouselist = houseService.getHosthouseList(page,user_num); // 리스트를 받아옴.

		// 총 페이지 수.
		int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95를 더해서 올림
																	// 처리.
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
		int endpage = maxpage;

		if (endpage > startpage + 10 - 1)
			endpage = startpage + 10 - 1;

		int num = listcount - (page - 1) * 10;

//		model.addAttribute("num", num);
		model.addAttribute("page", page);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("hosthouselist", hosthouselist);

		return "house/host_house_list";
	}

	// 숙소 상세보기 (상세보기,수정폼,삭제폼)
	@RequestMapping(value = "house_cont.Interceptor", method = RequestMethod.GET)
	public String house_cont(@RequestParam("house_num") int house_num, 
							@RequestParam("page") String page,
							@RequestParam("state") String state, 
							Model model, Model model2) throws Exception {
		System.out.println("house_num:" + house_num);
		HouseBean house = houseService.house_cont(house_num); // house 상세정보 구하기
		HousepriceBean hprice = houseService.hprice_cont(house_num); // houseprice 상세정보 구하기
		
		
		String pho = house.getHouse_photo();
		String[] photo = pho.split(",");
		System.out.println(photo[0]);
		
		String[] detail = house.getHouse_detail().split(",");
		
		model.addAttribute("hcont", house);
		model.addAttribute("photo", photo);
		model.addAttribute("detail", detail);
		model2.addAttribute("hpcont", hprice);
		model.addAttribute("page", page);
		model2.addAttribute("page", page);
		if (state.equals("cont")) { // 상세폼
//			String house_cont = house.getHouse_detail2().replace("\n", "<br>");
//			model.addAttribute("house_cont", house_cont);
			return "house/house_cont";
		} else if (state.equals("del")) { // 삭제폼
			return "house/house_delete";
		}
		return null;
	}

	// 등록된 숙소 삭제 
	@RequestMapping(value = "/house_del_ok.Interceptor", method = RequestMethod.POST)
	public String board_del_ok(@RequestParam("house_num") int house_num,
							   @RequestParam("page") int page,
							   Model model) throws Exception {
		// 등록된 숙소 사진 삭제			
		String path = System.getProperty("user.dir")+"/src/main/resources/static";
			
		HouseBean house = houseService.house_cont(house_num);
		String pho = house.getHouse_photo();
		String[] photo = pho.split(",");
		for (int i = 0; i < photo.length; i++) {			
			File deletefile = new File(path+photo[i]);
			deletefile.delete();
		}
		System.out.println("fileDelete_ok!");
		
		System.out.println("houseDelete_ok");
		houseService.del_ok(house_num);	
		
		return "redirect:/host_house_list.Interceptor?page=" + page;
	}
	
}
