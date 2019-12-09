package embedded;

import java.io.Serializable;

public class MarksEmbeddedKey implements Serializable{

	private static final long serialVersionUID = 1L;
	private String username;
	private int subject_ID;
	public MarksEmbeddedKey() {
		
	}

	public MarksEmbeddedKey(String username, int subject_ID) {
		super();
		this.username = username;
		this.subject_ID = subject_ID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		MarksEmbeddedKey other = (MarksEmbeddedKey) obj;
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
