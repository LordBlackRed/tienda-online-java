package tiendaonline.servlets.carrito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tiendaonline.ServletIndex;
import tiendaonline.clases.Categoria;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Factura;
import tiendaonline.clases.LineaFactura;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.metodos.MisMetodos;

/**
 * @author Rafael de los Santos Guirado
 *
 */
public class ServletRealizarCompra extends HttpServlet {

	private static final long serialVersionUID = 464588348788960164L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Usuario usuarioSesion = (Usuario) request.getSession()
				.getAttribute(MisAtributos.usuario.toString());
		
		//Actualizamos los datos dle usuario en la BD
		usuarioSesion.setNombre(request.getParameter("nombreUsuario"));
		usuarioSesion.setApellidos(request.getParameter("apellidos"));
		usuarioSesion.setDni(request.getParameter("dni"));
		usuarioSesion.setDireccion(request.getParameter("direccion"));
		usuarioSesion.setLocalidad(request.getParameter("localidad"));
		usuarioSesion.setProvincia(request.getParameter("provincia"));
		usuarioSesion.setCp(Integer.parseInt(request.getParameter("cp")));
		usuarioSesion.setFechaNacimiento(request.getParameter("fechaN"));
		usuarioSesion.setTelefonoFijo(Integer.parseInt(request.getParameter("telFijo")));
		usuarioSesion.setTelefonoMovil(Integer.parseInt(request.getParameter("telMovil")));
		
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		
		EntityManager entityManagerUsuario = entityManagerFactory
				.createEntityManager();
		EntityTransaction transactionUsuario = entityManagerUsuario.getTransaction();
		
		transactionUsuario.begin();
		entityManagerUsuario.merge(usuarioSesion);
		transactionUsuario.commit();
		
		entityManagerUsuario.close();
		
		String totalFactura = request.getParameter("precio");

		request.getSession().setAttribute(MisAtributos.totalFactura.toString(),
				totalFactura);

		List<Categoria> categorias = MisMetodos.obtenerCategorias(request);
		List<Fabricante> fabricantes = MisMetodos.obtenerFabricantes(request);
		long numeroFactura = MisMetodos.obtenerNumeroFactura(request);

		MisMetodos.asignarRequest(request, categorias, fabricantes);
		request.setAttribute(MisAtributos.numeroFactura.toString(),
				numeroFactura);

		// lo inicializamos de nuevo
		ServletIndex.usuarioSesion = new Usuario();
		ServletIndex.carritoSesion = new ArrayList<Producto>();
		ServletIndex.usuarioSesion = usuarioSesion;
		ServletIndex.carritoSesion = (List<Producto>) request.getSession()
				.getAttribute(MisAtributos.carrito.toString());

//		List<Producto> carrito = ServletIndex.carritoSesion;
//		List<Long> idProductos = MisMetodos.obtenerIdProductosCarrito(request);
//
//		Long numero = MisMetodos.obtenerIdFactura(request);
//
//		Usuario usuario = ServletIndex.usuarioSesion;
//		Factura factura = new Factura();
//
//		factura.setUsuario(usuario);
//		factura.setFecha(new Date());
//		factura.setIdProductos(idProductos);
//		factura.setNumero(numero);
//		factura.setIdEnvio(ServletIndex.idEnvioCompra);
//		// factura.setPrecio(Double.parseDouble(request.getParameter("mc_gross")));
//
//		for (Producto producto : carrito) {
//
//			LineaFactura lineaFactura = new LineaFactura();
//
//			lineaFactura.setCantidad(producto.getCantidad());
//			lineaFactura.setIdFactura(factura.getNumero());
//			lineaFactura.setPrecio(producto.getPrecio());
//			lineaFactura.setIdProducto(producto.getId().getId());
//
//			EntityManager entityManager = entityManagerFactory
//					.createEntityManager();
//			EntityTransaction transaction = entityManager.getTransaction();
//
//			transaction.begin();
//			entityManager.persist(lineaFactura);
//			transaction.commit();
//
//			transaction.begin();
//			// Actualizamos la cantidad del producto
//			/*
//			 * Metemos en una variable auxiliar la cantidad que ten’a en la
//			 * sesi—n para que una vez realizada o canlecada la compra le
//			 * aparezca en el carrito la cantidad que Žl quer’a/ha comprado,
//			 * sino, se sustituir’a por la cantidad que existe realmente ene l
//			 * stock
//			 */
//			int cantidadAux = producto.getCantidad();
//			int cantidadProducto = MisMetodos.obtenerCantidadProducto(request,
//					producto.getId().getId());
//			producto.setCantidad(cantidadProducto - producto.getCantidad());
//			entityManager.merge(producto);
//			transaction.commit();
//
//			producto.setCantidad(cantidadAux);
//			entityManager.close();
//
//		}
//
//		EntityManager entityManager = entityManagerFactory
//				.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
//
//		transaction.begin();
//		entityManager.persist(factura);
//
//		transaction.commit();
//		entityManager.close();

		request.getRequestDispatcher("realizar-compra.jsp").forward(request,
				response);

	}

}
