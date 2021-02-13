package edu.escuelaing.arep;

import org.apache.commons.io.FilenameUtils;

import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class HttpServer {
	
	
	
	
	static PrintWriter out;
	static BufferedReader in;
	
	
  public static void main(String[] args) throws IOException {
	  Socket clientSocket = null;
	  ServerSocket serverSocket = null;

	   
	   try { 
	      serverSocket = new ServerSocket(getPort());
	   } catch (IOException e) {
	     System.err.println("No es posible escuchar el puerto: 35000.");
	      System.exit(1);
	   }
	   while(true) {
		   
		   try {
		       System.out.println("Listo para recibir ...");
		       clientSocket = serverSocket.accept();
		   } catch (IOException e) {
		       System.err.println("Accept failed.");
		       System.exit(1);
		   }
		   
		   out = new PrintWriter(clientSocket.getOutputStream(), true);
		   in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		   String inputLine, outputLine;
		   
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
	                  System.out.println("Ruta del recurso al que se quiere acceder: "+path+req);
	                  Solicitud(req,clientSocket,path);
	              }
		    	  break; 
		      }
		   }
		  
		    out.close(); 
		    in.close(); 
		    clientSocket.close(); 
		    
	   }
  }
  
  public static void Solicitud(String req,Socket clientSocket,String path) throws IOException {
	  
      String extension = FilenameUtils.getExtension(req);
      System.out.println("Extencion: "+extension);
      
      File file = new File(path+req);
      
      try{
	      if (extension.equals("jpg") || extension.equals("png")) {
	    	  getImagen(extension,clientSocket,file);
	      } else {
	    	  out.println("HTTP/1.1 200 encontrado \r\nContent-Type: text/html\r\n\r\n");
	    	  BufferedReader br = new BufferedReader(new FileReader(file));
	    	  StringBuilder stringBuilder = new StringBuilder();
	          String string;
	          while ((string= br.readLine()) != null) {
	              stringBuilder.append(string);
	          }
	          out.println(stringBuilder.toString());
	          br.close();
	      }
      } catch (FileNotFoundException e){
          out = new PrintWriter(clientSocket.getOutputStream(), true);
          out.println("HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n" +
                  "<html><head><title>404</title></head><body><h1>Error 404 no se encontro la pagina</h1></body></html>");
      }
  }
  public static void getImagen(String extension,Socket clientSocket,File file) throws IOException {
      try {
    		FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			DataOutputStream binaryOut = new DataOutputStream(clientSocket.getOutputStream());
			binaryOut.writeBytes("HTTP/1.0 200 encontrado OK\r\n");
			binaryOut.writeBytes("Content-Type: image/"+extension+"\r\n");
			binaryOut.writeBytes("Content-Length: " + data.length);
			binaryOut.writeBytes("\r\n\r\n");
			binaryOut.write(data);

			binaryOut.close();
      }  catch (FileNotFoundException e){
          out = new PrintWriter(clientSocket.getOutputStream(), true);
          out.println("HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n" +
                  "<html><head><title>404</title></head><body><h1>Error 404 no se encontro la imagen</h1></body></html>");
      }
  }
  static int getPort() {
      if (System.getenv("PORT") != null) {
          return Integer.parseInt(System.getenv("PORT"));
      }        
      return 4567;
  }
}