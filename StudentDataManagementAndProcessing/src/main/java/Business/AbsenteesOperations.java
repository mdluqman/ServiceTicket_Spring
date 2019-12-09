package Business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Entities.Absentees;
import Service.AbsenteesService;

public class AbsenteesOperations {
	AbsenteesService absenteesService = new AbsenteesService();

	public String insert(List<Absentees> absenteeList) {
		return absenteesService.insert(absenteeList);
	}
	
	public List<Absentees> search(String date) throws ParseException {		
		return absenteesService.search(new SimpleDateFormat("dd/MM/yy").format(new SimpleDateFormat("yyyy-MM-dd").parse(date)));
	}
	public List<Absentees> searchstud(String date,String username) throws ParseException {		
		return absenteesService.searchstud(new SimpleDateFormat("dd/MM/yy").format(new SimpleDateFormat("yyyy-MM-dd").parse(date)),username);
	}

}
