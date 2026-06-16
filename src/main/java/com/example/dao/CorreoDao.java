package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Correo;
import com.example.entities.Empleado;
import java.util.List;

public interface CorreoDao extends JpaRepository<Correo, Integer> {
	boolean existsByEmpleado(Empleado empleado);
	void deleteByEmpleado(Empleado empleado);
	List<Correo> findByEmpleado(Empleado empleado);
	
}