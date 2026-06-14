package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.Empleado;

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
    List<Empleado> findByNombre(String nombre);
    List<Empleado> findByPrimerApellido(String primerApellido);
    List<Empleado> findBySegundoApellido(String segundoApellido);


}