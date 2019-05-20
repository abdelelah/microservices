package abdelelahr.com.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import abdelelahr.com.shared.UserDto;

public interface UsersService extends UserDetailsService{
	UserDto createUser(UserDto userDetails);
	
}
