package com.jwt.stateless.javaloginjwt.controller;

import com.jwt.stateless.javaloginjwt.dto.GreetingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.security.Principal;

@RestController
@RequestMapping("/rest")
public class GreetingsController {

    @RequestMapping(path = "/unsecure/greetings", method = GET, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<GreetingDTO> unsecuredGreetings() {
        return ResponseEntity.ok(new GreetingDTO("Greetings and salutations!"));
    }

    @RequestMapping(path = "/secure/greetings", method = GET, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<GreetingDTO> securedGreetings(Principal principal) {
        return ResponseEntity.ok(new GreetingDTO("Greetings and salutations, " + principal.getName() + "!"));
    }
}
