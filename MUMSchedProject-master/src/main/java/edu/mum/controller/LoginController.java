package edu.mum.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
	public class LoginController {


     @GetMapping("/guestPage")
     public String noRoleUserPage(){

    	 return "guestPage";
     }
     @GetMapping(value = "/home")
 	public String facultyHome(Model model, Map<?, ?> map) {
 		model.addAttribute("loggedInUser", map.get("username"));
 		return "home";
 	}

     @GetMapping(value = "/403")
  	public String accessDeniedError(Model model, Map<?, ?> map) {

  		return "error/403";
  	}
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";

    }


     @RequestMapping(value = "/login", method = RequestMethod.GET)
     public String login(ModelMap model) {

         return "login";

     }

     @RequestMapping(value = "/logout", method = RequestMethod.GET)
     public String logout(ModelMap model) {

         model.addAttribute("message",
                 "You have successfully logged off from application !");
         return "logout";

     }

     @RequestMapping(value = "/loginError", method = RequestMethod.GET)
     public String loginError(ModelMap model) {
         model.addAttribute("error", "true");
         return "login";

     }
}

