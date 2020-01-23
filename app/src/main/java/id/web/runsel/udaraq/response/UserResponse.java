package id.web.runsel.udaraq.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

	@SerializedName("message")
	@Expose
	private String message;
	@SerializedName("token")
	@Expose
	private String token;
	@SerializedName("errors")
	@Expose
	private Errors errors;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

}