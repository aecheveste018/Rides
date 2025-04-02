package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tarjeta {
	@Id
	private int nTarjeta;
	private int cvv;
	private String caducidad;
	private String dueno;
	private double cantidad;
	public Tarjeta(int nTarjeta, int cvv, String caducidad, String dueno, double cantidad) {
		this.nTarjeta=nTarjeta;
		this.cvv=cvv;
		this.caducidad = caducidad;
		this.dueno=dueno;
		this.cantidad=cantidad;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public int getnTarjeta() {
		return nTarjeta;
	}
	public void setnTarjeta(int nTarjeta) {
		this.nTarjeta = nTarjeta;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getCaducidad() {
		return caducidad;
	}
	public void setCaducidad(String caducidad) {
		this.caducidad = caducidad;
	}
	public String getDueno() {
		return dueno;
	}
	public void setDueno(String dueno) {
		this.dueno = dueno;
	}
			
			
}
