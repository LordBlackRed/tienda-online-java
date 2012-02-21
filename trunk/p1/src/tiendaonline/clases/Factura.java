package tiendaonline.clases;

import java.io.Serializable;
import java.util.Date;
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
public class Factura implements Comparable<Factura>, Serializable{

	private static final long serialVersionUID = -2861503446051715312L; 

	private Key id;
	private Long numero;
	private Usuario usuario;
	private Date fecha;
	private double precio;
	private List<Long> idProductos;

	public List<Long> getIdProductos() {
		return idProductos;
	}

	public void setIdProductos(List<Long> idProductos) {
		this.idProductos = idProductos;
	}

	public Factura(Key id, Long numero, Usuario usuario, Date fecha,
			double precio) {
		super();
		this.id = id;
		this.numero = numero;
		this.usuario = usuario;
		this.fecha = fecha;
		this.precio = precio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Factura() {
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Key getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", numero=" + numero + "]";
	}

	public void setId(Key id) {
		this.id = id;
	}

	@Override
	public int compareTo(Factura f) {
		if (this.fecha.before(f.fecha)) {
			return -1;
		} else if (this.fecha.after(f.fecha)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

}
