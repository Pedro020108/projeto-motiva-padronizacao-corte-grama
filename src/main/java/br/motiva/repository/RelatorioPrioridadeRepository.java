
package br.motiva.repository;

import br.motiva.model.RelatorioPrioridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RelatorioPrioridadeRepository
        extends JpaRepository<RelatorioPrioridade, Long> {

    List<RelatorioPrioridade> findByDataGeracaoBetween(
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
