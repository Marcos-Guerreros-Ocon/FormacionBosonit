package formacion.block7crudvalidation.security.controller;


import formacion.block7crudvalidation.person.domain.Person;
import formacion.block7crudvalidation.person.repository.PersonRepository;
import formacion.block7crudvalidation.security.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    PersonRepository personaRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMap) {
        String usuario = requestMap.get("usuario");
        String password = requestMap.get("password");

        Person persona = personaRepository.findByUsuario(usuario);


        if (!persona.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Usuario o contraseña incorrectos");
        }

        String role = Boolean.TRUE.equals(persona.getAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
        return new ResponseEntity<>(jwtTokenUtil.generateToken(usuario, role), HttpStatus.OK);
    }


}
