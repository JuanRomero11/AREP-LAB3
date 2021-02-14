package edu.escuelaing.arep;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public interface Response {
	void SolicitudArchivo(File file, Socket clientSocket,String var) throws IOException ;
}
