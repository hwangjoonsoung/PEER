package peer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import peer.model.member.MemberBean;

public class PeerInterceptor implements HandlerInterceptor{
	@Override
		public boolean preHandle(
				HttpServletRequest request, 
				HttpServletResponse response, 
				Object handler)
				throws Exception {
		
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean)session.getAttribute("MemberBean");
		
		if(memberBean == null) {
			System.out.println("============== INTERCEPTOR ==============");
			response.sendRedirect("Interceptor");
			return false;
		}
		
		int authority = memberBean.getUser_authority();
		
		if(memberBean != null && authority != 1) {
			System.out.println("============== INTERCEPTOR AUTHORITY ==============");
			response.sendRedirect("Suspension");
			return false;
		}
		
		return true;
		}
}
