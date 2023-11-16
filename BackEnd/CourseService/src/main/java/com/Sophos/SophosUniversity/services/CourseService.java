package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.config.RestConts;
import com.Sophos.SophosUniversity.dtos.courseDTO;
import com.Sophos.SophosUniversity.entities.Course;
import com.Sophos.SophosUniversity.exceptions.CourseNotfoundException;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.exceptions.StudentNotFoundException;
import com.Sophos.SophosUniversity.models.Enrollments;
import com.Sophos.SophosUniversity.models.Students;
import com.Sophos.SophosUniversity.models.Teachers;
import com.Sophos.SophosUniversity.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService implements ICourseService{

    @Autowired
    private CourseRepository repository;


    @Autowired
    RestTemplate restTemplate;



    @Override
    public List<courseDTO> getAllCourses() throws Exception{
        try{
            List<courseDTO> responseCourse = new ArrayList<>();

            List<Course> courses = (List<Course>) repository.findAll();

            for(Course course : courses){

                Teachers teacher= null;
                Students student= null;

                if(course.getTeacher_id() != null){
                    teacher = restTemplate.getForObject(RestConts.BASE_URL_TEACHERS_DEPLOY + "/api/v1/teachers/" + course.getTeacher_id(), Teachers.class);
                }

                if(course.getCourse_student_monitor_id() != null){
                    student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + course.getCourse_student_monitor_id(), Students.class);
                }

                responseCourse.add(new courseDTO(course,teacher,student));
            }



            return responseCourse;
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
    public List<courseDTO> searchAllCoursesById(Long id) throws Exception{
        try{

            List<courseDTO> responseCourse = new ArrayList<>();

            List<Course> courses = repository.searchAllCoursesById(id);

            for(Course course : courses){

                Teachers teacher= null;
                Students student= null;

                if(course.getTeacher_id() != null){
                    teacher = restTemplate.getForObject(RestConts.BASE_URL_TEACHERS_DEPLOY + "/api/v1/teachers/" + course.getTeacher_id(), Teachers.class);
                }

                if(course.getCourse_student_monitor_id() != null){
                    student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + course.getCourse_student_monitor_id(), Students.class);
                }

                responseCourse.add(new courseDTO(course,teacher,student));
            }

            return responseCourse;

        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        }catch (RuntimeException ex) {
            // Manejar otras excepciones de tiempo de ejecución
            ex.printStackTrace();
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }




    @Override
    public List<courseDTO> searchAllCoursesByName(String name) throws Exception{
        try{

            List<courseDTO> responseCourse = new ArrayList<>();
            List<Course> courses = repository.searchAllCoursesByName(name);

            for(Course course : courses){

                Teachers teacher= null;
                Students student= null;

                if(course.getTeacher_id() != null){
                    teacher = restTemplate.getForObject(RestConts.BASE_URL_TEACHERS_DEPLOY + "/api/v1/teachers/" + course.getTeacher_id(), Teachers.class);
                }

                if(course.getCourse_student_monitor_id() != null){
                    student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + course.getCourse_student_monitor_id(), Students.class);
                }

                responseCourse.add(new courseDTO(course,teacher,student));
            }

            return responseCourse;

        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        }catch (RuntimeException ex) {
            // Manejar otras excepciones de tiempo de ejecución
            ex.printStackTrace();
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }


    @Override
    public courseDTO getCourseById(Long id) throws Exception {

        try{

            Course course = repository.findById(id).get();

            Teachers teacher= null;
            Students student= null;

            if(course.getTeacher_id() != null){
                teacher = restTemplate.getForObject(RestConts.BASE_URL_TEACHERS_DEPLOY + "/api/v1/teachers/" + course.getTeacher_id(), Teachers.class);
            }

            if(course.getCourse_student_monitor_id() != null){
                student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + course.getCourse_student_monitor_id(), Students.class);
            }

            return new courseDTO(course,teacher,student);
        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("Curso no encontrado con ID: " + id);
        }


    }


    @Override
    public List<courseDTO> getMultipleCourseById(Iterable<Long> MultipleId) throws Exception{

        try{


            List<courseDTO> responseCourse = new ArrayList<>();
            List<Course> courses = (List<Course>) repository.findAllById(MultipleId);

            for(Course course : courses){

                Teachers teacher= null;
                Students student= null;

                if(course.getTeacher_id() != null){
                    teacher = restTemplate.getForObject(RestConts.BASE_URL_TEACHERS_DEPLOY + "/api/v1/teachers/" + course.getTeacher_id(), Teachers.class);
                }

                if(course.getCourse_student_monitor_id() != null){
                    student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + course.getCourse_student_monitor_id(), Students.class);
                }

                responseCourse.add(new courseDTO(course,teacher,student));
            }

            return responseCourse;

        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new StudentNotFoundException("Estudiantes no encontrado con esta lista de IDs");
        }

    }

    @Override
    public List<courseDTO> getCoursesByTeacherId(Long teacherId) throws Exception{

        try{

            List<courseDTO> responseCourse = new ArrayList<>();
            List<Course> courses = repository.findAllCoursesByTeacherId(teacherId);

            for(Course course : courses){

                Teachers teacher= null;
                Students student= null;

                if(course.getTeacher_id() != null){
                    teacher = restTemplate.getForObject(RestConts.BASE_URL_TEACHERS_DEPLOY + "/api/v1/teachers/" + course.getTeacher_id(), Teachers.class);
                }

                if(course.getCourse_student_monitor_id() != null){
                    student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + course.getCourse_student_monitor_id(), Students.class);
                }

                responseCourse.add(new courseDTO(course,teacher,student));
            }

            return responseCourse;
        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("No hay cursos asociados a este profesor ");
        }

    }

    @Override
    public List<courseDTO> getCoursesByMonitorId(Long monitorId) throws Exception{

        try{

            List<courseDTO> responseCourse = new ArrayList<>();
            List<Course> courses = repository.findAllCoursesByMonitorId(monitorId);

            for(Course course : courses){

                Teachers teacher= null;
                Students student= null;

                if(course.getTeacher_id() != null){
                    teacher = restTemplate.getForObject(RestConts.BASE_URL_TEACHERS_DEPLOY + "/api/v1/teachers/" + course.getTeacher_id(), Teachers.class);
                }

                if(course.getCourse_student_monitor_id() != null){
                    student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + course.getCourse_student_monitor_id(), Students.class);
                }

                responseCourse.add(new courseDTO(course,teacher,student));
            }

            return responseCourse;
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

                ResponseEntity<Enrollments[]> responseEntity= restTemplate.getForEntity(RestConts.BASE_URL_ENROLLMENTS_DEPLOY +  "/api/v1/enrollments/"+id+"/courses", Enrollments[].class);
                List<Enrollments> enrollments = Arrays.asList(responseEntity.getBody());


                for (Enrollments enroll : enrollments) {

                    restTemplate.delete(RestConts.BASE_URL_ENROLLMENTS_DEPLOY +  "/api/v1/enrollments/"+enroll.getEnrollment_id());

                }




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
