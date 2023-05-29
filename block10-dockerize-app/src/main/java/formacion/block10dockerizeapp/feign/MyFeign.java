package formacion.block10dockerizeapp.feign;


import formacion.block10dockerizeapp.teacher.controller.dto.SimpleTeacherOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", name = "feignProfesor")
public interface MyFeign {
    @GetMapping(value = "/teacher/{id}")
    SimpleTeacherOutputDto getTeacherById(@PathVariable int id);
}
