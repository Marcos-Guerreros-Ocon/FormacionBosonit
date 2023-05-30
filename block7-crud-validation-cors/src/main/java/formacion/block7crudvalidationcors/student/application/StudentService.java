package formacion.block7crudvalidationcors.student.application;

import formacion.block7crudvalidationcors.exception.EntityNotFoundException;
import formacion.block7crudvalidationcors.exception.UnprocessableEntityException;
import formacion.block7crudvalidationcors.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidationcors.student.controller.dto.StudentInputDto;

import java.util.List;

public interface StudentService {
    SimpleStudentOutputDto addStudent(StudentInputDto student) throws UnprocessableEntityException, EntityNotFoundException;

    SimpleStudentOutputDto getStudentById(int id) throws EntityNotFoundException;

    public SimpleStudentOutputDto getSimpleStudentById(int id) throws EntityNotFoundException;

    void deleteStudentById(int id) throws EntityNotFoundException;

    Iterable<SimpleStudentOutputDto> getAllStudent();

    SimpleStudentOutputDto updateStudent(int id, StudentInputDto person) throws EntityNotFoundException, UnprocessableEntityException;

    SimpleStudentOutputDto addAsignaturas(int id, List<Integer> idAsignaturas) throws EntityNotFoundException;
    SimpleStudentOutputDto removeAsignaturas(int id, List<Integer> idAsignaturas) throws EntityNotFoundException;
}
