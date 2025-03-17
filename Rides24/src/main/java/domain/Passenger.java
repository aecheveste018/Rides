package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Passenger implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String email;
	private String name; 
	private String[] psw;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Ride> reservas =new Vector<Ride>();



	public Passenger() {
		super();
	}

	public Passenger(String email, String name) {
		this.email = email;
		this.name = name;
		this.psw = new String[email.length()];
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public  String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPsw(String mail, String psw) {
		this.psw[mail.indexOf("@")]=psw;
	}
	
	public String getPsw(String email) {
		return this.psw[email.indexOf("@")];
	}
	
	public String toString(){
		return email + ";" + name;
	}
	public List<Ride> getReservas() {
		return reservas;
	}
	public void setReservas(List<Ride> reservas) {
		this.reservas = reservas;
	}
	

}
