package edu.mum.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ModelAndView accessDenied(){
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("error","Sorry! Error is Occured");
		mav.setViewName("error/error");
		return mav;
	}
	
}
