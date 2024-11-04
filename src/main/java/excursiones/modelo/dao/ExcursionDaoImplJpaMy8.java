package excursiones.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import excursiones.modelo.entities.Excursion;
import excursiones.modelo.repository.ExcursionRepository;
@Repository
public class ExcursionDaoImplJpaMy8 implements ExcursionDao{
	
	@Autowired
	private ExcursionRepository erepo;
	
	@Override
	public Excursion findById(long id) {
		// TODO Auto-generated method stub
		return erepo.findById(id).orElse(null);
	}
	

	@Override
	public List<Excursion> findAll() {
		// TODO Auto-generated method stub
		return erepo.findAll();
	}

	@Override
	public int insertOne(Excursion excursion) {
		
		return (erepo.save(excursion) != null) ? 1 : 0;
	}

	@Override
	public int updateOne(Excursion excursion) {
		// TODO Auto-generated method stub
		if (erepo.existsById(excursion.getIdExcursion())) {
			erepo.save(excursion);
			return 1;
		}
		return 0 ;
	}

	@Override
	public List<Excursion> findByCreados() {
		String estado ="creado";
		return erepo.findByEstado(estado);
	}

	@Override
	public List<Excursion> findByCancelados() {
		String estado ="cancelado";
		return erepo.findByEstado(estado);
	}

	@Override
	public List<Excursion> findByTerminado() {
		String estado ="terminado";
		return erepo.findByEstado(estado);
	}

	@Override
	public List<Excursion> findByDestacados() {
		String destacado= "S";
		return erepo.findByDestacado(destacado);
	}




	@Override
	public List<Excursion> origenContiene(String cadena) {
		
		return erepo.findByOrigenContaining(cadena);
	}

	

}
