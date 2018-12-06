package spittr.config;

import java.sql.DriverManager;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import spittr.data.SpitterRepository;
import spittr.data.impl.JdbcSpitterRepository;

@Configuration
@EnableWebMvc
@ComponentScan({"spittr.web", "spittr.data"})
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

//  @Bean
//  public ViewResolver viewResolver(){
//    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//    resolver.setPrefix("/WEB-INF/views/");
//    resolver.setSuffix(".html");
//    resolver.setExposeContextBeansAsAttributes(true);
//    return resolver;
//  }

  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine());
    resolver.setCharacterEncoding("UTF-8");
    return resolver;
  }

  @Bean
  public TemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setEnableSpringELCompiler(true);
    engine.setTemplateResolver(templateResolver());
    return engine;
  }

  private ITemplateResolver templateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("/WEB-INF/templates/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode("HTML5");
    return resolver;
  }

  //using jndi
//  @Bean
//  public JndiObjectFactoryBean dataSourece(){
//    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//    jndiObjectFactoryBean.setJndiName("jdbc/SpittrDS");
//    jndiObjectFactoryBean.setResourceRef(true);
//    jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
//    return  jndiObjectFactoryBean;
//  }

  //using pool
//  @Bean
//  public BasicDataSource dataSource(){
//    BasicDataSource dataSource = new BasicDataSource();
//    dataSource.setDriverClassName("org.h2.Driver");
//    dataSource.setUrl("jdbc:h2:tcp://localhost/~/spitter");
//    dataSource.setUsername("sa");
//    dataSource.setPassword("");
//    dataSource.setInitialSize(5);
//    dataSource.setMaxActive(10);
//    return dataSource;
//  }

  //  using JDBC
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:tcp://localhost/~/spitter");
    dataSource.setUsername("sa");
    dataSource.setPassword("");
    return dataSource;
  }

  //Embedded data source
  public DataSource dataSource1() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:schema.sql").addScript("classpath:test-data.sql").build();
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource){
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate){
    return new JdbcSpitterRepository(jdbcTemplate);
  }
  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }


  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

}
