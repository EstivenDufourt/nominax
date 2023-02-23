package com.dafourt.nominax.repositorio;

import com.dafourt.nominax.entidad.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Object> findAllById(Long id);
}