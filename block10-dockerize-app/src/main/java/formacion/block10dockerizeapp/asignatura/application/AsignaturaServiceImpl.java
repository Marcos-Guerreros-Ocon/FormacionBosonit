package formacion.block10dockerizeapp.asignatura.application;

import formacion.block10dockerizeapp.asignatura.controller.dto.*;
import formacion.block10dockerizeapp.asignatura.domain.Asignatura;
import formacion.block10dockerizeapp.asignatura.repository.AsignaturaRepository;
import formacion.block10dockerizeapp.exception.EntityNotFoundException;
import formacion.block10dockerizeapp.exception.UnprocessableEntityException;
import formacion.block10dockerizeapp.student.domain.Student;
import formacion.block10dockerizeapp.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Autowired
    StudentRepository studentRepository;

    public SimpleAsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto) throws EntityNotFoundException {
        Asignatura asignatura = asignaturaInputDtoToEntity(asignaturaInputDto);
        return asignaturaRepository.save(asignatura).asignaturaToSimpleAsignaturaOutputDto();
    }

    public List<AsignaturaOutputDto> getAllAsignaturas() {

        return asignaturaRepository.findAll().stream().map(Asignatura::asignaturaToAsignaturaOutputDto).toList();

    }

    @Override
    public SimpleAsignaturaOutputDto getAsignaturaById(int id) throws EntityNotFoundException {
        return asignaturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la asignatura con id: " + id)).asignaturaToSimpleAsignaturaOutputDto();
    }

    @Override
    public SimpleAsignaturaOutputDto getFullAsignaturaById(int id) throws EntityNotFoundException {
        return asignaturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la asignatura con id: " + id)).asignaturaToAsignaturaOutputDto();
    }

    @Override
    public SimpleAsignaturaOutputDto updateAsignaturaById(int id, AsignaturaInputDto asignatura) throws EntityNotFoundException, UnprocessableEntityException {
        Asignatura a = asignaturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la asignatura con id: " + id));

        if (asignatura.getInitial_date() == null)
            throw new UnprocessableEntityException("El campo getInitial_date es requerido");

        Set<Student> nuevosEstudiantes = new LinkedHashSet<>();
        for (Integer idEstudiante : asignatura.getEstudiantes()) {
            Student s = studentRepository.findById(idEstudiante).orElseThrow(() -> new EntityNotFoundException("No existe un alumno con el id: " + id));
            s.getAsignaturas().add(a);
            nuevosEstudiantes.add(s);
        }

        if (asignatura.getAsignatura() != null) a.setAsignatura(asignatura.getAsignatura());

        if (asignatura.getComents() != null) a.setAsignatura(asignatura.getComents());

        a.setInitial_date(asignatura.getInitial_date());

        if (asignatura.getFinish_date() != null) a.setFinish_date(asignatura.getFinish_date());

        a.setEstudiantes(nuevosEstudiantes);

        return asignaturaRepository.save(a).asignaturaToAsignaturaOutputDto();
    }

    @Override
    public void deleteAsignaturaById(int id) throws EntityNotFoundException {
        asignaturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se ha encontrado la asignatura con id: " + id));
        asignaturaRepository.deleteById(id);
    }

    @Override
    public List<SimpleAsignaturaOutputDto> getAllAsignaturasByStudent(int idStudent) throws EntityNotFoundException {
        Student s = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException("No se encuentra el alumno con el id: " + idStudent));
        List<Asignatura> asignaturasEstudiante = new ArrayList<>();


        List<Asignatura> asignaturas = asignaturaRepository.findAll();

        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getEstudiantes().contains(s)) asignaturasEstudiante.add(asignatura);
        }

        return asignaturasEstudiante.stream().map(Asignatura::asignaturaToSimpleAsignaturaOutputDto).toList();

    }

    private Asignatura asignaturaInputDtoToEntity(AsignaturaInputDto asignaturaInputDto) throws EntityNotFoundException {
        Asignatura asignatura = new Asignatura();
        Set<Student> estudiantes = new LinkedHashSet<>();


        Optional<Student> optionalStudent;
        for (Integer idStudent : asignaturaInputDto.getEstudiantes()) {
            optionalStudent = studentRepository.findById(idStudent);
            if (optionalStudent.isEmpty())
                throw new EntityNotFoundException("No se ha encontrado el alumno con el id: " + idStudent);

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
