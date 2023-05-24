package formacion.block7crudvalidation.student.application;

import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.student.controller.dto.SimpleStudentOutputDto;
import formacion.block7crudvalidation.student.controller.dto.StudentInputDto;

public interface StudentService {
    SimpleStudentOutputDto addStudent(StudentInputDto student) throws UnprocessableEntityException, EntityNotFoundException;

    SimpleStudentOutputDto getStudentById(int id) throws EntityNotFoundException;

    public SimpleStudentOutputDto getSimpleStudentById(int id) throws EntityNotFoundException;

    void deleteStudentById(int id) throws EntityNotFoundException;

    Iterable<SimpleStudentOutputDto> getAllStudent();

    SimpleStudentOutputDto updateStudent(int id, StudentInputDto person) throws EntityNotFoundException, UnprocessableEntityException;
}
