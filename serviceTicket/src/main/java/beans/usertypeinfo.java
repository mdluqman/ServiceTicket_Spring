package beans;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({@NamedNativeQuery(name="selectusertype", query="SELECT * from usertypeinfo usertype", resultClass = usertypeinfo.class)})

public class usertypeinfo {
@Id
int userTypeId;
String typeOfUser;

public int getUserTypeId() {
	return userTypeId;
}
public void setUserTypeId(int userTypeId) {
	this.userTypeId = userTypeId;
}
public String getTypeOfUser() {
	return typeOfUser;
}
public void setTypeOfUser(String typeOfUser) {
	this.typeOfUser = typeOfUser;
}

}

