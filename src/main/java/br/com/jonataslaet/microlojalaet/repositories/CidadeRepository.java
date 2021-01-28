package br.com.jonataslaet.microlojalaet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jonataslaet.microlojalaet.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	@Query("select c from Cidade c where c.estado.id =:idEstado")
	List<Cidade> findAllByEstado(@Param("idEstado") Integer estado);

}
