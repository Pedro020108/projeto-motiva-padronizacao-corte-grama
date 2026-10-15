
package br.motiva.repository;

import br.motiva.model.EquipeManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeManutencaoRepository
        extends JpaRepository<EquipeManutencao, Long> {

    List<EquipeManutencao> findByNomeContainingIgnoreCase(String nome);
}
