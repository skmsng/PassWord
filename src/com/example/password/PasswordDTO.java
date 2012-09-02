package com.example.password;

public class PasswordDTO {
	private int _id;
	private String category;
	private String title;
	private String user;
	private String pass;
	private String memo;

	
	public PasswordDTO(int _id, String category, String title, String user,
			String pass, String memo) {
		this._id = _id;
		this.category = category;
		this.title = title;
		this.user = user;
		this.pass = pass;
		this.memo = memo;
	}
	/**
	 * @return _id
	 */
	public int get_id() {
		return _id;
	}
	/**
	 * @param _id セットする _id
	 */
	public void set_id(int _id) {
		this._id = _id;
	}
	/**
	 * @return category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category セットする category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user セットする user
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass セットする pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo セットする memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

}
