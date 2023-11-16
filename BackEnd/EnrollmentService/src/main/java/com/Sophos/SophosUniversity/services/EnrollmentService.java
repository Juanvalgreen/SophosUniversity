package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.config.RestConts;
import com.Sophos.SophosUniversity.dtos.enrollmentDTO;
import com.Sophos.SophosUniversity.entities.Enrollment;
import com.Sophos.SophosUniversity.exceptions.CourseNotfoundException;
import com.Sophos.SophosUniversity.exceptions.EnrollmentNotFoundException;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.models.*;
import com.Sophos.SophosUniversity.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService implements IEnrollmentService{

    @Autowired
    private EnrollmentRepository repository;

    @Autowired
    RestTemplate restTemplate;

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
    public List<enrollmentDTO> getEnrollmentsByCourseId(Long id) throws Exception{
        try{
            List<enrollmentDTO> responseEnrollment = new ArrayList<>();

            List<Enrollment> enrollments = (List<Enrollment>) repository.findAllEnrollementsByCourseId(id);

            for(Enrollment enrollment : enrollments){

                Courses course = restTemplate.getForObject(RestConts.BASE_URL_COURSES_DEPLOY + "/api/v1/courses/" + enrollment.getCourse_id(), Courses.class);

                Students student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + enrollment.getStudent_id(), Students.class);


                responseEnrollment.add(new enrollmentDTO(enrollment,student,course));


            }



           return responseEnrollment;
        }catch(DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("No hay cursos matriculados asociados a este estudiante");
        }

    }

    @Override
    public List<enrollmentDTO> getEnrollmentsByStudentId(Long id) throws Exception{
        try{
            List<enrollmentDTO> responseEnrollment = new ArrayList<>();

            List<Enrollment> enrollments = (List<Enrollment>) repository.findAllEnrollementsByStudentId(id);

            for(Enrollment enrollment : enrollments){

                Courses course = restTemplate.getForObject(RestConts.BASE_URL_COURSES_DEPLOY + "/api/v1/courses/" + enrollment.getCourse_id(), Courses.class);

                Students student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + enrollment.getStudent_id(), Students.class);


                responseEnrollment.add(new enrollmentDTO(enrollment,student,course));


            }


            return responseEnrollment;
        }catch(DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new CourseNotfoundException("No hay cursos matriculados asociados a este estudiante");
        }

    }


    @Override
    public String addNewEnrollment(Enrollment enrollment) throws Exception {

        Long studentId = enrollment.getStudent_id();
        Long courseId = enrollment.getCourse_id();

        try {

            // Obtener información del curso
            Courses course = restTemplate.getForObject(RestConts.BASE_URL_COURSES_DEPLOY + "/api/v1/courses/" + courseId, Courses.class);

            if (course != null) {
                // Verificar si hay cupos disponibles
                if (course.getAvailable_places() > 0) {

                    // Obtener lista de prerequisitos para el curso
                    List<Prerequisites> prerequisites = restTemplate.getForObject(RestConts.BASE_URL_PREREQUISITES_DEPLOY + "/api/v1/prerequisites/" + courseId + "/courses", List.class);

                    // Verificar si el estudiante ha completado los prerequisitos
                    boolean hasCompletedPrerequisites = checkCompletedPrerequisites(studentId, prerequisites);

                    if (hasCompletedPrerequisites) {
                        // Obtener información del estudiante
                        Students student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + studentId, Students.class);

                        if (student != null) {
                            // Verificar si el estudiante tiene suficientes créditos disponibles
                            boolean hasEnoughCredits = checkEnoughCredits(student, course);

                            if (hasEnoughCredits) {
                                // Realizar la matrícula (guardar en la base de datos)
                                course.setAvailable_places(course.getAvailable_places() - 1);
                                student.setEnrolled_credits(student.getEnrolled_credits() + course.getAmount_credits());

                                restTemplate.put(RestConts.BASE_URL_COURSES_DEPLOY + "/api/v1/courses", course, Courses.class);
                                restTemplate.put(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students", student, Students.class);
                                repository.save(enrollment);
                                return "Matrícula Agregada Exitosamente";
                            } else {
                                return "El estudiante no tiene suficientes créditos disponibles.";
                            }
                        } else {
                            return "No se encontró información del estudiante.";
                        }
                    } else {
                        return "El estudiante no ha completado los cursos prerequisito.";
                    }
                } else {
                    return "No hay cupos disponibles en el curso.";
                }
            } else {
                return "No se encontró información del curso.";
            }
        } catch (RestClientException ex) {
            // Manejar errores de llamadas a la API externa
            ex.printStackTrace();
            //return "el id " + studentId;
            throw new InternalServerErrorException("Error al acceder a los servicios externos");
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error al acceder a la base de datos");
        } catch (RuntimeException ex) {
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }

    private boolean checkEnoughCredits(Students student, Courses course) {

        Integer credits = student.getAvailable_credits() - student.getEnrolled_credits();

        if (credits >= course.getAmount_credits()) {
            return true;
        }else{
            return false;
        }



    }

    private boolean checkCompletedPrerequisites(Long studentId, List<Prerequisites> prerequisites) {


        List<ApprovedCourses> approvedCourses = restTemplate.getForObject(RestConts.BASE_URL_APPROVED_DEPLOY + "/api/v1/approvedCourses/" + studentId + "/students", List.class);

        if(prerequisites.isEmpty()){
            return true;
        }else{

            for (Prerequisites prerequisite: prerequisites) {
                Long prerequisiteCourseId = prerequisite.getPrerequisite_course_id();

                boolean prerequisiteCompleted = approvedCourses.stream().anyMatch(course -> course.getCourse_id().equals(prerequisiteCourseId));

                if(!prerequisiteCompleted) {
                    return false;
                }
            }
            return true;

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
                Enrollment enrollment = getEnrollmentById(id);

                Courses course = restTemplate.getForObject(RestConts.BASE_URL_COURSES_DEPLOY + "/api/v1/courses/" + enrollment.getCourse_id() , Courses.class);
                Students student = restTemplate.getForObject(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students/" + enrollment.getStudent_id(), Students.class);

                course.setAvailable_places(course.getAvailable_places() + 1);
                student.setEnrolled_credits(student.getEnrolled_credits() - course.getAmount_credits());


                restTemplate.put(RestConts.BASE_URL_COURSES_DEPLOY + "/api/v1/courses", course, Courses.class);
                restTemplate.put(RestConts.BASE_URL_STUDENTS_DEPLOY + "/api/v1/students", student, Students.class);
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
