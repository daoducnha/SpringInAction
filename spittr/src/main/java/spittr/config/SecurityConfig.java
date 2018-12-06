//package spittr.config;
//
//import javax.sql.DataSource;
//import javax.xml.crypto.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import spittr.data.SpitterRepository;
//import spittr.security.SpitterUserService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Autowired
//  private SpitterRepository spitterRepository;
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
////    http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//
////    http.authorizeRequests().antMatchers("/spitters/me").authenticated()
////        .antMatchers(HttpMethod.POST,"//spittles").authenticated()
////        .anyRequest().permitAll();
//
////    http.authorizeRequests().antMatchers("/spitters/me")
////        .access("hasRole('ROLE_SPITTER') and hasIpAddress('192.168.1.2')");
////
////    http.authorizeRequests().antMatchers("/spitters/me").hasRole("SPITTER")
////        .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER").anyRequest().permitAll()
////        .and().requiresChannel().antMatchers("/spitter/form").requiresSecure();
//
//    http.formLogin().and().authorizeRequests().antMatchers("/spitter/me").hasRole("SPITTER")
//        .antMatchers(HttpMethod.POST, "/spittles").hasRole("SPITTER")
//        .anyRequest().permitAll().and().requiresChannel().antMatchers("/spitter/form").requiresSecure();
//  }
//
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    //Config in memory user
////    auth
////        .inMemoryAuthentication()
////        .withUser("user").password("password").roles("USER").and()
////        .withUser("admin").password("password").roles("USER", "ADMIN");
//
//    //LDAP config
////    auth
////        .ldapAuthentication()
////        .userSearchBase("ou=people")
////        .userSearchFilter("(uid={0})")
////        .groupSearchBase("ou=groups")
////        .groupSearchFilter("member={0}")
////        .contextSource()
////        .root("dc=habuma,dc=com")
////        .ldif("classpath:users.ldif");
//
//    auth.userDetailsService(new SpitterUserService(spitterRepository));
//  }
//
//
//}
