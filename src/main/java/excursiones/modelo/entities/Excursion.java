package excursiones.modelo.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name="excursiones")
public class Excursion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="id")
	@Id 
	private long idExcursion;
	private String descripcion;
	private String origen;
	private String destino;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha_excursion")
	@Temporal(TemporalType.DATE)
	private Date fechaExcursion;
	private int duracion;
	private String estado;
	private String destacado;
	private int aforoMaximo;
	private double precioUnitario;
	private String imagen;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	/*@ManyToOne
	@JoinColumn(name="id_familia")
	private Familia familia;
	*/
	
	
	public Excursion() {
		super();
	}

	public Excursion(long idExcursion, String descripcion, String origen, String destino, Date fechaExcursion,
			int duracion, String estado, String destacado, int aforoMaximo, int precioUnitario, String imagen,
			Date fechaAlta) {
		super();
		this.idExcursion = idExcursion;
		this.descripcion = descripcion;
		this.origen = origen;
		this.destino = destino;
		this.fechaExcursion = fechaExcursion;
		this.duracion = duracion;
		this.estado = estado;
		this.destacado = destacado;
		this.aforoMaximo = aforoMaximo;
		this.precioUnitario = precioUnitario;
		this.imagen = imagen;
		this.fechaAlta = fechaAlta;
	}

	public long getIdExcursion() {
		return idExcursion;
	}

	public void setIdExcursion(long idExcursion) {
		this.idExcursion = idExcursion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Date getFechaExcursion() {
		return fechaExcursion;
	}

	public void setFechaExcursion(Date fechaExcursion) {
		this.fechaExcursion = fechaExcursion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDestacado() {
		return destacado;
	}

	public void setDestacado(String destacado) {
		this.destacado = destacado;
	}

	public int getAforoMaximo() {
		return aforoMaximo;
	}

	public void setAforoMaximo(int aforoMaximo) {
		this.aforoMaximo = aforoMaximo;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idExcursion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Excursion))
			return false;
		Excursion other = (Excursion) obj;
		return idExcursion == other.idExcursion;
	}

	@Override
	public String toString() {
		return "Excursion [idExcursion=" + idExcursion + ", descripcion=" + descripcion + ", origen=" + origen
				+ ", destino=" + destino + ", fechaExcursion=" + fechaExcursion + ", duracion=" + duracion + ", estado="
				+ estado + ", destacado=" + destacado + ", aforoMaximo=" + aforoMaximo + ", precioUnitario="
				+ precioUnitario + ", imagen=" + imagen + ", fechaAlta=" + fechaAlta + "]";
	}
	
	
	
	
	
}
