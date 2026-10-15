
package br.motiva.repository;

import br.motiva.model.TrechoRodovia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrechoRodoviaRepository
        extends JpaRepository<TrechoRodovia, Long> {

    List<TrechoRodovia> findByTrechoUmido(Boolean trechoUmido);
}