package etf.unibl.org.ip.service;

import etf.unibl.org.ip.dto.EScooterDTO;
import etf.unibl.org.ip.entities.EScooterEntity;
import etf.unibl.org.ip.entities.ManufacturerEntity;
import etf.unibl.org.ip.entities.VehicleEntity;
import etf.unibl.org.ip.repositories.EScooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EScooterService {

    private final EScooterRepository escooterRepository;

    private final VehicleService vehicleService;

    private final ManufacturerService manufacturerService;

    public EScooterService(EScooterRepository escooterRepository, VehicleService vehicleService, ManufacturerService manufacturerService) {
        this.escooterRepository = escooterRepository;
        this.vehicleService = vehicleService;
        this.manufacturerService = manufacturerService;
    }

    public List<EScooterDTO> getAllScooters() {
        return escooterRepository.getAllEScooters();
    }

    public EScooterDTO getScooterById(String id) {
        return escooterRepository.getEScooterDTOByIdVehicle(id);
    }

    @Transactional
    public EScooterEntity saveEScooter(EScooterEntity eScooterEntity) {
        return escooterRepository.saveAndFlush(eScooterEntity);
    }

    public ResponseEntity<HttpStatus> addNewEScooter(EScooterDTO escooterDTO) {

        System.out.println(escooterDTO);

        if(!manufacturerService.manufacturerExists(escooterDTO.getIdManufacturer()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(vehicleService.vehicleEntityRepository.existsByIdVehicle(escooterDTO.getIdVehicle()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        ManufacturerEntity manufacturerEntity = manufacturerService.getManufacturer(escooterDTO.getIdManufacturer());

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setIdVehicle(escooterDTO.getIdVehicle());
        vehicleEntity.setIdManufacturer(manufacturerEntity);
        vehicleEntity.setModel(escooterDTO.getModel());
        vehicleEntity.setPurchasePrice(escooterDTO.getPurchasePrice());
        vehicleEntity.setIsRented(escooterDTO.getIsRented());
        vehicleEntity.setIsBroken(escooterDTO.getIsBroken());
        vehicleEntity.setImage(String.valueOf(escooterDTO.getImage()));

        vehicleEntity = vehicleService.addVehicle(vehicleEntity);

        if (vehicleEntity == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        EScooterEntity escooterEntity = new EScooterEntity();
        escooterEntity.setIdVehicle(vehicleEntity.getIdVehicle());
        escooterEntity.setVehicle(vehicleEntity);
        escooterEntity.setMaxSpeed(escooterDTO.getMaxSpeed());

        escooterEntity = saveEScooter(escooterEntity);

        if(escooterEntity == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteEScooter(String id) {
        escooterRepository.deleteEScooterEntityByIdVehicle(id);
        vehicleService.deleteVehicle(id);

        if(!escooterRepository.existsEScooterEntityByIdVehicle(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<HttpStatus> updateEScooter(EScooterDTO escooterDTO) {
        Optional<VehicleEntity> vehicleEntityOptional = vehicleService.vehicleEntityRepository.findByIdVehicle(escooterDTO.getIdVehicle());
        Optional<EScooterEntity> eScooterEntityOptional = escooterRepository.findEScooterEntityByIdVehicle(escooterDTO.getIdVehicle());


        if(vehicleEntityOptional.isPresent() && eScooterEntityOptional.isPresent()) {
            VehicleEntity vehicleEntity = vehicleEntityOptional.get();
            vehicleEntity.setIdVehicle(escooterDTO.getIdVehicle());
            vehicleEntity.setIdManufacturer(manufacturerService.getManufacturer(escooterDTO.getIdManufacturer()));
            vehicleEntity.setModel(escooterDTO.getModel());
            vehicleEntity.setPurchasePrice(escooterDTO.getPurchasePrice());
            vehicleEntity.setIsRented(escooterDTO.getIsRented());
            vehicleEntity.setIsBroken(escooterDTO.getIsBroken());
            vehicleEntity.setImage(String.valueOf(escooterDTO.getImage()));

            vehicleEntity = vehicleService.updateVehicle(vehicleEntity);

            EScooterEntity escooterEntity = eScooterEntityOptional.get();
            escooterEntity.setIdVehicle(vehicleEntity.getIdVehicle());
            escooterEntity.setVehicle(vehicleEntity);
            escooterEntity.setMaxSpeed(escooterDTO.getMaxSpeed());

            escooterRepository.saveAndFlush(escooterEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
