package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Produto;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	/**
	 * Retorna a listagem de todas os produtos ordenados pela denominação do
	 * produto.
	 * 
	 * @return List<Pessoa> - Todos os produtos existentes. '
	 */
	public List<Produto> listarProdutos() {
		return produtoRepository.findAll(Sort.by(Sort.Direction.ASC, "denominacao"));
	}
	
	/**
	 * Retorna a listagem de todas os produtos ordenados pela denominação do
	 * produto.
	 * 
	 * @return List<Pessoa> - Todos os produtos existentes. '
	 */
	public List<Produto> listarProdutosPorQuantitativo() {
		return produtoRepository.findAll(Sort.by(Sort.Direction.ASC, "quantidade"));
	}

	/**
	 * Salva um novo produto na base de dados.
	 * 
	 * @param produto<Produto> - Novo produto informado.
	 * @return Produto - Novo produto criado.
	 */
	public Produto salvarProduto(Produto produto) {

		if (produto.getCodigo() == 0 || produto.getDataEntrada() == null || produto.getDenominacao() == null
				|| produto.getQuantidade() == 0) {
			throw new ServiceException("Campos obrigatórios não informados.");
		}

		else if (findProdutoByCodigo(produto.getCodigo()) != null) {
			throw new ServiceException("Já existe um cadastro com o código informado.");
		}

		return produtoRepository.save(produto);
	}

	/**
	 * Retorna um produto através do seu código.
	 * 
	 * @param codigo<Int> - Código do produto.
	 * @return Produto - Produto com o código informado.
	 */
	public Produto findProdutoByCodigo(int codigo) {
		return produtoRepository.findByCodigo(codigo);
	}
}
