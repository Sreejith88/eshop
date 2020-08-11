/**
 * 
 */
package com.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * The Default controller class for Eshop Application.
 * </p>
 * 
 * @author Sreejith
 * @version 1.0
 * @since July 2020
 *
 */
@Controller
public class MainController {

	@GetMapping(path = {"/", "/dashboard"})
	public String root() {
		return "redirect:/getAllProducts?dashboard=true";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
}
