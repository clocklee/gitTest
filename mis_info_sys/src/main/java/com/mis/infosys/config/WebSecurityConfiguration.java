package com.mis.infosys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mis.infosys.persist.services.UsrService;
import com.mis.infosys.utils.MD5Util;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private UsrService urlUserService;
    @Autowired
    SessionRegistry sessionRegistry;
	
	
	@Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new UsrService();
    }
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	 http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/login").permitAll()
         .antMatchers("/logout").permitAll()
         .antMatchers("/images/**").permitAll()
         .antMatchers("/js/**").permitAll()
         .antMatchers("/css/**").permitAll()
         .antMatchers("/fonts/**").permitAll()
         .antMatchers("/favicon.ico").permitAll()
         .antMatchers("/").permitAll()
         .anyRequest().authenticated()
         .and()
         .sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry)
         .and()
         .and()
         .logout()
         .invalidateHttpSession(true)
         .clearAuthentication(true)
         .and()
         .httpBasic();  	
    	
    	
    	
//        http.authorizeRequests()
//                .anyRequest().authenticated() //任何请求,登录后可以访问
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .permitAll() //登录页面用户任意访问
//                .and()
//                .logout().permitAll(); //注销行为任意访问


    }
    
 /* @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(customUserService()); //user Details Service验证

  }*/
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(urlUserService).passwordEncoder(new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String) rawPassword));
            }
        });
    }

    @Bean
    public SessionRegistry getSessionRegistry(){
        SessionRegistry sessionRegistry=new SessionRegistryImpl();
        return sessionRegistry;
    }
	
	
	

}
