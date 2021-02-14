package edu.escuelaing.arep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import edu.escuelaing.edu.arep.model.Carro;
import edu.escuelaing.edu.arep.persistence.impl.CarroImpl;
import edu.escuelaing.edu.arep.persistence.impl.ConexionBD;

public class Archivo implements Response{
	private PrintWriter out;
	private ConexionBD nuevaConexion;
	
	
	public Archivo(PrintWriter out, ConexionBD nuevaConexion) {
		this.out = out;
		this.nuevaConexion = nuevaConexion;
	}
	
	@Override
	public void SolicitudArchivo(File file, Socket clientSocket,String req) throws IOException {
		try {
			if (req.equals("DescripcionCarros.html")) {
				 hacerTablaCarro();
		      }else {
			out.println("HTTP/1.1 200 encontrado \r\nContent-Type: text/html\r\n\r\n");
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder stringBuilder = new StringBuilder();
			String string;
			while ((string = br.readLine()) != null) {
				stringBuilder.append(string);
			}
			out.println(stringBuilder.toString());
			br.close();
		      }
		} catch (FileNotFoundException e) {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println("HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n"
					+ "<html><head><title>404</title></head><body><h1>Error 404 no se encontro la pagina</h1></body></html>");
		}
	}
	public void hacerTablaCarro(){
		 out.println("HTTP/1.1 200 \r\nContent-Type: html solicitud a base de datos\r\n\r\n");
		 String outputLine = 
		          "<!DOCTYPE html>" + 
		          "<html>" + 
		          "<head>" + 
		          "<meta charset=\"UTF-8\">" + 
		          "<title>Base de Datos</title>\n" + 
		          "</head>" + 
		          "<body>" + "<center>"+
		          "<h1>Descripcion de carros</h1>" + "</center>"+
		          "<table border=\"1\" style=\"margin: 0 auto;\">"+
		          "<tr>"+
		          "<td>Id</td>"+
		          "<td>Nombre</td>"+
		          "<td>Marca</td>"+
		          "<td>Modelo</td>"+
		          "</tr>";
		  		CarroImpl carro=new CarroImpl(nuevaConexion);
		          ArrayList<Carro> lista = carro.getCarros();
		         // System.out.println("AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  ->"+lista.size());
		          for (int i=0;i<lista.size();i++)
		          {
		             outputLine+= "<tr>"+"<td>"+lista.get(i).getId()+"</td>"+
								  "<td>"+lista.get(i).getNombre()+"</td>"+
								 "<td>"+lista.get(i).getMarca()+"</td>"+
								 "<td>"+lista.get(i).getModelo()+"</td>"+
							     "</tr>";
		          }
		          outputLine+= "</table>"+"</body>" + "</html>"; 
		    out.println(outputLine);
	}

}
