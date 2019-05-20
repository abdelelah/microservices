package abdelelahr.com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import abdelelahr.com.service.UsersService;

@Configuration 
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	private UsersService usersService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Environment env;
	
	@Autowired
	public WebSecurity(Environment env,UsersService usersService,BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.env = env;
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway.ip"))
		.and().addFilter(new AuthenticationFilter());
		http.headers().frameOptions().disable();
	}
	
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
	    }

}
