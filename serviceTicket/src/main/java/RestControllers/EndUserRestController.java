package RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Beans.EndUserBean;
import Beans.UserBean;
import Beans.deptInfo;
import BusinessClasses.EndUserBusiness;

@RestController
@RequestMapping(value = "/EndUser")
public class EndUserRestController {

	@Autowired
	EndUserBusiness eb;
	
	@RequestMapping(value = "/getdept", method = RequestMethod.GET)
	public List<deptInfo> sample() {
		List<deptInfo> l=eb.getdept();
		return l;
	}
	
	@RequestMapping(value = "/gettickets", method = RequestMethod.POST)
	public List<EndUserBean> sample1(@RequestBody EndUserBean eub) {
		List<EndUserBean> l = eb.gettickets(eub);
		return l;
	}
	
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public int verify(@RequestBody EndUserBean eub) {
		return eb.verify(eub);
	}
}
