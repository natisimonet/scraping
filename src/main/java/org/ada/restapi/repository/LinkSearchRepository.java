package org.ada.restapi.repository;

import java.util.List;

import org.ada.restapi.model.Etailer;
import org.ada.restapi.model.Link;
import org.ada.restapi.model.LinkSearch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
// Spring autoimplementa la interfaz y nos permitirá crear, leer, actualizar y borrar los datos de la tabla Reporte

@Repository
public interface LinkSearchRepository extends CrudRepository<LinkSearch, Integer> {

	@Override
	public List<LinkSearch> findAll();

	public List<LinkSearch> findByEtailer(Etailer etailer);

}
