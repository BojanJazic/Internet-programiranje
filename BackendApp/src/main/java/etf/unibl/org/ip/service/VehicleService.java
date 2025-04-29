package etf.unibl.org.ip.service;


import etf.unibl.org.ip.dto.VehicleResponseDTO;
import etf.unibl.org.ip.dto.VehicleResponseWithPriceDTO;
import etf.unibl.org.ip.entities.VehicleEntity;
import etf.unibl.org.ip.repositories.VehicleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {

    final
    VehicleEntityRepository vehicleEntityRepository;


    public VehicleService(VehicleEntityRepository vehicleEntityRepository) {
        this.vehicleEntityRepository = vehicleEntityRepository;
    }


    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleEntityRepository.getAllVehicles();
    }


    //provjeriti da li postoji registracija, odnosno id koji se rucno unosi i mora biti jedinstven
   /* public ResponseEntity<HttpStatus> addVehicle(VehicleEntity vehicleEntity) {
        if(vehicleEntityRepository.existsById(vehicleEntity.getIdVehicle())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else{
            vehicleEntityRepository.save(vehicleEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }*/
    public VehicleEntity addVehicle(VehicleEntity vehicleEntity) {
        return vehicleEntityRepository.save(vehicleEntity);
    }

    public VehicleEntity updateVehicle(VehicleEntity vehicleEntity) {
        return vehicleEntityRepository.save(vehicleEntity);
    }

    public void deleteVehicle(String idVehicle) {
        vehicleEntityRepository.deleteByIdVehicle(idVehicle);
    }

    public List<VehicleResponseWithPriceDTO> getAllVehiclesWithPrice() {
        return vehicleEntityRepository.getAllVehiclesWithPrice();
    }

    public ResponseEntity<HttpStatus> updateVehicle(String idVehicle, int price){
        Integer result = vehicleEntityRepository.updateVehicle(idVehicle, price);
        if(result == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<HttpStatus> updateVehicleMalfunction(String idVehicle){
        Integer result = vehicleEntityRepository.updateVehicleMalfunction(idVehicle);
        if(result == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<HttpStatus> repairVehicle(String idVehicle){
        Integer result = vehicleEntityRepository.repairVehicle(idVehicle);
        if(result == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
