package org.ada.restapi.repository;
import java.util.List;


import org.ada.restapi.model.ReporteSearch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
// Spring autoimplementa la interfaz y nos permitirá crear, leer, actualizar y borrar los datos de la tabla Reporte

@Repository
public interface ReporteSearchRepository extends CrudRepository <ReporteSearch, Integer>{
	@Override
	public List<ReporteSearch> findAll(); 
		
	public List <ReporteSearch> findById(int Id);
	
}
