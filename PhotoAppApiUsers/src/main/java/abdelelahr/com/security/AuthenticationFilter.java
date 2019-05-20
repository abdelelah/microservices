package abdelelahr.com.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import abdelelahr.com.ui.model.LoginRequestModel;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	 @Override
	    public Authentication attemptAuthentication(HttpServletRequest req,
	                                                HttpServletResponse res) throws AuthenticationException {
	        try {
	  
	            LoginRequestModel creds = new ObjectMapper()
	                    .readValue(req.getInputStream(), LoginRequestModel.class);
	            
	            return getAuthenticationManager().authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            creds.getEmail(),
	                            creds.getPassword(),
	                            new ArrayList<>())
	            );
	            
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 
	 @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {
//	    	String userName = ((User) auth.getPrincipal()).getUsername();
//	    	UserDto userDetails = usersService.getUserDetailsByEmail(userName);
//	    	
//	        String token = Jwts.builder()
//	                .setSubject(userDetails.getUserId())
//	                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
//	                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret") )
//	                .compact();
//	        
//	        res.addHeader("token", token);
//	        res.addHeader("userId", userDetails.getUserId());
	    } 
	    

	  
}
