package io.starinc.api.v1.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//	@Autowired
//	private HttpInterceptor httpInterceptor;

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(httpInterceptor)
//		.addPathPatterns("/**/*");
//		
//	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*")
			.maxAge(3600);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// registry.addRedirectViewController("/", "index.html");
		registry.addViewController("/").setViewName("forward:/index.html");
	}
}