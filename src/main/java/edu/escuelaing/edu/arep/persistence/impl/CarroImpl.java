package edu.escuelaing.edu.arep.persistence.impl;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import  edu.escuelaing.edu.arep.model.*;
import edu.escuelaing.edu.arep.persistence.ServicesCarro;

/**
 * The Class CarroImpl.
 */
public class CarroImpl implements ServicesCarro {
	
	/** The nueva conexion. */
	ConexionBD nuevaConexion=null;
	
	/**
	 * Instantiates a new carro impl.
	 *
	 * @param nuevaConexion the nueva conexion
	 */
	public CarroImpl(ConexionBD nuevaConexion){
		this.nuevaConexion=nuevaConexion;
	}
	
	/**
	 * Gets the carros.
	 *
	 * @return the carros
	 */
	public ArrayList<Carro> getCarros()
	   {
			ArrayList<Carro> listaCarroes=new ArrayList<Carro>();
	      try
	      {
	    	  Connection conexion = nuevaConexion.getConnection();
	         Statement st = conexion.createStatement();
	         ResultSet rs = st.executeQuery("select * from carro" );
	         while (rs.next())
	         {
	        	 Carro Carro = new Carro();
	        	 Carro.setId(rs.getInt("id"));
	        	 Carro.setNombre(rs.getString("nombre"));
	        	 Carro.setMarca(rs.getString("marca"));
	        	 Carro.setModelo(rs.getString("modelo"));
	        	 listaCarroes.add(Carro);
	         }
	         rs.close();
	         st.close();
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }
	      return listaCarroes;
	   }
}
