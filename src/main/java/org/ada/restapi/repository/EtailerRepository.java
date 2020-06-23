package org.ada.restapi.repository;

import java.util.List;

import org.ada.restapi.model.Categoria;
import org.ada.restapi.model.Etailer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtailerRepository extends CrudRepository <Etailer, Integer> {
	@Override
	public List <Etailer> findAll();
	public List <Etailer> findByNombre(String nombre);
	//public List <Etailer> findById(int Id);
	
}
