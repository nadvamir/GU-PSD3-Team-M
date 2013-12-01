package com.teamm.sprint2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {	
	    @RequestMapping("/login")
	    public String login(@RequestParam(value="user", required=true, defaultValue="") String user, @RequestParam(value="password", required=true, defaultValue="") String pass, Model model) {
	        model.addAttribute("name", user);
	        if (user.equals("admin")) {
	        	return "sessionadd";
	        } else {
	        	return "timetable";
	        }
	        
	    }
}
