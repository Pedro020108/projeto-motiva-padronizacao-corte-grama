
package br.motiva.repository;

import br.motiva.model.IntervencaoOperacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervencaoOperacionalRepository
        extends JpaRepository<IntervencaoOperacional, Long> {

    List<IntervencaoOperacional> findByTipoIgnoreCase(String tipo);

    List<IntervencaoOperacional> findByTrechoId(Long trechoId);
}
