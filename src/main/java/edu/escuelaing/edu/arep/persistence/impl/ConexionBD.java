package edu.escuelaing.edu.arep.persistence.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.escuelaing.edu.arep.model.Data;

/**
 * The Class ConexionBD.
 */
public class ConexionBD {
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new conexion BD.
	 *
	 * @throws SQLException the SQL exception
	 */
	public ConexionBD() throws SQLException {
		// logBd();
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Sets the connection.
	 *
	 * @param connection the new connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Log bd.
	 *
	 * @param datosBD the datos BD
	 * @throws SQLException the SQL exception
	 */
	public void logBd(Data datosBD) throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(datosBD.getBaseDeDatos(), datosBD.getUser(), datosBD.getClave());
		} catch (SQLException ex) {
			throw new SQLException(ex);
		} catch (ClassNotFoundException ex) {
			throw new ClassCastException(ex.getMessage());
		}

	}

}
