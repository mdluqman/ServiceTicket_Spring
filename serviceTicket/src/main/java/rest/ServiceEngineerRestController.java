                       package rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import beans.EndUserBean;
import beans.ServiceEngineerBean;
import beans.UserBean;
import business.ServiceEngineerFunctions;

@CrossOrigin
@RestController
@RequestMapping(value = "/ServiceEngineer")
public class ServiceEngineerRestController {
	@Autowired
	ServiceEngineerFunctions serviceengineerfunction;

	@RequestMapping(value = "/getsetickets", method = RequestMethod.POST)
	public List<EndUserBean> getsetickets(@RequestBody UserBean eub) {
		List<EndUserBean> l = new ArrayList<EndUserBean>();
		l = serviceengineerfunction.getsetickets(eub);
		return l;
	}

	@RequestMapping(value = "/ReportperS", method = RequestMethod.GET)
	public List<String> ReportperS() {
		return serviceengineerfunction.ReportperS();
	}

	@RequestMapping(value = "/ReportperSE", method = RequestMethod.GET)
	public List<String> ReportperSE() {
		return serviceengineerfunction.ReportperSE();
	}

	@RequestMapping(value = "/ticketage", method = RequestMethod.POST)
	public List<String> ticketage(@RequestBody UserBean eub) {
		return serviceengineerfunction.avgage(eub);
	}

	@RequestMapping(value = "/ChangeStat", method = RequestMethod.PUT)
	public void ChangeStat(@RequestBody EndUserBean eub) {
		serviceengineerfunction.ChangeStat(eub);
	}

	@RequestMapping(value = "/ChangePriority", method = RequestMethod.PUT)
	public void ChangePriority(@RequestBody EndUserBean eub) {
		serviceengineerfunction.ChangePriority(eub);
	}
}
