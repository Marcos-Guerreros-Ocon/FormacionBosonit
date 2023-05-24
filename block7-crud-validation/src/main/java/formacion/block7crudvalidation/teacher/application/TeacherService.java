package formacion.block7crudvalidation.teacher.application;

import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.exception.UnprocessableEntityException;
import formacion.block7crudvalidation.teacher.controller.dto.SimpleTeacherOutputDto;
import formacion.block7crudvalidation.teacher.controller.dto.TeacherInputDto;

public interface TeacherService {
    SimpleTeacherOutputDto addTeacher(TeacherInputDto person) throws UnprocessableEntityException, EntityNotFoundException;

    SimpleTeacherOutputDto getTeacherById(int id) throws EntityNotFoundException;

    void deleteTeacherById(int id) throws EntityNotFoundException;

    Iterable<SimpleTeacherOutputDto> getAllTeacher();

    SimpleTeacherOutputDto updateTeacher(int id, TeacherInputDto person) throws EntityNotFoundException, UnprocessableEntityException;
}
