package edu.escuelaing.edu.arep.persistence.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.escuelaing.edu.arep.model.Data;

public class ConexionBD {
	
	private Connection connection;

	public ConexionBD() throws SQLException {
		// logBd();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

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
