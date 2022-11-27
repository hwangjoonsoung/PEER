package peer.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import peer.model.member.MemberBean;
import peer.service.admin.AdminServiceImp;

@Controller
public class AdminController {
	@Autowired
	private AdminServiceImp AdminService;
	
	
	/* 어드민페이지 호출 */
	@GetMapping("call.AdminPage")
	public String admin(Model model) throws Exception{
		
		List<MemberBean> users = AdminService.getUsers();
		model.addAttribute("users", users);
		
		return "admin/admin";
	}
	
	/* 계정정지 */
	@GetMapping("Suspension.Admin")
	public String suspension(int user_num) throws Exception{
		AdminService.suspension(user_num);
		
		return "redirect:call.AdminPage";
	}
	
	/* 계정 활성화 */
	@GetMapping("Access.Admin")
	public String access(int user_num) throws Exception{
		AdminService.access(user_num);
		
		return "redirect:call.AdminPage";
	}
	
	
	

}
