package dio.desafio.parking.controller;

import dio.desafio.parking.model.Parking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @GetMapping
    public List<Parking> findAll() {
        var carro = new Parking();
        carro.setColor("PRETO");
        carro.setLicense("KJR-8929");
        carro.setModel("vw GOL");
        carro.setState("PE");


        return Arrays.asList(carro);
    }



}
