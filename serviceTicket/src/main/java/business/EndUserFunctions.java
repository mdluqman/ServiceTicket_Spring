package business;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import beans.EndUserBean;
import beans.deptInfo;
import services.EndUserServices;

@Component
public class EndUserFunctions {

	java.util.Date date = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	@Autowired
	EndUserServices enduserservice = new EndUserServices();

	public List<deptInfo> getdept() {
		return enduserservice.getdept();
	}

	public int verify(EndUserBean eub) {
		boolean b = redv(eub);
		if (b) {
			int v = 0;
			String tid;
			do {
				tid = gen();
				v = verifytid(tid);
			} while (v != 1);
			eub.setTicketId(tid);
			eub.setTicketStatus("New");
			int x = enduserservice.raiseticket(eub);
			return x;
		} else
			return 8;
	}

	private int verifytid(String tid) {
		// TODO Auto-generated method stub
		int v = enduserservice.verifytid(tid);
		return v;
	}

	public boolean redv(EndUserBean enduser) {
		java.util.Date date = new java.util.Date();
		java.sql.Date now = new java.sql.Date(date.getTime());
		java.util.Date cd = null;
		try {
			cd = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(enduser.getRequestedEndDAte());
//			cd = new SimpleDateFormat("yyyy-MM-dd").parse(enduser.getRequestedEndDAte());
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
		return enduserservice.gettickets(eub);
	}
}
