package com.arllanfelipe.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arllanfelipe.cursomc.domain.Categoria;
import com.arllanfelipe.cursomc.domain.Cidade;
import com.arllanfelipe.cursomc.domain.Cliente;
import com.arllanfelipe.cursomc.domain.Endereco;
import com.arllanfelipe.cursomc.domain.Estado;
import com.arllanfelipe.cursomc.domain.Pagamento;
import com.arllanfelipe.cursomc.domain.PagamentoComBoleto;
import com.arllanfelipe.cursomc.domain.PagamentoComCartao;
import com.arllanfelipe.cursomc.domain.Pedido;
import com.arllanfelipe.cursomc.domain.Produto;
import com.arllanfelipe.cursomc.domain.enums.EstadoPagamento;
import com.arllanfelipe.cursomc.domain.enums.TipoCliente;
import com.arllanfelipe.cursomc.repositories.CategoriaRepository;
import com.arllanfelipe.cursomc.repositories.CidadeRepository;
import com.arllanfelipe.cursomc.repositories.ClienteRepository;
import com.arllanfelipe.cursomc.repositories.EnderecoRepository;
import com.arllanfelipe.cursomc.repositories.EstadoRepository;
import com.arllanfelipe.cursomc.repositories.PagamentoRepository;
import com.arllanfelipe.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
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
		
		Cliente cli1 = new Cliente(null,"Arllan","arl@gmail.com","12332112345",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("34380011","99882323"));		
		Endereco end1 = new Endereco(null,"Rua Prof Anuncidada da Rocha Melo","180","Casa","Madalena","50710390",cli1,cid1);
		Endereco end4 = new Endereco(null,"Rua Vem que tem","09","Apt","Abreu e Lima","32421234",cli1,cid1);
		Cliente cli2 = new Cliente(null,"Dayana dos Santos","day@hotmail.com","12332112345",TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("23231212","34345656"));
		Endereco end2 = new Endereco(null,"Rua Manuel de Araujo","387","Prive","Pau Amarelo","12312345",cli2,cid3);
		
		Cliente cli3 = new Cliente(null, "Empresa X", "empx@gmail.com", "000000700015414", TipoCliente.PESSOAJURIDICA);
		cli3.getTelefones().addAll(Arrays.asList("21210000","32320001","32423232"));
		Endereco end3 = new Endereco(null, "Rua Paraiba 321", "111", "ND", "Soledar", "00011122233", cli3, cid2);
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("01/01/2018 10:20"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("02/02/2018 04:15"), cli1,end4);
		
		Pagamento pgto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null,EstadoPagamento.PEDENTE,ped2,sdf.parse("28/01/2018 10:00"),sdf.parse("28/02/2018 00:00"));
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(cid1,cid2,cid3));
		clienteRepository.save(Arrays.asList(cli1,cli2,cli3));
		enderecoRepository.save(Arrays.asList(end1,end2,end3,end4));
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pgto1,pgto2));
	}
}
