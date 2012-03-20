package tiendaonline;
/*
 * uploadFichero.java
 *
 * Created on 4 de agosto de 2003, 22:26
 */

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.util.*;
/**
 *
 * @author  Roberto Canales
 * @version
 */

public class UploadFichero extends HttpServlet {
    
	private static final long serialVersionUID = -3194552376109044339L;
	private static final String tempDirectoryPath = 
			System.getProperty("java.io.tmpdir")+"/tmp-andami";
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet</title>");
        out.println("</head>");
        out.println("<body>");

        //Procesamiento de ficheros
        
        procesaFicheros(request,out);
        out.println("<a href='nuevo-producto.jsp'>Volver</a>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }


    void depura(String cadena)
    {
        System.out.println("El error es " + cadena);
    }

    @SuppressWarnings("rawtypes")
	public boolean procesaFicheros(HttpServletRequest request, PrintWriter out ) {
        try {
        	 // construimos el objeto que es capaz de parsear la perici—n
        	FileItemFactory factory = new DiskFileItemFactory();
        	
        	ServletFileUpload upload = new ServletFileUpload(factory);

        	List items = upload.parseRequest(request);
        	
        	 DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        	 
            // maximo numero de bytes
        	diskFileItemFactory.setSizeThreshold(1024*512);
           // fu.setSizeMax(1024*512); // 512 K

            depura("Ponemos el tama–o m‡ximo");
            
            
            File repositoryPath = new File(tempDirectoryPath);
            diskFileItemFactory.setRepository(repositoryPath);

       

            if(items == null)
            {
                depura("La lista es nula");
                return false;
            }

            out.print("<br>El nœmero de ficheros subidos es: " +  items.size());

            // Iteramos por cada fichero
	    Iterator i = items.iterator();
            FileItem actual = null;
            depura("estamos en la iteraci—n");

            while (i.hasNext())
            {
                actual = (FileItem)i.next();
                String fileName = actual.getName();
                out.println("<br>Nos han subido el fichero" + fileName);

                // construimos un objeto file para recuperar el trayecto completo
                File fichero = new File(fileName);
                depura("El nombre del fichero es " + fichero.getName());

                // nos quedamos solo con el nombre y descartamos el path
                fichero = new  File("c:\\ficherossubidos\\" + fichero.getName());

                // escribimos el fichero colgando del nuevo path
                actual.write(fichero);
            }

        }
        catch(Exception e) {
            depura("Error de Aplicaci—n " + e.getMessage());
            return false;
        }

        return true;
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
    	        processRequest(request, response);
    	    }
}