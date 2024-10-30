package excursiones.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import excursiones.modelo.entities.Excursion;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {

	List<Excursion> findByEstado(String estado);
	List<Excursion> findByDestacado(String destacado);
}
