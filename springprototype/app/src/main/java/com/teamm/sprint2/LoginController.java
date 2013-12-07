package com.teamm.sprint2;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {	
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);
	@RequestMapping("/login")
	public String login(@RequestParam(value="username", required=true, defaultValue="") String user, @RequestParam(value="password", required=true, defaultValue="") String pass, Model model) {
		model.addAttribute("name", user);
//		logger.info("login by"+user);
		if (user.equals("admin")) {
			return "timetable-admin";
		} else {
			return "timetable";
		}

	}
}
