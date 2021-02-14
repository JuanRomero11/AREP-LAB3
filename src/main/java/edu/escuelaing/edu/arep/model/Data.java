package edu.escuelaing.edu.arep.model;

public class Data {
	private  String user = null;
	private  String clave = null;
    private  String baseDeDatos = null;
    public Data(String user, String clave, String baseDeDatos) {
		this.user = user;
		this.clave = clave;
		this.baseDeDatos = baseDeDatos;
	}
    public String getUser() {
		return user;
	}
	public void setUser(String user) {  
		this.user = user;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getBaseDeDatos() {
		return baseDeDatos;
	}
	public void setBaseDeDatos(String baseDeDatos) {
		this.baseDeDatos = baseDeDatos;
	}
	
}
