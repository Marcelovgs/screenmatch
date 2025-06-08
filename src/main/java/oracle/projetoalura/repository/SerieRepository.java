package oracle.projetoalura.repository;

import oracle.projetoalura.Model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
