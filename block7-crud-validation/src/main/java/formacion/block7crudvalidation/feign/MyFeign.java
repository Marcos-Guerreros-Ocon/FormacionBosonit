package formacion.block7crudvalidation.feign;


import formacion.block7crudvalidation.teacher.controller.dto.SimpleTeacherOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", name = "feignProfesor")
public interface MyFeign {
    @GetMapping("/teacher/{id}")
    ResponseEntity<SimpleTeacherOutputDto> getTeacher(@PathVariable int id);
}
