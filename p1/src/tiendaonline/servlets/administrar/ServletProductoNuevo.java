package tiendaonline.servlets.administrar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;

public class ServletProductoNuevo extends HttpServlet {
	//public static String url;


	private String dirUploadFiles; // directorio donde se guardara los archivos

	
	private static final long serialVersionUID = 865732587598002069L;

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		double precio = Double.parseDouble(request.getParameter("precio"));
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		String fecha = request.getParameter("fecha");
		String urlImagen = request.getParameter("url");
		String tituloCategoria = request.getParameter("categoria");
		
		Producto producto = new Producto();

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		producto.setCantidad(cantidad);
		producto.setFecha(fecha);
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setUrlImagen(urlImagen);
		producto.setCategoriaString(tituloCategoria);
		
		transaction.begin();
		entityManager.persist(producto);
		transaction.commit();
		response.sendRedirect("Index");
	}
	
}
