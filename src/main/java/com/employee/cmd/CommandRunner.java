package com.employee.cmd;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.employee.config.UserSecurity;
import com.employee.entity.User;
import com.employee.model.UserRole;
import com.employee.repository.UserRepository;

@Component
public class CommandRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserSecurity security;

	@Override
	public void run(String... args) throws Exception {
		User dbUser = userRepository.findByuserName("admin");
		if(dbUser==null) {
	
		User user=new User();
		user.setUserName("admin");
		user.setPassword(security.passwordEncoder().encode("admin"));
		user.setEmail("admin@gmail.com");
		user.setAccountNonExpired(Boolean.TRUE);
		user.setAccountNonLocked(Boolean.TRUE);
		user.setCredentialsNonExpired(Boolean.TRUE);
		user.setEnabled(Boolean.TRUE);
		user.setUserRole(Arrays.asList(UserRole.ROLE_ADMIN.getUserRole()));
		userRepository.save(user);
		}

	}

}
