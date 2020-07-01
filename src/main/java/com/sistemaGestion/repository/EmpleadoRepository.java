package com.sistemaGestion.repository;

import com.sistemaGestion.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByLegajo(String legajo);

    Optional<Empleado> findByLegajoAndActivoIsTrue(String legajo);

    List<Empleado> findAllByActivoIsTrue();
}
