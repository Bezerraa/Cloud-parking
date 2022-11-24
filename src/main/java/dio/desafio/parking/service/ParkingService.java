package dio.desafio.parking.service;

import dio.desafio.parking.exception.ParkingNotFoundException;
import dio.desafio.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {


    private static Map<String, Parking> parkingMap = new HashMap();
    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "MJR-4222", "PR", "CELTA", "BRANCO");
        Parking parking1 = new Parking(id1, "BHR-4265", "PE", "KWID", "LARANJA");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);


    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");

    }
    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if (parking == null){throw new ParkingNotFoundException(id);

        }
        return parking;
    }

    public Parking create(Parking parkingcreate) {
        String uuid = getUUID();
        parkingcreate.setId(uuid);
        parkingcreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingcreate);
        return parkingcreate;
    }

    public void delete(String id) {
        Parking parking = findById(id);
        parkingMap.remove(id);
    }

    public Parking update(String id, Parking parkingEdited) {
        Parking outDtParking = findById(id);
        outDtParking.setColor(parkingEdited.getColor());
//
        parkingMap.replace(id, outDtParking); //previously outdated but just updated now

        return outDtParking;
    }

    public Parking exit(String id) {
       Parking exitParking = findById(id);
       exitParking.setExitDate(LocalDateTime.now());
       Duration duration = Duration.between(exitParking.getEntryDate(), exitParking.getExitDate());
       double bill = duration.toSeconds();
       exitParking.setBill(bill*1.5);
        return exitParking;
    }
}
