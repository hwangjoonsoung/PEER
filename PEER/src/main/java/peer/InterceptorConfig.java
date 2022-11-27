package peer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private intercepter intercepter;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(intercepter).addPathPatterns("/*.Intercept").excludePathPatterns("/*.EI");
		registry.addInterceptor(new PeerInterceptor()).addPathPatterns("/*.Intercept").excludePathPatterns("/*.EI");
	}

}