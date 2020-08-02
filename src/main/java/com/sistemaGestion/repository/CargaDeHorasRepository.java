package com.sistemaGestion.repository;

import com.sistemaGestion.model.CargaDeHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CargaDeHorasRepository extends JpaRepository<CargaDeHoras, Long> {

    List<CargaDeHoras> findByLegajoAndTareaIdAndProyectoId(String legajo, String tareaId, Long proyectoId);

    List<CargaDeHoras> findByLegajoAndTareaIdAndProyectoIdAndFecha(String legajo, String tareaId, Long proyectoId, LocalDate parse);

    List<CargaDeHoras> findByProyectoIdAndLegajo(Long proyectoId, String legajo);

    List<CargaDeHoras> findByLegajoAndTareaIdAndProyectoIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
            String legajo, String tareaId, Long proyectoId, LocalDate fecha1, LocalDate fecha2);

    List<CargaDeHoras> findByLegajoAndProyectoIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
            String legajo, Long proyectoId, LocalDate fecha1, LocalDate fecha2);

    List<CargaDeHoras> findByLegajoAndTareaIdAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
            String legajo, String tareaId, LocalDate fecha1, LocalDate fecha2);

    List<CargaDeHoras> findByLegajoAndFechaIsGreaterThanEqualAndFechaIsLessThanEqual(
            String legajo, LocalDate fecha1, LocalDate fecha2);

}
