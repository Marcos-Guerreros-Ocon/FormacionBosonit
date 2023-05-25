package formacion.block7crudvalidation.asignatura.application;

import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaInputDto;
import formacion.block7crudvalidation.asignatura.controller.dto.AsignaturaOutputDto;
import formacion.block7crudvalidation.asignatura.controller.dto.SimpleAsignaturaOutputDto;
import formacion.block7crudvalidation.exception.EntityNotFoundException;
import formacion.block7crudvalidation.exception.UnprocessableEntityException;

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
