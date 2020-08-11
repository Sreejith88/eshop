package com.eshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshop.model.User;

/**
 * <p>
 * The repository interface for User activities.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
