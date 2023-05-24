package formacion.block7crudvalidation.asignatura.application;

import formacion.block7crudvalidation.asignatura.controller.dto.*;
import formacion.block7crudvalidation.asignatura.domain.Asignatura;
import formacion.block7crudvalidation.asignatura.repository.AsignaturaRepository;
import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.student.domain.Student;
import formacion.block7crudvalidation.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Autowired
    StudentRepository studentRepository;

    public AsignaturaOutputDto añadirAsignatura(AsignaturaInputDto asignaturaInputDto) throws EntityNotFoundException {
        Asignatura asignatura = asignaturaInputDtoToEntity(asignaturaInputDto);
        return asignaturaRepository.save(asignatura).asignaturaToAsignaturaOutputDto();
    }

    private Asignatura asignaturaInputDtoToEntity(AsignaturaInputDto asignaturaInputDto) throws EntityNotFoundException {
        Asignatura asignatura = new Asignatura();
        List<Student> estudiantes = new ArrayList<>();


        Optional<Student> optionalStudent;
        for (Integer idStudent : asignaturaInputDto.getEstudiantes()) {
            optionalStudent = studentRepository.findById(idStudent);
            if (optionalStudent.isEmpty())
                throw new EntityNotFoundException("No se ha encontrado el alumno con el id: " + idStudent, HttpStatus.NOT_FOUND.value(), LocalDateTime.now());

            estudiantes.add(optionalStudent.get());
        }
        asignatura.setEstudiantes(estudiantes);

        asignatura.setAsignatura(asignaturaInputDto.getAsignatura());
        asignatura.setComents(asignaturaInputDto.getComents());
        asignatura.setInitial_date(asignaturaInputDto.getInitial_date());
        asignatura.setFinish_date(asignaturaInputDto.getFinish_date());

        return asignatura;

    }
}
