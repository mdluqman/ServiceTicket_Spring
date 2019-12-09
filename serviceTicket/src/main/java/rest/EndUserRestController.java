 package rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import beans.EndUserBean;
import beans.deptInfo;
import business.EndUserFunctions;

@CrossOrigin
@RestController
@RequestMapping(value = "/EndUser")
public class EndUserRestController {
	@Autowired
	EndUserFunctions enduserfunction;

	@RequestMapping(value = "/getdept", method = RequestMethod.GET)
	public List<deptInfo> sample() {
		List<deptInfo> l = enduserfunction.getdept();
		return l;
	}

	@RequestMapping(value = "/gettickets", method = RequestMethod.POST)
	public List<EndUserBean> sample1(@RequestBody EndUserBean eub) {
		List<EndUserBean> l = enduserfunction.gettickets(eub);
		return l;
	}

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public int verify(@RequestBody EndUserBean eub) {
		return enduserfunction.verify(eub);
	}
}
