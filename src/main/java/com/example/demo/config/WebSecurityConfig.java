package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.demo.springsecurity.service.UserService;

//import com.example.demo.springsecurity.service.UserService;



@EnableWebSecurity
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
@Autowired
	    private UserService userService;
	String[] resources = new String[]{
			 "/webjars/**",
			 "/include/**","/css/**","/fonts/**","/icons/**","/img/**","/js/**",
			 "/images/**","/layer/**",
			 "/home"
			,"/products.html","/store.html","/about.html","/index.html","/vendors**"
			,"/cart/**","/pay/**","/giohang","/Dmmm/**","/thanhtoan/**"
    };
	 
	 @Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests()
				.antMatchers(resources).permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
				.antMatchers("/").permitAll()
				//.antMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập				
				.and()				
			.formLogin()
				.loginPage("/login")
				.permitAll() // Tất cả đều được truy cập vào địa chỉ này
				.and()
			.logout() // Cho phép logout
				.permitAll();
		}
//		@Bean
//		@Override
//		public UserDetailsService userDetailsService() {
//			UserDetails user =
//				 User.withDefaultPasswordEncoder()
//					.username("admin")
//					.password("admin")
//					.roles("Admin")
//					.build();
//
//			return new InMemoryUserDetailsManager(user);
//		}
	 
		 @Bean
		    public BCryptPasswordEncoder passwordEncoder(){
			   // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
		        return new BCryptPasswordEncoder();
		    }

		    @Bean
		    public DaoAuthenticationProvider authenticationProvider(){
		        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		        auth.setUserDetailsService(userService);
		        auth.setPasswordEncoder(passwordEncoder());
		        return auth;
		    }

		    @Override
		    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		        auth.authenticationProvider(authenticationProvider());
		    }

		    @Bean
		    public PersistentTokenRepository persistentTokenRepository() {
		        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); // Ta lưu tạm remember me trong memory (RAM). Nếu cần mình có thể lưu trong database
		        return memory;
		    }
}
