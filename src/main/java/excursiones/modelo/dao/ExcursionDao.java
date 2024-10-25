package excursiones.modelo.dao;

import java.util.List;

import excursiones.modelo.entities.Excursion;

public interface ExcursionDao {
	Excursion findById(int id);
	List<Excursion> findAll();
	int insertOne(Excursion excursion);
	int updateOne(Excursion excursion);
	List<Excursion> findByCreados(String estado);
	List<Excursion> findByCancelados(String estado);
	List<Excursion> findByTerminado(String estado);
	List<Excursion> findByDestados(String destacado);
}
