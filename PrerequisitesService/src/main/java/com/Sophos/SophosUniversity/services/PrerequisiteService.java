package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.Prerequisite;
import com.Sophos.SophosUniversity.exceptions.CourseNotfoundException;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.repository.PrerequisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrerequisiteService implements IPrerequisiteService {

    @Autowired
    private PrerequisiteRepository repository;


    @Override
    public List<Prerequisite> getAllPrerequisites() throws Exception {

        try {
            return (List<Prerequisite>) repository.findAll();
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch (RuntimeException ex) {
            // Manejar otras excepciones de tiempo de ejecución
            ex.printStackTrace();
            throw new InternalServerErrorException("Error interno del servidor");
        }

    }

    @Override
    public List<Prerequisite> getPrerequistesByCourseId(Long id) throws Exception {
        try {
            return repository.findAllPrerequisitesByCourseId(id);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch (RuntimeException ex) {
            throw new CourseNotfoundException("No hay cursos asociados a este profesor ");
        }
    }


    @Override
    public String updatePrerequiste(Prerequisite prerequisite) throws Exception {

        Long prerequisiteId = prerequisite.getPrerequisite_id();
        if (repository.existsById(prerequisiteId)) {
            try {
                repository.save(prerequisite);
                return "Prerequisito actualizado Exitosamente";
            } catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }

        } else {
            throw new CourseNotfoundException("Curso no encontrado con ID: " + prerequisiteId);
        }


    }


    @Override
    public String addNewPrerequisite(Prerequisite prerequisite) throws Exception {
        try {
            repository.save(prerequisite);
            return "prerequisito agregado exitosamente";
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error al acceder a la base de datos");
        } catch (RuntimeException ex) {
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }


    @Override
    public String deletePrerequisite(Long id) throws Exception{

        if(repository.existsById(id)){
            try{
                repository.deleteById(id);
                return "Prerequisito eliminado correctamente";
            }catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }
        }else{
            throw new CourseNotfoundException("Prerequisito no encontrado con este id");
        }


    }

}