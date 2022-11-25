package dio.desafio.parking.service;

import dio.desafio.parking.exception.ParkingNotFoundException;
import dio.desafio.parking.model.Parking;
import dio.desafio.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {


    private final ParkingRepository parkingRepository;
    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }




    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");

    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }

    @Transactional
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));

    }

    @Transactional
    public Parking create(Parking parkingcreate) {
        String uuid = getUUID();
        parkingcreate.setId(uuid);
        parkingcreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingcreate);
        return parkingcreate;
    }

    @Transactional
    public void delete(String id) {
        Parking parking = findById(id);
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingEdited) {
        Parking outDtParking = findById(id);
        outDtParking.setColor(parkingEdited.getColor());
        outDtParking.setState(parkingEdited.getState());
        outDtParking.setLicense(parkingEdited.getLicense());
        outDtParking.setModel(parkingEdited.getModel());
        parkingRepository.save(outDtParking);
        return outDtParking;
    }

    @Transactional
    public Parking checkOut(String id) {
       Parking checkOutParking = findById(id);
       checkOutParking.setExitDate(LocalDateTime.now());
       checkOutParking.setBill(ParkingCheckOut.getBill(checkOutParking));
       parkingRepository.save(checkOutParking);
        return checkOutParking;
    }
}
