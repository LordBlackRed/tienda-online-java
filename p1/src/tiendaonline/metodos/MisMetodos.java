package tiendaonline.metodos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import tiendaonline.ServletIndex;
import tiendaonline.clases.Categoria;
import tiendaonline.clases.Envio;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Factura;
import tiendaonline.clases.LineaFactura;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Puntuacion;
import tiendaonline.clases.Usuario;
import tiendaonline.enumerados.MisAtributos;
import tiendaonline.listeners.ContextoListener;

/**
 * @author Rafael de los Santos Guirado
 * 
 */
public class MisMetodos {

	public static List<Categoria> obtenerCategorias(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpqlCategoria = "select categoria from Categoria categoria";
		@SuppressWarnings("unchecked")
		List<Categoria> categorias = entityManager.createQuery(jpqlCategoria)
				.getResultList();

		return categorias;
	}

	public static List<Producto> obtenerProductos(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select producto from Producto producto";
		@SuppressWarnings("unchecked")
		List<Producto> productos = entityManager.createQuery(jpql)
				.getResultList();

		for (@SuppressWarnings("unused")
		Producto producto : productos) {

		}
		entityManager.close();

		return productos;
	}

	public static List<Puntuacion> obtenerPuntuaciones(
			HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select puntuacion from Puntuacion puntuacion";
		@SuppressWarnings("unchecked")
		List<Puntuacion> puntuaciones = entityManager.createQuery(jpql)
				.getResultList();

		transaction.commit();
		entityManager.close();

		return puntuaciones;
	}

	public static List<Fabricante> obtenerFabricantes(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select fabricante from Fabricante fabricante";
		@SuppressWarnings("unchecked")
		List<Fabricante> fabricantes = entityManager.createQuery(jpql)
				.getResultList();

		return fabricantes;
	}

	public static Producto obtenerProducto(List<Producto> productos,
			String idProducto) {
		for (Producto producto : productos) {
			if (producto.getId().getId() == Long.parseLong(idProducto)) {
				return producto;

			}
		}
		return null;
	}

	public static Usuario obtenerUsuario(String nick, HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select usuario from Usuario usuario where usuario.nick=:n";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("n", nick);

		Usuario usuario = null;
		try {
			usuario = (Usuario) query.getSingleResult();

			/*
			 * Debido al maravilloso objeto entityManager de GAE, tenemos que
			 * obtener los productos favoritos del usuario logueado (aunque no
			 * lo almacenemos en ningún lado) para que luego no salte un error
			 * nullpointer cuando queramos acceder a los favoritos de dicho
			 * usuario, ya que, es como si no se almacenara en la sesión
			 */
			usuario.getProdFavoritos();
		} catch (NoResultException ex) {
		}
		entityManager.close();
		return usuario;
	}

	public static Usuario obtenerUsuarioPorDni(String dni,
			HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select usuario from Usuario usuario where usuario.dni=:d";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("d", dni);

		Usuario usuario = null;
		try {
			usuario = (Usuario) query.getSingleResult();

			/*
			 * Debido al maravilloso objeto entityManager de GAE, tenemos que
			 * obtener los productos favoritos del usuario logueado (aunque no
			 * lo almacenemos en ningún lado) para que luego no salte un error
			 * nullpointer cuando queramos acceder a los favoritos de dicho
			 * usuario, ya que, es como si no se almacenara en la sesión
			 */
			usuario.getProdFavoritos();
		} catch (NoResultException ex) {
		}
		entityManager.close();
		return usuario;
	}

	public static Long obtenerIdProducto(HttpServletRequest request,
			String nombre) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select producto from Producto producto";
		Query query = entityManager.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<Producto> productos = ((List<Producto>) query.getResultList());

		Long idProducto = 0L;
		for (Producto p : productos) {
			if (p.getNombre().equals(nombre)) {
				idProducto = p.getId().getId();
				break;
			}
		}

		transaction.commit();
		entityManager.close();

		return idProducto;
	}

	public static List<Producto> obtenerProductosCategoriaPaginados(
			HttpServletRequest request, String tituloCategoria, int start,
			int productosPorPagina) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select producto from Producto producto where producto.categoriaString=:cat";
		Query query = entityManager.createQuery(jpql);
		query = query.setFirstResult(start);
		query = query.setMaxResults(productosPorPagina);
		query.setParameter("cat", tituloCategoria);

		@SuppressWarnings("unchecked")
		List<Producto> productosCategoria = query.getResultList();

		return productosCategoria;

	}

	public static List<Producto> obtenerProductosCategoria(
			HttpServletRequest request, String tituloCategoria) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select producto from Producto producto";
		Query query = entityManager.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<Producto> productos = query.getResultList();

		List<Producto> productosCategoria = new ArrayList<Producto>();

		for (Producto producto : productos) {
			if (producto.getCategoriaString().equals(tituloCategoria)) {
				productosCategoria.add(producto);
			}
		}
		entityManager.close();

		return productosCategoria;

	}

	public static List<Puntuacion> obtenerPuntuacionesProducto(
			List<Puntuacion> puntuaciones, long idProducto) {

		List<Puntuacion> puntuacionProducto = new ArrayList<Puntuacion>();

		for (Puntuacion puntuacion : puntuaciones) {
			if (puntuacion.getIdProducto().longValue() == idProducto) {
				puntuacionProducto.add(puntuacion);
			}
		}
		return puntuacionProducto;
	}

	public static List<Envio> obtenerEmpresasEnvio(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select envio from Envio envio";
		Query query = entityManager.createQuery(jpql);

		@SuppressWarnings("unchecked")
		List<Envio> empresasEnvio = query.getResultList();

		return empresasEnvio;
	}

	public static Envio obtenerEmpresaEnvio(List<Envio> empresasEnvio,
			long idEnvio) {

		Envio envio = null;
		for (Envio empresaEnvio : empresasEnvio) {
			if (empresaEnvio.getId().longValue() == idEnvio) {
				envio = empresaEnvio;
				break;
			}
		}

		return envio;
	}

	public static long obtenerNumeroFactura(HttpServletRequest request) {

		List<Factura> facturas = obtenerFacturas(request);

		long numeroFactura = 1L;
		if (facturas.size() > 0) {
			numeroFactura = facturas.get(facturas.size() - 1).getNumero()
					.longValue();
		}
		return numeroFactura;
	}

	public static List<Factura> obtenerFacturas(HttpServletRequest request) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select factura from Factura factura";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Factura> facturas = query.getResultList();

		return facturas;
	}

	public static List<Factura> obtenerFacturasPaginadas(
			HttpServletRequest request, int start, int facturasPorPagina) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select factura from Factura factura";
		Query query = entityManager.createQuery(jpql);
		query = query.setFirstResult(start);
		query = query.setMaxResults(facturasPorPagina);
		@SuppressWarnings("unchecked")
		List<Factura> facturas = query.getResultList();

		return facturas;
	}

	public static void introducirUsuarioSesion(HttpServletRequest request,
			String nick, String pass, boolean admin) {

		// Buscamos el usuario en concreto en la base de datos por si tiene
		// otros datos en la BD
		Usuario usuario = obtenerUsuario(nick, request);

		if (usuario == null) {
			usuario = new Usuario();
			usuario.setAdmin(admin);
			usuario.setPass(pass);
			usuario.setNick(nick);
			usuario.setFacturas(new ArrayList<Factura>());

		}
		request.getSession().setAttribute(MisAtributos.usuario.toString(),
				usuario);

	}

	public static List<Factura> obtenerFacturasUsuario(
			HttpServletRequest request, Usuario usuario) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		String jpql = "select usuario from Usuario usuario where usuario.nick=:n";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("n", usuario.getNick());

		Usuario user = (Usuario) query.getSingleResult();

		List<Factura> facturas = user.getFacturas();
		// Recorremos el bucle, aunque parezca vacío, si no se pone es como si
		// facturas estuviera vacío
		// Misterios del GAE
		// Si no cerráramos el EM no haría falta crear el bucle, pero es mejor
		// cerrarlo
		for (@SuppressWarnings("unused")
		Factura factura : facturas) {

		}
		entityManager.close();

		return facturas;
	}

	public static List<Long> obtenerIdProductosCarrito(
			HttpServletRequest request) {

		List<Producto> carrito = ServletIndex.carritoSesion;
		List<Long> idProductosCarrito = new ArrayList<Long>();

		for (Producto producto : carrito) {
			idProductosCarrito.add(producto.getId().getId());
		}

		return idProductosCarrito;
	}

	public static Long obtenerIdFactura(HttpServletRequest request) {
		List<Factura> facturas = obtenerFacturas(request);

		if (facturas.size() == 0) {
			return 1L;
		} else {
			return facturas.get(facturas.size() - 1).getNumero() + 1;
		}
	}

	public static List<LineaFactura> obtenerLineasFacturas(
			HttpServletRequest request, Long idFactura) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select lineaFactura from LineaFactura lineaFactura where lineaFactura.idFactura=:idF";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idF", idFactura);

		@SuppressWarnings("unchecked")
		List<LineaFactura> lineasFacturas = query.getResultList();

		return lineasFacturas;
	}

	public static List<Producto> obtenerProductosFabricante(
			HttpServletRequest request, String idFabricante, int start,
			int productosPorPagina) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select producto from Producto producto where producto.idFabricante=:id";
		Query query = entityManager.createQuery(jpql);
		query = query.setFirstResult(start);
		query = query.setMaxResults(productosPorPagina);
		query.setParameter("id", Long.parseLong(idFabricante));

		@SuppressWarnings("unchecked")
		List<Producto> productosFabricante = query.getResultList();

		return productosFabricante;
	}

	public static List<Producto> obtenerProductosFavoritos(
			HttpServletRequest request, Usuario usuario, int start,
			int productosPorPagina) throws NoResultException {

		Set<Long> prodFavoritos = usuario.getProdFavoritos();
		List<Producto> productos = new ArrayList<Producto>();
		Iterator<Long> it = prodFavoritos.iterator();
		List<Producto> productosFavoritos = new ArrayList<Producto>();
		while (it.hasNext()) {
			Long id = it.next();
			EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
					.getSession().getServletContext().getAttribute("emf");
			EntityManager entityManager = entityManagerFactory
					.createEntityManager();
			String jpql = "select producto from Producto producto where producto.id=:id";
			Query query = entityManager.createQuery(jpql);

			query.setParameter("id", id);
			try {
				productos.add((Producto) query.getSingleResult());
			} catch (NoResultException e) {
				prodFavoritos.remove(id);
				EntityTransaction tx = entityManager.getTransaction();
				usuario.setProdFavoritos(prodFavoritos);
				tx.begin();
				entityManager.merge(usuario);
				tx.commit();
				throw new NoResultException(
						"El producto favorito ha sido eliminado de la Base de Datos");
			}
			entityManager.close();
		}
		/*
		 * Si es admin se salta el proceso para que no dé una excepción ya que
		 * los admin no tienen favoritos Ésto sólo sirve si el usuario admin
		 * entra en los favoritos a través de la url (debería de conocerla) ya
		 * que, desde la interfaz no existe ningún link que la lleve a ella Y si
		 * el tamaño es 0 tampoco se hace, esto se controla por si el usuario no
		 * tiene favoritos
		 */

		if (!usuario.getNick().equals("admin") && productos.size() != 0) {
				for (int i = start; i < productos.size(); i++) {
					//Secuencia que hace la paginación de los productos favoritos
					if (i == productosPorPagina + start) {
						break;
					} else {
						productosFavoritos.add(productos.get(i));
					}
				}
			
		}
		return productosFavoritos;
	}

	public static boolean validarEmail(String mail) {

		char c1 = mail.charAt(0);
		int pos1 = 0;
		int pos2 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;
		int x = 0;
		String cadena1 = "";
		String cadena2 = "";
		String cadena3 = "";

		try {
			if (c1 != '@' && c1 != '.') {

				for (int i = 1; i < mail.length(); i++) {
					if (mail.charAt(i) == '@') {
						pos1 = i;
					}
				}
				for (int i = 1; i < mail.length(); i++) {
					if (mail.charAt(i) == '.') {
						pos2 = i;
					}
				}

				cadena1 = mail.substring(0, pos1);
				cadena2 = mail.substring(pos1 + 1, pos2);
				cadena3 = mail.substring(pos2 + 1, mail.length());

				for (int i = 0; i < cadena1.length(); i++) {
					a1 = cadena1.codePointAt(i);//
					if ((a1 > 47 && a1 < 58) || (a1 > 64 && a1 < 91)
							|| (a1 > 96 && a1 < 123) || a1 == 46) {
						x++;
					}
				}
				for (int i = 0; i < cadena2.length(); i++) {
					a2 = cadena2.codePointAt(i);
					if ((a2 > 47 && a2 < 58) || (a2 > 64 && a2 < 91)
							|| (a2 > 96 && a2 < 123)) {
						x++;
					}
				}

				for (int i = 0; i < cadena3.length(); i++) {
					a3 = cadena3.codePointAt(i);
					if ((a3 > 47 && a3 < 58) || (a3 > 64 && a3 < 91)
							|| (a3 > 96 && a3 < 123)) {
						x++;
					}
				}
				if (x == mail.length() - 2) {
					if (pos1 != 0 && pos2 != 0 && (pos1 + 2) < pos2) {

						if (mail.length() - 1 >= (pos2 + 2)) {

							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (StringIndexOutOfBoundsException e) {
			return false;
		}

	}

	public static void eliminarFavUsuarioPro(HttpServletRequest request,
			String idProducto) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		String jpql = "select usuario from Usuario usuario";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = query.getResultList();

		for (Usuario usuario : usuarios) {
			Set<Long> favsUsuario = usuario.getProdFavoritos();
			Iterator<Long> it = favsUsuario.iterator();
			while (it.hasNext()) {
				Long idProductoFav = it.next();
				if (Long.parseLong(idProducto) == idProductoFav.longValue()) {
					favsUsuario.remove(idProductoFav);
					transaction.begin();
					entityManager.merge(usuario);
					transaction.commit();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductosSearchPaginado(
			HttpServletRequest request, String parameter, int start,
			int productosPorPagina) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select producto FROM Producto producto WHERE LOWER(alumno.nombre) LIKE :busq";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("busq", parameter + "%".toLowerCase());
		query = query.setFirstResult(start);
		query = query.setMaxResults(productosPorPagina);
		List<Producto> productos;

		try {
			productos = query.getResultList();
		} catch (NullPointerException ex) {
			productos = obtenerProductos(request);

		}

		if (productos.isEmpty()) {
			productos = new ArrayList<Producto>();
		}
		return productos;
	}

	@SuppressWarnings("unchecked")
	public static List<Producto> obtenerProductosSearch(
			HttpServletRequest request, String parameter) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select producto FROM Producto producto WHERE LOWER(alumno.nombre) LIKE :busq";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("busq", parameter + "%".toLowerCase());

		List<Producto> productos;

		try {
			productos = query.getResultList();
		} catch (NullPointerException ex) {
			productos = obtenerProductos(request);

		}

		if (productos.isEmpty()) {
			productos = new ArrayList<Producto>();
		}
		return productos;
	}

	public static Producto obtenerProductoNuevo(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select producto FROM Producto producto";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Producto> productos = query.getResultList();

		Collections.sort(productos);

		return productos.get(0);
	}

	public static void asignarRequest(HttpServletRequest request,
			List<Categoria> categorias, List<Fabricante> fabricantes) {
		Producto productoNuevo = null;
		try {
			productoNuevo = MisMetodos.obtenerProductoNuevo(request);
		} catch (IndexOutOfBoundsException ex) {

		}
		request.setAttribute(MisAtributos.productoNuevo.toString(),
				productoNuevo);
		request.setAttribute(MisAtributos.productosCabecera.toString(),
				ContextoListener.productosCabecera);
		request.setAttribute(MisAtributos.productoEspecial.toString(),
				ContextoListener.productoEspecial);
		request.setAttribute(MisAtributos.sponsor.toString(),
				ContextoListener.sponsor);
		request.setAttribute(MisAtributos.categorias.toString(), categorias);
		request.setAttribute(MisAtributos.fabricantes.toString(), fabricantes);
	}

	public static boolean isProductoFavorito(HttpServletRequest request,
			Long idProducto, Usuario usuario) throws NullPointerException {

		boolean favorito = false;
		try {
			Set<Long> favoritos = usuario.getProdFavoritos();
			Iterator<Long> it = favoritos.iterator();

			while (it.hasNext()) {
				Long idP = it.next();

				if (idProducto.longValue() == idP.longValue()) {
					favorito = true;
				}
			}
		} catch (NullPointerException ex) {

		}
		return favorito;
	}

	public static boolean comprobarExisteUsuario(HttpServletRequest request,
			String nick) {

		boolean existe = false;

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		String jpql = "select usuario FROM Usuario usuario where usuario.nick=:n";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("n", nick);

		try {
			Usuario usuario = (Usuario) query.getSingleResult();
			if (usuario != null) {
				existe = true;
			}
		} catch (NoResultException ex) {
			existe = false;
		} catch (NonUniqueResultException e) {
			existe = true;
		}

		return existe;
	}

	public static List<Producto> paginacion(HttpServletRequest request,
			int start, int productosPorPagina) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager em = entityManagerFactory.createEntityManager();

		Query query = em
				.createQuery("SELECT producto FROM Producto producto order by producto.fecha desc");

		query = query.setFirstResult(start);
		query = query.setMaxResults(productosPorPagina);

		@SuppressWarnings("unchecked")
		List<Producto> productos = query.getResultList();

		for(@SuppressWarnings("unused") Producto p: productos){
			
		}
		
		em.close();
		
		return productos;
	}

	public static int numPaginas(HttpServletRequest request,
			int productosPorPagina) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		Query q = entityManager
				.createQuery("select count(p) from Producto as p");
		// interesante!!! obtenemos un "int" como resultado!!
		int count = ((Integer) q.getSingleResult()).intValue();

		entityManager.close();
		double numero = (double) count / (double) productosPorPagina;

		String val = numero + "";
		BigDecimal big = new BigDecimal(val);
		big = big.setScale(0, RoundingMode.UP);

		int paginas = big.intValue();

		return paginas;
	}

	public static int numPaginasCategoria(HttpServletRequest request,
			int productosPorPagina, String categoria) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		Query q = entityManager
				.createQuery("select count(p) from Producto as p where p.categoriaString=:c");

		q.setParameter("c", categoria);

		int count = ((Integer) q.getSingleResult()).intValue();

		entityManager.close();
		double numero = (double) count / (double) productosPorPagina;

		String val = numero + "";
		BigDecimal big = new BigDecimal(val);
		big = big.setScale(0, RoundingMode.UP);

		int paginas = big.intValue();

		return paginas;

	}

	public static int numPaginasFavorito(HttpServletRequest request,
			Usuario usuario, int productosPorPagina) {

		Set<Long> prodFavoritos = usuario.getProdFavoritos();

		double numero = prodFavoritos.size() / (double) productosPorPagina;

		String val = numero + "";
		BigDecimal big = new BigDecimal(val);
		big = big.setScale(0, RoundingMode.UP);

		int paginas = big.intValue();

		return paginas;
	}

	public static int numPaginasFabricante(HttpServletRequest request,
			int productosPorPagina, String idFabricante) {

		List<Producto> productos = obtenerProductos(request);

		List<Producto> productosFabricante = new ArrayList<Producto>();

		for (Producto producto : productos) {
			if (producto.getIdFabricante().longValue() == Long
					.parseLong(idFabricante)) {
				productosFabricante.add(producto);
			}
		}
		int count = productosFabricante.size();
		double numero = (double) count / (double) productosPorPagina;

		String val = numero + "";
		BigDecimal big = new BigDecimal(val);
		big = big.setScale(0, RoundingMode.UP);

		int paginas = big.intValue();

		return paginas;
	}

	public static int numPaginasSearch(HttpServletRequest request,
			int productosPorPagina, String parameter) {

		List<Producto> productosSearch = obtenerProductosSearch(request,
				parameter);
		int count = productosSearch.size();
		double numero = (double) count / (double) productosPorPagina;

		String val = numero + "";
		BigDecimal big = new BigDecimal(val);
		big = big.setScale(0, RoundingMode.UP);

		int paginas = big.intValue();

		return paginas;
	}

	public static int obtenerCantidadProducto(HttpServletRequest request,
			long id) {

		List<Producto> productos = obtenerProductos(request);
		int cantidadProducto = 0;

		for (Producto producto : productos) {
			if (producto.getId().getId() == id) {
				cantidadProducto = producto.getCantidad();
				break;
			}
		}

		return cantidadProducto;
	}

	public static boolean comprobarStockProducto(HttpServletRequest request,
			long id, int cantidad) {

		boolean correcto = true;

		int stock = obtenerCantidadProducto(request, id);

		if (stock < cantidad) {
			correcto = false;
		}

		return correcto;
	}

	public static Long obtenerIdEnvio(HttpServletRequest request, Long idFactura) {

		List<Factura> facturas = obtenerFacturas(request);

		Long idEmpresaEnvio = 0L;

		for (Factura factura : facturas) {
			if (factura.getNumero().longValue() == idFactura.longValue()) {
				idEmpresaEnvio = factura.getIdEnvio();
			}
		}
		return idEmpresaEnvio;
	}

	public static int numPaginasFactura(HttpServletRequest request,
			int facturasPorPagina) {

		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		Query q = entityManager
				.createQuery("select count(f) from Factura as f");

		int count = ((Integer) q.getSingleResult()).intValue();

		entityManager.close();
		double numero = (double) count / (double) facturasPorPagina;

		String val = numero + "";
		BigDecimal big = new BigDecimal(val);
		big = big.setScale(0, RoundingMode.UP);

		int paginas = big.intValue();

		return paginas;
	}

}
