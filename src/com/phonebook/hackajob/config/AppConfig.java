package com.phonebook.hackajob.config;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.CacheControl;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@ComponentScan("com.phonebook.hackajob")
@Import({ThymeleafAutoConfiguration.class, DispatcherServlet.class, StandardServletMultipartResolver.class}) 
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer ,ApplicationContextAware{


	private ApplicationContext applicationContext;

	
	@Bean 
	public JettyServletWebServerFactory jetty(){
		JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
		factory.setPort(80);
		factory.setDocumentRoot( new File("web"));
		return factory;
	}
	

	@Bean
	  public ViewResolver viewResolver() {
	    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
	    resolver.setCharacterEncoding("UTF-8");
	 
	    return resolver;
	  }

	  @Bean
	  public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.setEnableSpringELCompiler(true);
	    engine.setTemplateResolver(templateResolver());
	    return engine;
	  }

	  private ITemplateResolver templateResolver() {
		    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		    resolver.setApplicationContext(applicationContext);
		    resolver.setTemplateMode(TemplateMode.HTML);
		    return resolver;
		  }


	  public void addResourceHandlers(ResourceHandlerRegistry registry)
		{

			registry.addResourceHandler(new String[] { "/**" }).addResourceLocations(new String[] { "/" }).setCacheControl(CacheControl.maxAge(0, TimeUnit.MILLISECONDS));

		}


	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	
		this.applicationContext = applicationContext;
	  
		
	}
	
}
