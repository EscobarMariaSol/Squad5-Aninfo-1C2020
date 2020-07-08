package com.sistemaGestion.repository;

import com.sistemaGestion.model.AsignacionProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsignacionProyectoRepository extends JpaRepository<AsignacionProyecto, String> {

    Optional<AsignacionProyecto> findByCodigo(String codigo);
}
