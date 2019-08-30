package BusinessClasses;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BeanClasses.EndUserBean;
import BeanClasses.UserBean;
import BeanClasses.deptInfo;
import DaoClasses.EndUserDao;

@Component
public class EndUserBusiness {

	java.util.Date date = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	
	
	@Autowired
	EndUserDao eud = new EndUserDao();

	public List<deptInfo> getdept() {
		System.out.println("in b");
		return eud.getdept();
	}

	public int verify(EndUserBean eub) {
		// TODO Auto-generated method stub
		boolean b = redv(eub);
		System.out.println(b);
		if(b)
		{
			eub.setTicketId(gen());
			eub.setTicketStatus("New");
			int x = eud.raiseticket(eub);
			return x;
		}
		else
			return 8;
	}

	public boolean redv(EndUserBean enduser) {
		// TODO Auto-generated method stub
		java.util.Date date = new java.util.Date();
		java.sql.Date now = new java.sql.Date(date.getTime());
		java.util.Date cd = null;
		try {
			cd = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(enduser.getRequestedEndDAte());
			if (cd.getYear() > now.getYear()) {
				return true;
			} else if (cd.getYear() == now.getYear()) {
				if (cd.getMonth() > now.getMonth()) {
					return true;
				} else if (cd.getMonth() == now.getMonth()) {
					if (cd.getDate() >= now.getDate()) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String gen() {
		String x;
		final Random RANDOM = new Random();
		String digits = "0123456789";
		StringBuilder returnValue = new StringBuilder(5);
		String v = "TKTID";
		for (int i = 0; i < 5; i++) {
			returnValue.append(digits.charAt(RANDOM.nextInt(digits.length())));
		}
		x = returnValue.toString();
		x = v + x;
		return x;
	}

	public List<EndUserBean> gettickets(EndUserBean eub) {
		// TODO Auto-generated method stub
		return eud.gettickets(eub);
	}
}
