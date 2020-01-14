package id.web.runsel.udaraq.adapter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors {

	@SerializedName("email")
	@Expose
	private List<String> email = null;
	@SerializedName("password")
	@Expose
	private List<String> password = null;

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public List<String> getPassword() {
		return password;
	}

	public void setPassword(List<String> password) {
		this.password = password;
	}

}