package dio.desafio.parking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class helloController {
    @GetMapping
    public String Hello(){return "Ai jojós";}
}