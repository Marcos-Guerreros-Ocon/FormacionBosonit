package formacion.block6pathvariableheaders;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class Controller {

    @PostMapping(value = "/controller/postExample")
    public Object postReturn(@RequestBody Object object){
        return object;
    }

    @GetMapping("/user/{id}")
    public int getId(@PathVariable int id){
        return id;
    }

        @PutMapping("/post")
    public Map<String,String> putExample(@RequestParam()Map<String,String> test){
        return test;

    }
}
