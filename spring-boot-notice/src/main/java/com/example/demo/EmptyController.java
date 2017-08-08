package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmptyController {
	
	@Value("${application.message}")
	private String message = "hello world";
	
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	public String hello(Model model) {
		
//		model.addAttribute("message", "hello");

		
		return message;
	}
	
	@RequestMapping(value="/hello500", method=RequestMethod.GET)
	@ResponseBody
	public String hello500(Model model) throws Exception {
		
//		model.addAttribute("message", "hello");
	
		throw new Exception();
		
	}
	
	@RequestMapping(value="/hello400", method=RequestMethod.GET)
	@ResponseBody
	public String hello400(Model model, @RequestParam String parameter) throws Exception {
		
//		model.addAttribute("message", "hello");
	
		return "hello";
		
	}
	
//	@RequestMapping(value="/hello404", method=RequestMethod.GET)
//	@ResponseBody
//	public String hello404(Model model) throws Exception {
//		
////		model.addAttribute("message", "hello");
//	
//		
//		
//	}
	
}
 