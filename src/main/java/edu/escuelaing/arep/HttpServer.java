package edu.escuelaing.arep;

import org.apache.commons.io.FilenameUtils;

import edu.escuelaing.edu.arep.model.Data;
import edu.escuelaing.edu.arep.persistence.impl.ConexionBD;

import java.net.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
 * The Class HttpServer.
 */
/**
 * @author Juan Romero
 *
 */
public class HttpServer {
	
	/** The out. */
	static PrintWriter out;
	
	/** The in. */
	static BufferedReader in;
	
	/** The nueva conexion. */
	static ConexionBD nuevaConexion;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		Data nuevaData=new Data("dxwdscxwcxwvsr",
				"9c3f69bda7ee9a5deff34defbc961f3beee8f969bcc7482b0209c7f4ad531e56",
				"jdbc:postgresql://ec2-100-24-139-146.compute-1.amazonaws.com:5432/da7l6g9mja86le?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
		try {
			nuevaConexion=new ConexionBD();
			nuevaConexion.logBd(nuevaData);
			System.out.println("conexion con la base de datos exitosa.");
		} catch (SQLException e1) {
			System.err.println("No es posible conectarse a la base de datos");
			e1.printStackTrace();
		}
		try {
			serverSocket = new ServerSocket(getPort());
		} catch (IOException e) {
			System.err.println("No es posible escuchar el puerto: 4567.");
			System.exit(1);
		}
		while (true) {
			try {
				System.out.println("Listo para recibir ...");
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String inputLine;
			StringBuilder stringBuilder = new StringBuilder();
			Pattern pattern = Pattern.compile("GET /([^\\s]+)");
			Matcher matcher = null;
			String path = "src/main/resources/";
			while ((inputLine = in.readLine()) != null) {
				System.out.println("Recibi: " + inputLine);
				stringBuilder.append(inputLine);
				if (!in.ready()) {
					matcher = pattern.matcher(stringBuilder.toString());
					if (matcher.find()) {
						String req = matcher.group().substring(5);
						System.out.println("VALUE: " + req);
						System.out.println("Ruta del recurso al que se quiere acceder: " + path + req);
						Solicitud(req, clientSocket, path);
					}
					break;
				}
			}
			out.close();
			in.close();
			clientSocket.close();

		}
	}

	/**
	 * Solicitud.
	 *
	 * @param req the req
	 * @param clientSocket the client socket
	 * @param path the path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void Solicitud(String req, Socket clientSocket, String path) throws IOException {

		String extension = FilenameUtils.getExtension(req);
		System.out.println("Extencion: " + extension);
		File file = new File(path + req);
		Response archivo;
		Response imagen;
			if(req.startsWith("funcionLambda")) {
				getFunctionLambda(req);
			 } 
			else if (extension.equals("jpg") || extension.equals("png")) {
				imagen =new Imagen(out);
				imagen.SolicitudArchivo(file, clientSocket, extension);
			}else {
				archivo =new Archivo(out,nuevaConexion);
				archivo.SolicitudArchivo(file, clientSocket, req);
			}
		
	}
	
	
	/**
	 * Gets the function lambda.
	 *
	 * @param req the req
	 * @return the function lambda
	 */
	public static void getFunctionLambda(String req) {
		 String[] list = req.split("\\?");
		  String valor = list[1];
		  out.println("HTTP/1.1 200 \r\nContent-Type: text/html\r\n\r\n");
		  lambda expresion = (param) -> { 
	    	  out.println(
	    	  "<!DOCTYPE html>"+
	    	  "<html>"+
	    	  	"<head>"+
	    	  		"<title>FUNCTIONLAMBDA</title>"+
	    	  	"</head>"+
	    	  	"<body>"+
	    	  		"<h1> Bienvenido! </h1>"+"<h2>A continuacion le mostrare el valor ingresado en la funcion</h2>"+
	    	  	"<h3>"+param+"</h3>"+
	    	  	"</body>"+
	    	  "</html>" );
	      };
	      expresion.get(valor);
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567;
	}
}