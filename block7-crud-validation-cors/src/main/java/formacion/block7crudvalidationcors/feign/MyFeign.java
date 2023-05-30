package formacion.block7crudvalidationcors.feign;


import formacion.block7crudvalidationcors.teacher.controller.dto.SimpleTeacherOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", name = "feignProfesor")
public interface MyFeign {
    @GetMapping(value = "/teacher/{id}")
    SimpleTeacherOutputDto getTeacherById(@PathVariable int id);
}
