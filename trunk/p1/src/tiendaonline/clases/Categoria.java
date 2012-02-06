package tiendaonline.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class Categoria {

	private Key id;
	private String titulo;
	private List<Producto> productos;
	private Fabricante fabricante;

	public Categoria(String titulo, List<Producto> productos,
			Fabricante fabricante) {
		super();
		this.titulo = titulo;
		this.productos = productos;
		this.fabricante = fabricante;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Categoria() {
		this.productos = new ArrayList<Producto>();
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@OneToMany(mappedBy = "categoria")
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
