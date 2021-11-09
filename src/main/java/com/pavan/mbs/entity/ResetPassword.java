package com.pavan.mbs.entity;

public class ResetPassword {
	private String existingPassword;
	private String newPassword;
	private String confirmNewPassword;
	public String getExistingPassword() {
		return existingPassword;
	}
	public void setExistingPassword(String existingPassword) {
		this.existingPassword = existingPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	@Override
	public String toString() {
		return "ResetPassword [existingPassword=" + existingPassword + ", newPassword=" + newPassword
				+ ", confirmNewPassword=" + confirmNewPassword + "]";
	}
	
	
}
