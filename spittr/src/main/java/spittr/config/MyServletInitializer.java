package spittr.config;

import javax.servlet.Registration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import spittr.Spitter;

public class MyServletInitializer implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
//    Dynamic myServlet = servletContext.addServlet("myServlet", Spitter.class);
//    ((ServletRegistration.Dynamic) myServlet).addMapping("afda");
  }
}
