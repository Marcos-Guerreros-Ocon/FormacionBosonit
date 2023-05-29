package formacion.block10dockerizeapp.teacher.application;

import formacion.block10dockerizeapp.exception.EntityNotFoundException;
import formacion.block10dockerizeapp.exception.UnprocessableEntityException;
import formacion.block10dockerizeapp.teacher.controller.dto.SimpleTeacherOutputDto;
import formacion.block10dockerizeapp.teacher.controller.dto.TeacherInputDto;

public interface TeacherService {
    SimpleTeacherOutputDto addTeacher(TeacherInputDto person) throws UnprocessableEntityException, EntityNotFoundException;

    SimpleTeacherOutputDto getTeacherById(int id) throws EntityNotFoundException;

    void deleteTeacherById(int id) throws EntityNotFoundException;

    Iterable<SimpleTeacherOutputDto> getAllTeacher();

    SimpleTeacherOutputDto updateTeacher(int id, TeacherInputDto person) throws EntityNotFoundException, UnprocessableEntityException;
}
