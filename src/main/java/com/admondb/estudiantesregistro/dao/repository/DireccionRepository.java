package com.admondb.estudiantesregistro.dao.repository;

import com.admondb.estudiantesregistro.model.CategoriaDireccion;
import com.admondb.estudiantesregistro.model.Direccion;
import com.admondb.estudiantesregistro.model.Estudiante;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    @Query("select d from Direccion d where d.estudiante.id = :#{#id} and d.categoria = :#{#trabajo}")
    Direccion encontrarTrabajo(Long id, CategoriaDireccion trabajo);

    @Query("select d from Direccion d where d.estudiante.id = :#{#id} and d.categoria = :#{#residencia}")
    Direccion encontrarResidencia(Long id, CategoriaDireccion residencia);

    @Modifying
    @Transactional
    @Query("DELETE FROM Direccion d WHERE d = :direccion")
    void eliminarDireccion(Direccion direccion);

    @Query("select e.cedula, e.nombres, e.apellidos, ST_Distance_Sphere(ur.coordenadas, ut.coordenadas) as distancia " +
            "FROM Estudiante e " +
            "JOIN Direccion dr on e = dr.estudiante and dr.categoria = 'RESIDENCIA'" +
            "JOIN Direccion dt on e = dt.estudiante and dt.categoria = 'TRABAJO'" +
            "JOIN Ubicacion ur on ur = dr.ubicacion " +
            "JOIN Ubicacion ut on ut = dt.ubicacion " +
            "ORDER BY distancia asc")
    List<Object[]> obtenerEstudiantesDistancia();
}
