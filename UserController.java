package com.user_controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.user_dao.UserDao;
import com.user_dto.User;

@Controller
public class UserController {
	
	@Autowired
	UserDao udao;
	
	@RequestMapping("/registration")
	public ModelAndView getRegister() {
		User u= new User();
		ModelAndView mav= new ModelAndView();
		mav.addObject("userobj", u);
		mav.setViewName("userform");
		return mav;
	}
	
	@RequestMapping("/adduser") 
	public ModelAndView addUser(@ModelAttribute("userobj") User u) {
		udao.addUser(u);
		ModelAndView mav= new ModelAndView();
	
		mav.setViewName("options");
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView loginUser() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("userloginform");
		return mav;
	}
	
	@RequestMapping("/validateuser")
	public ModelAndView validateUser(ServletRequest req,HttpSession session) {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		ModelAndView mav= new ModelAndView();
		User u= udao.validateUser(email, password);
		
		if(u!=null) {
			mav.addObject("msg", "WELCOME!");
			session.setAttribute("userinfo", u);
			mav.setViewName("userwelcome");
			return mav;
		}
		else {
			mav.addObject("msg", "Invalid Credentials");
			mav.setViewName("userloginform");
			return mav;
		}
		
	}
	
	@RequestMapping("/create")
	public ModelAndView createAccount() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect://registration");
		return mav;
	}
	
	@RequestMapping("/viewuser")
	public ModelAndView viewUser() {
		List<User> user= udao.fetchUser();
		ModelAndView mav= new ModelAndView();
		mav.addObject("userlist", user);
		mav.setViewName("usertable");
		return mav;
	}
	
	@RequestMapping("/changepassword")
	public ModelAndView changePassword() {
		ModelAndView mav= new ModelAndView();
		mav.setViewName("passwordform");
		return mav;
		
	}
	
	@RequestMapping("/password")
	public ModelAndView reset(ServletRequest req,HttpSession session) {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String newPassword=req.getParameter("newpassword");
		
		String check=udao.setPassword(email, password, newPassword);
		
		ModelAndView mav= new ModelAndView();
		if(check=="Yes") {
		mav.addObject("msg", "Password Updated");
		mav.setViewName("userwelcome");
		}else {
			mav.addObject("msg","Invalid EmailId or Password");
			mav.setViewName("passwordform");
		}
		return mav;
		
		}
		
	

}
