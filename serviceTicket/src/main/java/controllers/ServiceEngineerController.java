package controllers;

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

import beans.EndUserBean;
import beans.ServiceEngineerBean;
import beans.UserBean;
import beans.deptInfo;

@Controller
public class ServiceEngineerController {
	@Autowired
	org.springframework.core.env.Environment environment;

	/*
	 * ServiceEngineer to ServiceEngineerOutput this is used by serviceEngineer to
	 * view all of his tickets(ie.tickets assigned to him) he can not just view but
	 * also respond to a ticket by changing the desired tickets status or priority
	 */
	@RequestMapping(value = "/ViewandRespond")
	public ModelAndView getsetickets(HttpSession session) {
		String port = environment.getProperty("local.server.port");
		UserBean u = new UserBean();
		u.setUsername((String) session.getAttribute("username"));
		ServiceEngineerBean seb = new ServiceEngineerBean();
		seb.setSEusername(u);
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:" + port + "/ServiceEngineer/getsetickets";
		HttpEntity<UserBean> requestEntity = new HttpEntity<>(u);
		ResponseEntity<List<EndUserBean>> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<List<EndUserBean>>() {
				});
		List<EndUserBean> l = response.getBody();
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput");
		if (l.size() == 0) {
			mv.addObject("ch", 2);
		} else {
			mv.addObject("ch", 1);
		}
		mv.addObject("tickets", l);
		return mv;
	}

	/*
	 * ServiceEngineerOutput to ServiceEngineerOutput1 this guides to the functions
	 * that help the ServiceEngineer to change a tickets status by that the change
	 * will be reflected in the EndUserBean table (tickets table) and also the
	 * ServiceEngineer table if any changes required
	 */
	@RequestMapping(value = "/ChangeticketStat")
	public ModelAndView ChangeStat(EndUserBean eub) {
		String port = environment.getProperty("local.server.port");
		RestTemplate restTemplate = new RestTemplate();
		try {
			final String uri = "http://localhost:" + port + "/ServiceEngineer/ChangeStat";
			restTemplate.put(uri, eub);
			ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
			mv.addObject("ch", 1);
			System.out.println("changestat in try");
			return mv;
		} catch (Exception e) {
			System.out.println("changestat in catch");
			ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
			mv.addObject("ch", 3);
			return mv;
		}
	}

	/*
	 * ServiceEngineerOutput to ServiceEngineerOutput1 this guides to the functions
	 * that help the ServiceEngineer to change a tickets priority by that the change
	 * will be reflected in the EndUserBean table (tickets table) and also the
	 * ServiceEngineer table if any changes required
	 */
	@RequestMapping(value = "/ChangeticketPriority")
	public ModelAndView ChangePriority(EndUserBean eub) {
		String port = environment.getProperty("local.server.port");
		RestTemplate restTemplate = new RestTemplate();
		try {
			final String uri = "http://localhost:" + port + "/ServiceEngineer/ChangePriority";
//		int x = restTemplate.postForObject(uri, eub, Integer.class);
			restTemplate.put(uri, eub);
			ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
			mv.addObject("ch", 2);
			return mv;
		} catch (Exception e) {
			ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
			mv.addObject("ch", 12);
			return mv;
		}
	}

	/*
	 * ServiceEngineerOutput to ServiceEngineerOutput1 this will call function that
	 * will return the statistics of ticket in terms of days based on their priority
	 * by stats i mean, itll return how much time was taken by the ServiceEngineers
	 * to resolve tickets of priority high and medium and low
	 */
	@RequestMapping(value = "/ReportperSeverity")
	public ModelAndView ReportperS() {
		RestTemplate restTemplate = new RestTemplate();
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:" + port + "/ServiceEngineer/ReportperS";
		ResponseEntity<List<String>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<String>>() {
				});
		List<String> sev = response.getBody();
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
		mv.addObject("ch", 4);
		mv.addObject("severity", sev);
		return mv;
	}

	/*
	 * ServiceEngineerOutput to ServiceEngineerOutput1 this will call function that
	 * will return the statistics of ticket in terms of days based on their each
	 * serviceEngineer by stats i mean, itll return how much time was taken by the
	 * particular ServiceEngineer to resolve tickets assigned to him(Only for
	 * Closed/Completed tickets)
	 */
	@RequestMapping(value = "/ReportperSE")
	public ModelAndView ReportperSE() {
		RestTemplate restTemplate = new RestTemplate();
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:" + port + "/ServiceEngineer/ReportperSE";
		ResponseEntity<List<String>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<String>>() {
				});
		List<String> sev = response.getBody();
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
		mv.addObject("ch", 5);
		mv.addObject("service", sev);
		return mv;
	}

	/*
	 * ServiceEngineerOutput to ServiceEngineerOutput1 it gives the time in terms of
	 * days taken by an open ticket from the day it was issued to current date
	 */
	@RequestMapping(value = "/openticketage")
	public ModelAndView avgage(HttpSession session) {
		String port = environment.getProperty("local.server.port");
		UserBean u = new UserBean();
		u.setUsername((String) session.getAttribute("username"));
		ServiceEngineerBean seb = new ServiceEngineerBean();
		seb.setSEusername(u);
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:" + port + "/ServiceEngineer/ticketage";
		HttpEntity<UserBean> requestEntity = new HttpEntity<>(u);
		ResponseEntity<List<String>> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<List<String>>() {
				});
		List<String> sev = response.getBody();
		ModelAndView mv = new ModelAndView("/ServiceEngineerOutput1");
		if (sev.size() == 0) {
			mv.addObject("ch", 11);
			return mv;
		} else {
			mv.addObject("ch", 6);
			mv.addObject("tickets", sev);
			return mv;
		}
	}
}
