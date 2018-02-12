package com.arllanfelipe.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arllanfelipe.cursomc.domain.Categoria;
import com.arllanfelipe.cursomc.domain.Cidade;
import com.arllanfelipe.cursomc.domain.Estado;
import com.arllanfelipe.cursomc.domain.Produto;
import com.arllanfelipe.cursomc.repositories.CategoriaRepository;
import com.arllanfelipe.cursomc.repositories.CidadeRepository;
import com.arllanfelipe.cursomc.repositories.EstadoRepository;
import com.arllanfelipe.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",100.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		Estado est1 = new Estado(null,"Pernambuco");
		Estado est2 = new Estado(null,"Paraiba");
		
		Cidade cid1 = new Cidade(null,"Recife",est1);
		Cidade cid2 = new Cidade(null,"João Pessoa",est2);
		Cidade cid3 = new Cidade(null,"Paulista",est1);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		est1.getCidades().addAll(Arrays.asList(cid1,cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(cid1,cid2,cid3));
	}
}
