package com.example.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Departamento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String nombre;

        /*
         * Las relaciones entre las entidades en JPA(Java Persistence Api) son
         * bidireccionales a diferencia de las relaciones en SQL que son
         * unidireccionales, es decir que una entidad hija, sabe quien es
         * su padre porque en dicha entidad es donde se crea la relación de clave
         * externa (Foreing Key) pero el padre no sabe que tiene hijo(s)
         */

        /*
         * La relacion la define la relacion OneToMany pero el tipo de busqueda es
         * definido por Fecht, en este caso busqueda "perezosa/LAZY" no se atraen los
         * empleados hasta que haga falta
         */

        /*
         * El efecto cascada que se implementa con cada acción de los empleados es que
         * si elimino el empleado desaparece de la tabla de departamentos REMOVE
         */

        /*
         * El atributo mapedby apunta a una propiedad en el lado de muchos de la
         * relación, pues aunque la relaciones son bidireccionales hay que especificar
         * donde se va a crear la relación de clave externa, que al igual
         * que en SQL es en el lado de muchos(un Departamento muchos empleados)
         */
        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "departamento")
        private List<Empleado> empleados;

        
}
