package dio.desafio.parking.controller;

import dio.desafio.parking.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
//Porta aleatória para que se houverem outros testes feitos por outros desenvolvedores,
//que não choquem com as portas.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT extends AbstractContainerBase{
    @LocalServerPort
    private int randomport;

    
    @BeforeEach //anotação do Junit -> Para cada teste realizado, esse método será executado
    public void setUpTest(){
        RestAssured.port = randomport;
    }

    @Test
    void whenfindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    void whenCreateThenCheckIsCreated() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AZUL");
        createDTO.setModel("FORD FIESTA");
        createDTO.setLicense("QWW-3123");
        createDTO.setState("MA");


        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}