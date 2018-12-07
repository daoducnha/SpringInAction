package spittr.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
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
//  @Bean
//  public DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName("org.h2.Driver");
//    dataSource.setUrl("jdbc:h2:tcp://localhost/~/spitter");
//    dataSource.setUsername("sa");
//    dataSource.setPassword("");
//    return dataSource;
//  }

  //Embedded data source
  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:schema.sql").build();
//    addScript("classpath:test-data.sql").
  }


  @Bean
  public LocalSessionFactoryBean sessionFactory(DataSource ds) {
    LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
    sfb.setDataSource(ds);
    sfb.setPackagesToScan(new String[]{"com.habuma.spittr.domain"});
    Properties prop = new Properties();
    prop.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
    sfb.setHibernateProperties(prop);
    return sfb;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate) {
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
