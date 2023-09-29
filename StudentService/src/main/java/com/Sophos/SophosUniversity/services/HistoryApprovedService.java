package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.HistoryApproved;
import com.Sophos.SophosUniversity.exceptions.CourseNotfoundException;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.repository.HistoryApprovedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryApprovedService implements IHistoryApprovedService{


    @Autowired
    private HistoryApprovedRepository repository;

    @Override
    public List<HistoryApproved> getAllApprovedCourses() throws Exception{
        try{
           return (List<HistoryApproved>) repository.findAll();
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
    public List<HistoryApproved> getCoursesApprovedByStudentId(Long id) throws Exception{
        try{
            return repository.findAllCoursesApprovedByStudentId(id);
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
