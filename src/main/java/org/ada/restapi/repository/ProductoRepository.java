package org.ada.restapi.repository;
import java.util.List;
import java.util.Optional;


import org.ada.restapi.model.Producto;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
// Spring autoimplementa la interfaz y nos permitirá crear, leer, actualizar y borrar los datos de la tabla Reporte

public interface ProductoRepository extends CrudRepository <Producto, String>{

	@Override
	public List <Producto> findAll();
	
	public Optional<Producto> findById(String Id);
}
