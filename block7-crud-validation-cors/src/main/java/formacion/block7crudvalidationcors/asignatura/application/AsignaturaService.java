package formacion.block7crudvalidationcors.asignatura.application;

import formacion.block7crudvalidationcors.asignatura.controller.dto.AsignaturaInputDto;
import formacion.block7crudvalidationcors.asignatura.controller.dto.AsignaturaOutputDto;
import formacion.block7crudvalidationcors.asignatura.controller.dto.SimpleAsignaturaOutputDto;
import formacion.block7crudvalidationcors.exception.EntityNotFoundException;
import formacion.block7crudvalidationcors.exception.UnprocessableEntityException;

import java.util.List;

public interface AsignaturaService {
    SimpleAsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto) throws EntityNotFoundException;
    List<AsignaturaOutputDto> getAllAsignaturas();

    SimpleAsignaturaOutputDto getAsignaturaById(int id) throws EntityNotFoundException;
    SimpleAsignaturaOutputDto getFullAsignaturaById(int id) throws EntityNotFoundException;

    SimpleAsignaturaOutputDto updateAsignaturaById(int id,AsignaturaInputDto asignatura) throws EntityNotFoundException, UnprocessableEntityException;

    void deleteAsignaturaById(int id) throws EntityNotFoundException;
    List<SimpleAsignaturaOutputDto> getAllAsignaturasByStudent(int idStudent) throws EntityNotFoundException;

}
