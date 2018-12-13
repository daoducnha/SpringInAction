package spittr.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import spittr.Spitter;
import spittr.Spittle;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({ "spittr.web", "spittr.data" })
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	
	private ApplicationContext applicationContext;

	@Autowired
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
	 //start Thymeleaf specific configuration
    @Bean
    public ServletContextTemplateResolver templateResolver() {
    	ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
    	templateResolver.setPrefix("/WEB-INF/templates/");
    	templateResolver.setSuffix(".html");
    	templateResolver.setTemplateMode("HTML5");
	return templateResolver;
    }
    @Bean   
    public SpringTemplateEngine templateEngine() {
    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    	templateEngine.setTemplateResolver(templateResolver());
	return templateEngine;
    }
    @Bean
    public ThymeleafViewResolver viewResolver(){
    	ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); 
    	viewResolver.setTemplateEngine(templateEngine());
	return viewResolver;
    }

	// using jndi
//  @Bean
//  public JndiObjectFactoryBean dataSourece(){
//    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//    jndiObjectFactoryBean.setJndiName("jdbc/SpittrDS");
//    jndiObjectFactoryBean.setResourceRef(true);
//    jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
//    return  jndiObjectFactoryBean;
//  }

	// using pool
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

	// using JDBC
//  @Bean
//  public DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName("org.h2.Driver");
//    dataSource.setUrl("jdbc:h2:tcp://localhost/~/spitter");
//    dataSource.setUsername("sa");
//    dataSource.setPassword("");
//    return dataSource;
//  }

	// Embedded data source
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("classpath:schema.sql").build();
//    addScript("classpath:test-data.sql").
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(ds);
//		sfb.setPackagesToScan(new String[] { "com.habuma.spittr.domain" });
		sfb.setAnnotatedClasses(new Class[] {Spitter.class, Spittle.class});
		Properties prop = new Properties();
		prop.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		sfb.setHibernateProperties(prop);
		return sfb;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
//
//  @Bean
//  public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate) {
//    return new JdbcSpitterRepository(jdbcTemplate);
//  }

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("spitterPU");
		return emfb;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		emfb.setPackagesToScan("spittr");
		return emfb;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.HSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.HSQLDialect");
		return adapter;
	}
	
	
}