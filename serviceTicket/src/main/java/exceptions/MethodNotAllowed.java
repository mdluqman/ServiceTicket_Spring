package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public class MethodNotAllowed extends RuntimeException{

		private static final long serialVersionUID = 1L;

		public MethodNotAllowed(String exception) {
			super(exception);
		}
	}


