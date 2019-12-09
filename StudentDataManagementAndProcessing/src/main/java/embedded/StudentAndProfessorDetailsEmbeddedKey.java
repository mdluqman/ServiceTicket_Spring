package embedded;

import java.io.Serializable;


public class StudentAndProfessorDetailsEmbeddedKey implements Serializable{

	private static final long serialVersionUID = 1L;
	private int branch_ID;
	private String username;
	private int subject_ID;
	private String section;
	
	public StudentAndProfessorDetailsEmbeddedKey()
	{
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branch_ID;
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		result = prime * result + subject_ID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentAndProfessorDetailsEmbeddedKey other = (StudentAndProfessorDetailsEmbeddedKey) obj;
		if (branch_ID != other.branch_ID)
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		if (subject_ID != other.subject_ID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
}
