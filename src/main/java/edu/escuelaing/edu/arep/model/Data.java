package edu.escuelaing.edu.arep.model;

/**
 * The Class Data.
 */
public class Data {
	
	/** The user. */
	private  String user = null;
	
	/** The clave. */
	private  String clave = null;
    
    /** The base de datos. */
    private  String baseDeDatos = null;
    
    /**
     * Instantiates a new data.
     *
     * @param user the user
     * @param clave the clave
     * @param baseDeDatos the base de datos
     */
    public Data(String user, String clave, String baseDeDatos) {
		this.user = user;
		this.clave = clave;
		this.baseDeDatos = baseDeDatos;
	}
    
    /**
     * Gets the user.
     *
     * @return the user
     */
    public String getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(String user) {  
		this.user = user;
	}
	
	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	
	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	/**
	 * Gets the base de datos.
	 *
	 * @return the base de datos
	 */
	public String getBaseDeDatos() {
		return baseDeDatos;
	}
	
	/**
	 * Sets the base de datos.
	 *
	 * @param baseDeDatos the new base de datos
	 */
	public void setBaseDeDatos(String baseDeDatos) {
		this.baseDeDatos = baseDeDatos;
	}
	
}
