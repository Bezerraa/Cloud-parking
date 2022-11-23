package dio.desafio.parking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@RestController
@RequestMapping("/")
@ApiIgnore
public class helloController {
    @GetMapping
    public String Hello(){return "Em andamento";}
}
