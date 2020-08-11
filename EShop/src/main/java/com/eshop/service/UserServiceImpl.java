package com.eshop.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eshop.dto.UserRegistrationDto;
import com.eshop.model.Role;
import com.eshop.model.User;
import com.eshop.repository.UserRepository;

/**
 * <p>
 * The service implementation class for user services.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * {@inheritDoc}
	 */
	public User findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	public User save(final UserRegistrationDto registration) {
		final User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		if (Objects.nonNull(registration) && null != registration.getRole()
				&& !registration.getRole().trim().isEmpty()) {
			user.setRoles(Arrays.asList(new Role(registration.getRole())));
		} else {
			final List<User> userList = this.userRepository.findAll();
			if (userList.size() == 0)
				user.setRoles(Arrays.asList(new Role("ROLE_SUPER_ADMIN")));
			else
				user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		}
		LOGGER.info("Saved User role" + user.getRoles().toString());
		return userRepository.save(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		final User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(final Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);

	}
}
