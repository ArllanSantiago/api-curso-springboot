package com.arllanfelipe.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arllanfelipe.cursomc.domain.Categoria;
import com.arllanfelipe.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
	private CategoriaRepository repo;
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		return obj;
	}
}
