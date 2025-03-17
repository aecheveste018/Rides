package domain;

import javax.persistence.*;
@Entity
public class Credentials {
	@Id
	private String mail;
	private String password;
	//D => Driver | P => Passenger
	private Character Tipe;
	
	public Credentials(String pMail, String pPassword, Character pTipe) {
		
		mail = pMail;
		password = pPassword;
		Tipe = pTipe;
		
	}
	public String getmail() {
		return mail;
	}
	public void setUser(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Character getTipe() {
		return Tipe;
	}
	public void setTipe(Character tipe) {
		Tipe = tipe;
	}
}
