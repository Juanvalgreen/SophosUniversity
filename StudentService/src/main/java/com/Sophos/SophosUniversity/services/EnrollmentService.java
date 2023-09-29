package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.Enrollment;
import com.Sophos.SophosUniversity.exceptions.CourseNotfoundException;
import com.Sophos.SophosUniversity.exceptions.EnrollmentNotFoundException;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService implements IEnrollmentService{

    @Autowired
    private EnrollmentRepository repository;

    @Override
    public List<Enrollment> getAllEnrollments() throws Exception{
        try{
            return (List<Enrollment>) repository.findAll();
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
    public Enrollment getEnrollmentById(Long id) throws Exception{
        try{
            return repository.findById(id).get();
        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new EnrollmentNotFoundException("curso matriculado no encontrado con ID: " + id);
        }

    }

    @Override
    public List<Enrollment> getEnrollmentsByCourseId(Long id) throws Exception{
        try{
           return (List<Enrollment>) repository.findAllEnrollementsByCourseId(id);
        }catch(DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("No hay cursos matriculados asociados a este estudiante");
        }

    }

    @Override
    public List<Enrollment> getEnrollmentsByStudentId(Long id) throws Exception{
        try{
            return (List<Enrollment>) repository.findAllEnrollementsByStudentId(id);
        }catch(DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("No hay cursos matriculados asociados a este estudiante");
        }

    }


    @Override
    public String addNewEnrollment(Enrollment enrollment) throws Exception{
        try{
            repository.save(enrollment);
            return "Matricula Agregada Exitosamente";
        }catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error al acceder a la base de datos");
        } catch (RuntimeException ex) {
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }


    @Override
    public String updateEnrollment(Enrollment enrollment) throws Exception{
        Long enrollmentId = enrollment.getEnrollment_id();
        if(repository.existsById(enrollmentId)){
            try{
                repository.save(enrollment);
                return "Matricula con el ID:" + enrollmentId +"Actulizada exitosamente";
            }catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }
        }else{
            throw new EnrollmentNotFoundException("curso matriculado no encontrado con ID: " + enrollmentId);
        }
    }


    @Override
    public String deleteEnrollment(Long id) throws Exception{
        if(repository.existsById(id)){
            try{
                repository.deleteById(id);
                return "Matricula eliminada correctamente";
            }catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }
        }else{
            throw new EnrollmentNotFoundException("curso matriculado no encontrado con ID: "+ id);
        }
    }




}
