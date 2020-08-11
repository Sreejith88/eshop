package com.eshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eshop.dto.UserRegistrationDto;
import com.eshop.model.User;
import com.eshop.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	/**
	 * <p>
	 * Method for get registration form.
	 * </p>
	 * 
	 * @param model the {@link Model} instance.
	 * @return the render page name.
	 */
	@GetMapping
	public String showRegistrationForm(final Model model) {
		return "registration";
	}

	/**
	 * <p>
	 * Method for Register user details.
	 * </p>
	 * 
	 * @param userDto the {@link UserRegistrationDto} instance contains the user
	 *                details.
	 * @param result  the {@link BindingResult} instance.
	 * @param model   the {@link Modal} instance
	 * @return the render page name.
	 */
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid final UserRegistrationDto userDto,
			final BindingResult result, final Model model) {

		final User existing = userService.findByEmail(userDto.getEmail());
		if (null != existing && !existing.getEmail().isEmpty()) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		}

		if (result.hasErrors()) {
			return "registration";
		}
		userService.save(userDto);
		return "redirect:/registration?success";
	}

	/**
	 * <p>
	 * Service method for getting all user details.
	 * </p>
	 * 
	 * @param model the {@link Model} instance.
	 * @return the render page name.
	 */
	@GetMapping("/getAllUser")
	public String getAllUser(final Model model) {
		model.addAttribute("userList", this.userService.findAllUser());
		return "user_list";
	}

	/**
	 * <p>
	 * Service method for delete a user details by id.
	 * </p>
	 * 
	 * @param id the user id
	 * @return the render page name.
	 */
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(value = "id") final long id) {

		this.userService.deleteUserById(id);
		return "redirect:/registration/getAllUser";
	}

}
