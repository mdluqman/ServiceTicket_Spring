package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import beans.EndUserBean;
import beans.ServiceEngineerBean;
import businessLogic.ServiceEngineerFunctions;

@RestController
@RequestMapping(value = "/ServiceEngineer")
public class ServiceEngineerRestController {

	@Autowired
	ServiceEngineerFunctions seb;
	
	
	@RequestMapping(value = "/getsetickets", method = RequestMethod.POST)
	public List<EndUserBean> getsetickets(@RequestBody ServiceEngineerBean eub) {
		return seb.getsetickets(eub);
	}
	
	@RequestMapping(value = "/ReportperS", method = RequestMethod.GET)
	public List<String> ReportperS() {
		return seb.ReportperS();
	}
	
	@RequestMapping(value = "/ReportperSE", method = RequestMethod.GET)
	public List<String> ReportperSE() {
		return seb.ReportperSE();
	}
	
	@RequestMapping(value = "/avgage", method = RequestMethod.POST)
	public List<String> avgage(@RequestBody ServiceEngineerBean eub) {
		return seb.avgage(eub);
	}
	
	@RequestMapping(value = "/ChangeStat", method = RequestMethod.POST)
	public int ChangeStat(@RequestBody EndUserBean eub) {
		return seb.ChangeStat(eub);
	}
	
	@RequestMapping(value = "/ChangePriority", method = RequestMethod.POST)
	public int ChangePriority(@RequestBody EndUserBean eub) {
		return seb.ChangePriority(eub);
	}
}
