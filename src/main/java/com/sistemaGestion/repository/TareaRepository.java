package com.sistemaGestion.repository;

import com.sistemaGestion.model.Empleado;
import com.sistemaGestion.model.Tarea;
import com.sistemaGestion.model.TareaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, TareaId> {

    Optional<Tarea> findById(TareaId tareaId);

}
