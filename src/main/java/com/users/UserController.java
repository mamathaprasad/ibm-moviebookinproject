package com.users;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repo;
		
	@RequestMapping(method = RequestMethod.POST, value = "/register/users")
	void register(@RequestBody User user) {
		service.register(user);;
		System.out.println("successfully created your account with id"+user.getUserId());
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
	void updateUser(@RequestBody User user) {
		service.updateUser(user);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
	void deleteUser(@PathVariable int id) {
		service.deleteUser(id);
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/login/{email}/{password}")
	public String login(@PathVariable String email,@PathVariable String password)
	{
		User user=repo.findByEmail(email);
		if(!user.getPassword().equals(password))
		{
			System.out.println("invalid username and password");
			return "failed";
		}
		else
		{
			System.out.println("Successfully logged in");
			return "success";
		}
	}
	
	/*
	 * @RequestMapping(method=RequestMethod.POST,value="/login/{email}") public
	 * String checkuser(@PathVariable String email)
	 * 
	 * { User user=new User(); String email1=user.getEmail(); Optional<User>
	 * userCheck=repo.findByEmail(email); if(userCheck.isPresent()) { return
	 * "User already exists"; } else
	 * 
	 * { return "Go to http://localhost:8080/add/users to create account";
	 * 
	 * 
	 * }
	 * 
	 * }
	 */
		
	
	/*
	 * @RequestMapping(method=RequestMethod.POST,value="/login/{email}/{password}")
	 * public String login(@RequestParam("email")String
	 * email,@RequestParam("password") String password) { User
	 * user=repo.findByEmail(email);
	 * 
	 * if(!(user.getPassword().equals(password))) { return
	 * "oops!!!INVALID USERNAME AND PASSWORD PLEASE TRY AGAIN"; }
	 * 
	 * }
	 */
	
	
	
	
	
}
