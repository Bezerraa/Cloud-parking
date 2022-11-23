package dio.desafio.parking.service;

import dio.desafio.parking.model.Parking;
import org.springframework.stereotype.Service;

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
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingcreate) {
        String uuid = getUUID();
        parkingcreate.setId(uuid);
        parkingcreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingcreate);
        return parkingcreate;
    }
}
