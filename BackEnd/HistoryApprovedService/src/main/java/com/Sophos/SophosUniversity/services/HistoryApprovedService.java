package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.dtos.historyApprovedDTO;
import com.Sophos.SophosUniversity.entities.HistoryApproved;
import com.Sophos.SophosUniversity.exceptions.CourseNotfoundException;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.models.Course;
import com.Sophos.SophosUniversity.models.Students;
import com.Sophos.SophosUniversity.repository.HistoryApprovedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryApprovedService implements IHistoryApprovedService{


    @Autowired
    private HistoryApprovedRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<historyApprovedDTO> getAllApprovedCourses() throws Exception{
        try{
            List<historyApprovedDTO> responseHistoryApproved = new ArrayList<>();

            List<HistoryApproved> historyApproveds = (List<HistoryApproved>) repository.findAll();

            for (HistoryApproved historyApproved : historyApproveds){

                Course course = restTemplate.getForObject("http://localhost:9002/api/v1/courses/" + historyApproved.getCourse_id(), Course.class);
                Students student = restTemplate.getForObject("http://localhost:9005/api/v1/students/" + historyApproved.getStudent_id(), Students.class);

                responseHistoryApproved.add(new historyApprovedDTO(historyApproved,course,student));

            }



           return responseHistoryApproved;
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        }catch (RuntimeException ex) {
            // Manejar otras excepciones de tiempo de ejecución
            ex.printStackTrace();
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }


    @Override
    public List<historyApprovedDTO> getCoursesApprovedByStudentId(Long id) throws Exception{
        try{
            List<historyApprovedDTO> responseHistoryApproved = new ArrayList<>();

            List<HistoryApproved> historyApproveds = repository.findAllCoursesApprovedByStudentId(id);

            for (HistoryApproved historyApproved : historyApproveds){

                Course course = restTemplate.getForObject("http://localhost:9002/api/v1/courses/" + historyApproved.getCourse_id(), Course.class);
                Students student = restTemplate.getForObject("http://localhost:9005/api/v1/students/" + historyApproved.getStudent_id(), Students.class);

                responseHistoryApproved.add(new historyApprovedDTO(historyApproved,course,student));

            }



            return responseHistoryApproved;
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        }catch (RuntimeException ex) {
            // Manejar otras excepciones de tiempo de ejecución
            ex.printStackTrace();
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }

    @Override
    public String addNewApprovedCourse(HistoryApproved historyApproved) throws Exception{
        try{
            repository.save(historyApproved);
            return "Curso aprobado registrado exitosamente";
        }catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error al acceder a la base de datos");
        } catch (RuntimeException ex) {
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }

    @Override
    public String updateApprovedCourse(HistoryApproved historyApproved) throws Exception{
        Long ApprovedCourseId = historyApproved.getHistory_courses_id();
        if(repository.existsById(ApprovedCourseId)){
            try{
                repository.save(historyApproved);
                return "Curso aprobado actualizado exitosamente";
            }catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }

        } else {
            throw new CourseNotfoundException("Curso aprobado no encontrado con ID: " + ApprovedCourseId);
        }
    }

    @Override
    public String deleteApprovedCourse(Long id) throws Exception{
        if(repository.existsById(id)){
            try{
                repository.deleteById(id);
                return "Curso aprobado ha eliminado exitosamente";
            }catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }

        } else {
            throw new CourseNotfoundException("Curso aprobado no encontrado con ID: " + id);
        }


    }


}
