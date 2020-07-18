package com.sistemaGestion.repository;

import com.sistemaGestion.model.CargaDeHoras;
import com.sistemaGestion.model.HorasCargadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CargaDeHorasRepository extends JpaRepository<CargaDeHoras, Long> {

    List<CargaDeHoras> findByLegajoAndTareaIdAndProyectoId(String legajo, String tareaId, String proyectoId);

    List<CargaDeHoras> findByLegajoAndTareaIdAndProyectoIdAndFecha(String legajo, String tareaId, String proyectoId, LocalDate parse);
}
