package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.Student;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.exceptions.StudentNotFoundException;
import com.Sophos.SophosUniversity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService{


    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> getAllStudents() throws Exception{
        try{
            return (List<Student>) repository.findAll();
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
    public Student getStudentById(Long id) throws Exception{

        try{
            return repository.findById(id).get();
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new StudentNotFoundException("Estudiante no encontrado con ID: " + id);
        }

    }

    @Override
    public List<Student> getMultipleStudentsById(Iterable<Long> MultipleId) throws Exception{

        try{
            return (List<Student>) repository.findAllById(MultipleId);
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new StudentNotFoundException("Estudiantes no encontrado con esta lista de IDs");
        }

    }

    @Override
    public String updateStudent(Student student) throws Exception{
        Long studentId = student.getStudent_id();
        if(repository.existsById(studentId)){
            try{
                repository.save(student);
                return "Estudiante Actualizado";

            }catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }

        }else{
            throw new StudentNotFoundException("Estudiante no encontrado con el ID: "+ studentId);
        }

    }

    @Override
    public String addNewStudent(Student student) throws Exception{

        try{
            repository.save(student);
            return"Estudiante Registrado";
        }catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error al acceder a la base de datos");
        } catch (RuntimeException ex) {
            throw new InternalServerErrorException("Error interno del servidor");
        }

    }

    @Override
    public String deleteStudent(Long id){
        if(repository.existsById(id)){
            try{
                repository.deleteById(id);
                return "Estudiate eliminado exitosamente";
            } catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }

        }else{
            throw new StudentNotFoundException("Estudiante no encontrado con este ID: "+id);
        }

    }


}
