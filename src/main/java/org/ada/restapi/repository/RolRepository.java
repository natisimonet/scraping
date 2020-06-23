package org.ada.restapi.repository;
import java.util.Optional;

import org.ada.restapi.enums.RolNombre;
import org.ada.restapi.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository <Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);


}
