package com.sistemaGestion.repository;

import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, String> {

    Optional<Proyecto> findByCodigo(String codigo);
}
