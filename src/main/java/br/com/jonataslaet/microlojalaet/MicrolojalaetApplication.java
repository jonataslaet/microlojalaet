package br.com.jonataslaet.microlojalaet;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.domain.Cidade;
import br.com.jonataslaet.microlojalaet.domain.Estado;
import br.com.jonataslaet.microlojalaet.domain.Produto;
import br.com.jonataslaet.microlojalaet.repositories.CategoriaRepository;
import br.com.jonataslaet.microlojalaet.repositories.CidadeRepository;
import br.com.jonataslaet.microlojalaet.repositories.EstadoRepository;
import br.com.jonataslaet.microlojalaet.repositories.ProdutoRepository;

@SpringBootApplication
public class MicrolojalaetApplication implements CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
