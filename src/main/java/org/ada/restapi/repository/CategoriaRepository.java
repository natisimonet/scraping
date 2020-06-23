package org.ada.restapi.repository;

import java.util.List;

import org.ada.restapi.model.Categoria;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository <Categoria,Integer> {
	
	@Override
	public List <Categoria> findAll();
	
	public List <Categoria> findById(int Id);
	public List <Categoria> findByNombre (String nombre);
	
	

}
