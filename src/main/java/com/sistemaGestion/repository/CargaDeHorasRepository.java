package com.sistemaGestion.repository;

import com.sistemaGestion.model.CargaDeHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CargaDeHorasRepository extends JpaRepository<CargaDeHoras, Long> {

    List<CargaDeHoras> findByLegajoAndTareaIdAndProyectoId(String legajo, String tareaId, String proyectoId);

    List<CargaDeHoras> findByLegajoAndTareaIdAndProyectoIdAndFecha(String legajo, String tareaId, String proyectoId, LocalDate parse);

    List<CargaDeHoras> findByProyectoIdAndLegajo(String proyectoId, String legajo);

}
