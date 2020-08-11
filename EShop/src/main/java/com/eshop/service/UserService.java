package com.eshop.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eshop.model.User;
import com.eshop.dto.UserRegistrationDto;

/**
 * <p>
 * The interface for user services.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
public interface UserService extends UserDetailsService {

	/**
	 * <p>
	 * find the user details by using username(email).
	 * </p>
	 * 
	 * @param email the email address.
	 * @return the {@link User} instance.
	 */
	User findByEmail(String email);

	/**
	 * <p>
	 * Save user details in database.
	 * </p>
	 * 
	 * @param registration the {@link UserRegistrationDto} instance.
	 * @return the saved {@link User} instance.
	 */
	User save(UserRegistrationDto registration);

	/**
	 * <p>
	 * Service method for getting all user details.
	 * </p>
	 * 
	 * @return the list of {@link User} instance
	 */
	List<User> findAllUser();

	/**
	 * <p>
	 * Service method for delete a user details by id.
	 * </p>
	 * 
	 * @param id the user id
	 */
	void deleteUserById(long id);
}
