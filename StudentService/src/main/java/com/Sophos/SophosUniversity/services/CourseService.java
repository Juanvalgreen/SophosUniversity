package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.Course;
import com.Sophos.SophosUniversity.exceptions.CourseNotfoundException;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.exceptions.StudentNotFoundException;
import com.Sophos.SophosUniversity.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService{

    @Autowired
    private CourseRepository repository;

    @Override
    public List<Course> getAllCourses() throws Exception{
        try{
            return (List<Course>) repository.findAll();
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
    public Course getCourseById(Long id) throws Exception {


        try{
            return repository.findById(id).get();
        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("Curso no encontrado con ID: " + id);
        }


    }

    @Override
    public List<Course> getMultipleCourseById(Iterable<Long> MultipleId) throws Exception{

        try{
            return (List<Course>) repository.findAllById(MultipleId);
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new StudentNotFoundException("Estudiantes no encontrado con esta lista de IDs");
        }

    }

    @Override
    public List<Course> getCoursesByTeacherId(Long teacherId) throws Exception{

        try{
            return repository.findAllCoursesByTeacherId(teacherId);
        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("No hay cursos asociados a este profesor ");
        }

    }

    @Override
    public List<Course> getCoursesByMonitorId(Long monitorId) throws Exception{

        try{
            return repository.findAllCoursesByMonitorId(monitorId);
        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("No hay cursos asociados a este estudiante como monitor ");
        }

    }


    @Override
    public String deleteCourse(Long id) throws Exception{
        if (repository.existsById(id)) {
            try {
                repository.deleteById(id);
                return "Curso eliminado con éxito"; // Mensaje de éxito
            } catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }
        } else {
            throw new CourseNotfoundException("Curso no encontrado con ID: " + id);
        }
    }

    @Override
    public String addNewCourse(Course course) throws Exception{
        try{
            repository.save(course);
            return "Curso agregado";
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error al acceder a la base de datos");
        } catch (RuntimeException ex) {
            throw new InternalServerErrorException("Error interno del servidor");
        }

    }

    @Override
    public String updateCourse(Course course) throws Exception{
        Long courseId = course.getCourse_id();
        if (repository.existsById(courseId)) {
            try{
                repository.save(course);
                return "Curso con el id"+ courseId +"actualizado ";
            }catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }

        } else {
            throw new CourseNotfoundException("Curso no encontrado con ID: " + courseId);
        }

    }



}
