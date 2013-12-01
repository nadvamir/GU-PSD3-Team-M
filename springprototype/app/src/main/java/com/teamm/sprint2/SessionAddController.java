package com.teamm.sprint2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionAddController {	
	    @RequestMapping("/sessionadd")
	    public String add(
	    		@RequestParam(value="date", 		required=true, defaultValue="") String date, 
	    		@RequestParam(value="time", 		required=true, defaultValue="") String time,
	    		@RequestParam(value="duration", 	required=true, defaultValue="") String dur,
	    		@RequestParam(value="frequency", 	required=true, defaultValue="") String freq,
	    		@RequestParam(value="lecturer", 	required=true, defaultValue="") String lect,
	    		@RequestParam(value="maxAtt",		required=true, defaultValue="") String max,
	    		@RequestParam(value="compulsory",	required=true, defaultValue="") String comp,
	    		@RequestParam(value="venue",		required=true, defaultValue="") String ven,
	    		Model model) {

	    	//TODO: do stuff with those: convert to proper types, add to database
	        return "success";
	    }
}
