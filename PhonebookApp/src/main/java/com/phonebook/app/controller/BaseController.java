package com.phonebook.app.controller;

/****
 * This BaseController is to display welcome page with all the options.
 * @author Mounika
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BaseController {
	@RequestMapping(method = RequestMethod.GET)
	public String showMain() {

		// model.addAttribute("movie", "");
		return "Index";
	}

}
