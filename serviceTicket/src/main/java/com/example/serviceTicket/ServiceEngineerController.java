package com.example.serviceTicket;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import Beans.EndUserBean;
import Beans.ServiceEngineerBean;
import Beans.UserBean;
import Beans.deptInfo;

@Controller
public class ServiceEngineerController {

	@Autowired
	org.springframework.core.env.Environment environment;

	/*
	 * this is used by serviceEngineer to view all of his tickets(ie.tickets assigned to him)
	 * he can not just view but also respond to a ticket by changing the desired tickets status or priority
	 * */
	
	@RequestMapping(value = "/ViewandRespond")
	public ModelAndView getsetickets(HttpSession session) {
		String port = environment.getProperty("local.server.port");
		UserBean u = new UserBean();
		u.setUsername((String)session.getAttribute("username"));
		ServiceEngineerBean seb = new ServiceEngineerBean();
		seb.setSEusername(u);
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:"+port+"/ServiceEngineer/getsetickets";
		HttpEntity<ServiceEngineerBean> requestEntity = new HttpEntity<>(seb);
		ResponseEntity<List<EndUserBean>> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<List<EndUserBean>>() {
				});
		List<EndUserBean> l = response.getBody();
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput");
		if(l.size()==0)
		{
			mv.addObject("ch",2);
		}
		else
		{
		mv.addObject("ch",1);
		}
		mv.addObject("tickets", l);
		return mv;
	}
	
	
	/*
	 * this guides to the functions that help the ServiceEngineer to change a tickets status by that 
	 * the change will be reflected in the EndUserBean table (tickets table) and also the ServiceEngineer table
	 * if any changes required*/
	@RequestMapping(value = "/ChangeStat")
	public ModelAndView ChangeStat(EndUserBean eub) {
		String port = environment.getProperty("local.server.port");
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:"+port+"/ServiceEngineer/ChangeStat";
		int x = restTemplate.postForObject(uri, eub, Integer.class);
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
		mv.addObject("ch",x);
		return mv;
	}
	
	/*
	 * this guides to the functions that help the ServiceEngineer to change a tickets priority by that 
	 * the change will be reflected in the EndUserBean table (tickets table) and also the ServiceEngineer table
	 * if any changes required*/
	@RequestMapping(value = "/ChangePriority")
	public ModelAndView ChangePriority(EndUserBean eub) {
		String port = environment.getProperty("local.server.port");
		System.out.println("hi controller");
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:"+port+"/ServiceEngineer/ChangePriority";
		int x = restTemplate.postForObject(uri, eub, Integer.class);
		System.out.println(x+" back in controller");
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
		mv.addObject("ch",x);
		return mv;
		
	}
	
	/*
	 * this will call function that will return the statistics of ticket in terms of days based on their priority
	 * by stats i mean, itll return how much time was taken by the ServiceEngineers to resolve 
	 * tickets of priority high and medium and low
	 * */
	@RequestMapping(value = "/ReportperS")
	public ModelAndView ReportperS() {
		RestTemplate restTemplate = new RestTemplate();
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:"+port+"/ServiceEngineer/ReportperS";
		ResponseEntity<List<String>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<String>>() {
				});
		List<String> sev = response.getBody();
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
		mv.addObject("ch",4);
		mv.addObject("severity", sev);
		return mv;
	}
	
	
	/*
	 * this will call function that will return the statistics of ticket in terms of days based on their each serviceEngineer
	 * by stats i mean, itll return how much time was taken by the particular ServiceEngineer to resolve 
	 * tickets assigned to him(Only for Closed/Completed tickets)
	 * */
	@RequestMapping(value = "/ReportperSE")
	public ModelAndView ReportperSE() {
		RestTemplate restTemplate = new RestTemplate();
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:"+port+"/ServiceEngineer/ReportperSE";
		ResponseEntity<List<String>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<String>>() {
				});
		List<String> sev = response.getBody();
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
		mv.addObject("ch",5);
		mv.addObject("service", sev);
		return mv;
	}
	
	
	/*
	 * it gives the time in terms of days taken by an open ticket from the day it was issued to current date
	 * */
	@RequestMapping(value = "/avgage")
	public ModelAndView avgage(HttpSession session) {
		String port = environment.getProperty("local.server.port");
		UserBean u = new UserBean();
		u.setUsername((String)session.getAttribute("username"));
		ServiceEngineerBean seb = new ServiceEngineerBean();
		seb.setSEusername(u);
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:"+port+"/ServiceEngineer/avgage";
		HttpEntity<ServiceEngineerBean> requestEntity = new HttpEntity<>(seb);
		ResponseEntity<List<String>> response = restTemplate.exchange(uri, HttpMethod.POST,requestEntity,
				new ParameterizedTypeReference<List<String>>() {
				});
		List<String> sev = response.getBody();
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
		if(sev.size()==0)
		{
		mv.addObject("ch",11);
		return mv;
		}
		else
		{
			mv.addObject("ch",6);
			mv.addObject("tickets", sev);
			return mv;
		}
	}
	
}
