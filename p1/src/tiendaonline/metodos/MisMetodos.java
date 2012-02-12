package tiendaonline.metodos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import tiendaonline.clases.Categoria;
import tiendaonline.clases.Envio;
import tiendaonline.clases.Fabricante;
import tiendaonline.clases.Producto;
import tiendaonline.clases.Puntuacion;
import tiendaonline.clases.Usuario;

public class MisMetodos {

	public static List<Categoria> obtenerCategorias(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpqlCategoria = "select categoria from Categoria categoria";
		List<Categoria> categorias = entityManager.createQuery(jpqlCategoria)
				.getResultList();
		transaction.commit();
		entityManager.close();

		return categorias;
	}

	public static List<Producto> obtenerProductos(HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select producto from Producto producto";
		List<Producto> productos = entityManager.createQuery(jpql)
				.getResultList();

		transaction.commit();
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
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select fabricante from Fabricante fabricante";
		List<Fabricante> fabricantes = entityManager.createQuery(jpql)
				.getResultList();

		transaction.commit();
		entityManager.close();

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

	public static Usuario obtenerUsuario(String nick,
			HttpServletRequest request) {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		String jpql = "select usuario from Usuario usuario where usuario.nick=:n";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("n", nick);

		Usuario usuario = (Usuario) query.getSingleResult();

		transaction.commit();
		entityManager.close();

		return usuario;
	}

//	public static int obtenerPuntuacion(HttpServletRequest request,
//			Long idProducto) {
//
//		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
//				.getSession().getServletContext().getAttribute("emf");
//		EntityManager entityManager = entityManagerFactory
//				.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();
//		transaction.begin();
//
//		String jpql = "select puntuacion from Puntuacion puntuacion where puntuacion.idProducto=:idProd";
//		Query query = entityManager.createQuery(jpql);
//		query.setParameter("idProd", idProducto);
//
//		int puntuacion = 0;
//
//		List<Puntuacion> puntuaciones= (List<Puntuacion>) query.getResultList();
//
//		Collections.sort(puntuaciones);
//		try{
//		puntuacion = puntuaciones.get(0).getPuntuacionTotal();
//		}catch (IndexOutOfBoundsException ex){
//			puntuacion = 0;
//		}
//		System.out.println("PUNTUACION****" + puntuacion);
//		transaction.commit();
//		entityManager.close();
//
//		return puntuacion;
//	}

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

		List<Producto> productos = (List<Producto>) query.getResultList();

		Long idProducto = 0L;
		for (Producto p : productos) {
			System.out.println("nombre producto: " + p.getNombre());
			if (p.getNombre().equals(nombre)) {
				idProducto = p.getId().getId();
				break;
			}
		}

		transaction.commit();
		entityManager.close();

		return idProducto;
	}

	public static List<Producto> obtenerProductosCategoria(
			HttpServletRequest request, String tituloCategoria) {
	
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request
				.getSession().getServletContext().getAttribute("emf");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		
		String jpql = "select producto from Producto producto";
		Query query = entityManager.createQuery(jpql);
		
		List<Producto> productos = query.getResultList();
		
		List<Producto> productosCategoria = new ArrayList<Producto>();
		

		for (Producto producto: productos){
			if (producto.getCategoriaString().equals(tituloCategoria)){
				productosCategoria.add(producto);
			}
		}
		entityManager.close();
		
		return productosCategoria;
	}

	public static List<Puntuacion> obtenerPuntuacionesProducto(List<Puntuacion> puntuaciones, long idProducto){
		
		List<Puntuacion> puntuacionProducto = new ArrayList<Puntuacion>();
		
		for (Puntuacion puntuacion: puntuaciones){
			if (puntuacion.getIdProducto().longValue() == idProducto){
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
		
		List<Envio> empresasEnvio = query.getResultList();
		
		return empresasEnvio;
	}

	public static Envio obtenerEmpresaEnvio(List<Envio> empresasEnvio,
			long idEnvio) {

		Envio envio = null;
		for (Envio empresaEnvio: empresasEnvio){
			if (empresaEnvio.getId().longValue() == idEnvio){
				envio = empresaEnvio;
				break;
			}
		}
		
		return envio;
	}
}
