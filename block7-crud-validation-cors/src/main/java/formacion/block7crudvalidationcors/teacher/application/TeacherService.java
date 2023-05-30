package formacion.block7crudvalidationcors.teacher.application;

import formacion.block7crudvalidationcors.exception.EntityNotFoundException;
import formacion.block7crudvalidationcors.exception.UnprocessableEntityException;
import formacion.block7crudvalidationcors.teacher.controller.dto.SimpleTeacherOutputDto;
import formacion.block7crudvalidationcors.teacher.controller.dto.TeacherInputDto;

public interface TeacherService {
    SimpleTeacherOutputDto addTeacher(TeacherInputDto person) throws UnprocessableEntityException, EntityNotFoundException;

    SimpleTeacherOutputDto getTeacherById(int id) throws EntityNotFoundException;

    void deleteTeacherById(int id) throws EntityNotFoundException;

    Iterable<SimpleTeacherOutputDto> getAllTeacher();

    SimpleTeacherOutputDto updateTeacher(int id, TeacherInputDto person) throws EntityNotFoundException, UnprocessableEntityException;
}
