package com.sistemaGestion.repository;

import com.sistemaGestion.model.Tarea;
import com.sistemaGestion.model.TareaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, TareaId> {

}
