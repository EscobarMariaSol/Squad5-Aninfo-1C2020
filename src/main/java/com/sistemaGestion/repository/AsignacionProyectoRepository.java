package com.sistemaGestion.repository;

import com.sistemaGestion.model.AsignacionProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AsignacionProyectoRepository extends JpaRepository<AsignacionProyecto, Long> {

}
