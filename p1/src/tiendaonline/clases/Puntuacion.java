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

@Entity
public class Puntuacion implements Comparable<Puntuacion>, Serializable {


	private Long id;
	private Long idUsuario;
	private Long idProducto;
	private Date fecha;
	private int puntos;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Puntuacion() {
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Puntuacion [id=" + id + ", idUsuario=" + idUsuario
				+ ", idProducto=" + idProducto + ", fecha=" + fecha
				+ ", puntos=" + puntos + "]";
	}

	public Puntuacion(Long id, Long idUsuario, Long idProducto, Date fecha,
			int puntos) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idProducto = idProducto;
		this.fecha = fecha;
		this.puntos = puntos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	@Override
	public int compareTo(Puntuacion p) {
		// TODO Auto-generated method stub
		if (this.fecha.before(p.getFecha())) {
			return 1;
		} else if (this.fecha.after(p.getFecha())) {
			return -1;
		} else {
			return 0;
		}
	}
}
