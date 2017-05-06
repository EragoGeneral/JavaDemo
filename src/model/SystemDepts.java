package model;

/**
 * SystemDepts entity. @author MyEclipse Persistence Tools
 */

public class SystemDepts implements java.io.Serializable {

	// Fields

	private Integer sysDeptId;
	private String sysDeptCode;
	private String sysDeptName;
	private String sysUrl;
	private Integer sysParentId;
	private String sysDeptPath;

	// Constructors

	/** default constructor */
	public SystemDepts() {
	}

	/** full constructor */
	public SystemDepts(String sysDeptCode, String sysDeptName, String sysUrl,
			Integer sysParentId, String sysDeptPath) {
		this.sysDeptCode = sysDeptCode;
		this.sysDeptName = sysDeptName;
		this.sysUrl = sysUrl;
		this.sysParentId = sysParentId;
		this.sysDeptPath = sysDeptPath;
	}

	// Property accessors

	public Integer getSysDeptId() {
		return this.sysDeptId;
	}

	public void setSysDeptId(Integer sysDeptId) {
		this.sysDeptId = sysDeptId;
	}

	public String getSysDeptCode() {
		return this.sysDeptCode;
	}

	public void setSysDeptCode(String sysDeptCode) {
		this.sysDeptCode = sysDeptCode;
	}

	public String getSysDeptName() {
		return this.sysDeptName;
	}

	public void setSysDeptName(String sysDeptName) {
		this.sysDeptName = sysDeptName;
	}

	public String getSysUrl() {
		return this.sysUrl;
	}

	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}

	public Integer getSysParentId() {
		return this.sysParentId;
	}

	public void setSysParentId(Integer sysParentId) {
		this.sysParentId = sysParentId;
	}

	public String getSysDeptPath() {
		return this.sysDeptPath;
	}

	public void setSysDeptPath(String sysDeptPath) {
		this.sysDeptPath = sysDeptPath;
	}

}