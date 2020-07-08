package com.sistemaGestion.repository;

import com.sistemaGestion.model.CargaDeHoras;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargaDeHorasRepository extends JpaRepository<CargaDeHoras, Long> {

    public CargaDeHoras findByLegajoEmpleado(String legajo);
}
