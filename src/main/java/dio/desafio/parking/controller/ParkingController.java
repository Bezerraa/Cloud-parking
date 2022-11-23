package dio.desafio.parking.controller;

import dio.desafio.parking.controller.dto.ParkingCreateDTO;
import dio.desafio.parking.controller.dto.ParkingDTO;
import dio.desafio.parking.controller.mapper.ParkingMapper;
import dio.desafio.parking.model.Parking;
import dio.desafio.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/parking")
@Api(tags = "ParkingController")
public class ParkingController {
    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation(  "Find All parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
        }
    @GetMapping("/{id}")
    @ApiOperation("Find by parking Id")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
    Parking parking = parkingService.findById(id);
    ParkingDTO result = parkingMapper.toParkingDTO(parking);
    return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation("Add a new parking register")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
    var parkingCreate = parkingMapper.toParkingCreate(dto);
    Parking parking = parkingService.create(parkingCreate);
    ParkingDTO result = parkingMapper.toParkingDTO(parking);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}


