package br.com.jonataslaet.microlojalaet.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	@Query("SELECT DISTINCT prod from Produto prod INNER JOIN prod.categorias cat where prod.nome like %:nome% and cat in :categorias")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

}
