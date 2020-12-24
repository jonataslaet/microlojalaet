package br.com.jonataslaet.microlojalaet;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.domain.Produto;
import br.com.jonataslaet.microlojalaet.repositories.CategoriaRepository;
import br.com.jonataslaet.microlojalaet.repositories.ProdutoRepository;

@SpringBootApplication
public class MicrolojalaetApplication implements CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MicrolojalaetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletrônico");
		
		Produto p1 = new Produto(null, "Notebook", 2780.00);
		Produto p2 = new Produto(null, "Impressora", 790.00);
		Produto p3 = new Produto(null, "Cadeira", 320.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
