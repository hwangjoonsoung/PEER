package peer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import peer.model.member.MemberBean;
import peer.model.member.UserLog;
import peer.service.member.MemberService;

@Component
public class intercepter extends HandlerInterceptorAdapter {

	@Autowired
	MemberService ms;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		MemberBean member = (MemberBean) session.getAttribute("MemberBean");
		if (member != null) {
			UserLog log = new UserLog();
			Date today = new Date();

			String readyToMappingName = request.getRequestURI();
			if (!readyToMappingName.equals("/error")) {
				log.setUser_do(readyToMappingName);
				log.setUser_name(member.getUser_name());
				log.setUser_num(member.getUser_num());
				log.setUser_date(today);
			} else {
				return true;
			}
			ms.insertLog(log);
			return true;
		}

		return true;
	}

}