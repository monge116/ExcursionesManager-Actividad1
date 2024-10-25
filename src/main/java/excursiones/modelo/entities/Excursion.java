package excursiones.modelo.entities;

import java.io.Serializable;
import java.util.Date;

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
	@Column(name="fecha_excursion")
	private Date fechaExcursion;
	private int duracion;
	private String estado;
	private String destacado;
	private int aforoMaximo;
	private int precioUnitario;
	private String imagen;
	@Column(name="fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	/*@ManyToOne
	@JoinColumn(name="id_familia")
	private Familia familia;
	*/
}
