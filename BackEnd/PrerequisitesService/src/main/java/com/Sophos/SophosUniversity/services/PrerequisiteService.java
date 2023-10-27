package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.dtos.prerequisiteDTO;
import com.Sophos.SophosUniversity.entities.Prerequisite;
import com.Sophos.SophosUniversity.exceptions.CourseNotfoundException;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.models.Course;
import com.Sophos.SophosUniversity.repository.PrerequisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrerequisiteService implements IPrerequisiteService {

    @Autowired
    private PrerequisiteRepository repository;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<prerequisiteDTO> getAllPrerequisites() throws Exception {

        try {
            List<prerequisiteDTO> responsePrerequistes = new ArrayList<>();

            List<Prerequisite>  prerequisites =(List<Prerequisite>) repository.findAll();

            for(Prerequisite prerequisite : prerequisites){

                Course course = restTemplate.getForObject("http://localhost:9002/api/v1/courses/"+ prerequisite.getCourse_id(), Course.class);
                Course coursePrerequiste = restTemplate.getForObject("http://localhost:9002/api/v1/courses/"+ prerequisite.getPrerequisite_course_id(), Course.class);

                responsePrerequistes.add(new prerequisiteDTO(prerequisite, course,coursePrerequiste));
            }

            return responsePrerequistes;
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
    public List<prerequisiteDTO> getPrerequistesByCourseId(Long id) throws Exception {
        try {
            List<prerequisiteDTO> responsePrerequistes = new ArrayList<>();

            List<Prerequisite>  prerequisites =(List<Prerequisite>) repository.findAllPrerequisitesByCourseId(id);

            for(Prerequisite prerequisite : prerequisites){

                Course course = restTemplate.getForObject("http://localhost:9002/api/v1/courses/"+ prerequisite.getCourse_id(), Course.class);
                Course coursePrerequiste = restTemplate.getForObject("http://localhost:9002/api/v1/courses/"+ prerequisite.getPrerequisite_course_id(), Course.class);

                responsePrerequistes.add(new prerequisiteDTO(prerequisite, course,coursePrerequiste));
            }

            return responsePrerequistes;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch (RuntimeException ex) {
            throw new CourseNotfoundException("No hay cursos asociados a este Curso ");
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