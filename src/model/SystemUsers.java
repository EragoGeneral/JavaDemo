package model;

/**
 * SystemUsers entity. @author MyEclipse Persistence Tools
 */

public class SystemUsers implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userFirstname;
	private String userLastname;
	private String userPhone;
	private String userEmail;

	// Constructors

	/** default constructor */
	public SystemUsers() {
	}

	/** full constructor */
	public SystemUsers(String userFirstname, String userLastname,
			String userPhone, String userEmail) {
		this.userFirstname = userFirstname;
		this.userLastname = userLastname;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserFirstname() {
		return this.userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return this.userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}