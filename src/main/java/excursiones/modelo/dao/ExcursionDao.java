package excursiones.modelo.dao;

import java.util.List;

import excursiones.modelo.entities.Excursion;

public interface ExcursionDao {
	Excursion findById(long id);
	List<Excursion> findAll();
	int insertOne(Excursion excursion);
	int updateOne(Excursion excursion);
	List<Excursion> findByCreados();
	List<Excursion> findByCancelados();
	List<Excursion> findByTerminado();
	List<Excursion> findByDestados();
}
