package com.briup.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
@RequestMapping("/")
	public String toLogin() {
		return "login";
	}
}
