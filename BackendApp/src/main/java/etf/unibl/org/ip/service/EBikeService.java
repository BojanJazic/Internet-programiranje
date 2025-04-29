package etf.unibl.org.ip.service;

import etf.unibl.org.ip.dto.EBikeDTO;
import etf.unibl.org.ip.entities.EBikeEntity;
import etf.unibl.org.ip.entities.ManufacturerEntity;
import etf.unibl.org.ip.entities.VehicleEntity;
import etf.unibl.org.ip.repositories.EBikeEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EBikeService {

    private final EBikeEntityRepository repository;

    private final VehicleService vehicleService;

    private final ManufacturerService manufacturerService;

    public EBikeService(EBikeEntityRepository repository, VehicleService vehicleService, ManufacturerService manufacturerService) {
        this.repository = repository;
        this.vehicleService = vehicleService;
        this.manufacturerService = manufacturerService;
    }

    public List<EBikeDTO> getAllEBikes() {
        return repository.getAllEBikeDTO();
    }

    public ResponseEntity<HttpStatus> addNewEBike(EBikeDTO dto) {
        if(!manufacturerService.manufacturerExists(dto.getIdManufacturer()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ManufacturerEntity manufacturerEntity = manufacturerService.getManufacturer(dto.getIdManufacturer());

        if(vehicleService.vehicleEntityRepository.existsByIdVehicle(dto.getIdVehicle()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setIdVehicle(dto.getIdVehicle());
        vehicleEntity.setIdManufacturer(manufacturerEntity);
        vehicleEntity.setModel(dto.getModel());
        vehicleEntity.setPurchasePrice(dto.getPurchasePrice());
        vehicleEntity.setIsRented(dto.getIsRented());
        vehicleEntity.setIsBroken(dto.getIsBroken());
        vehicleEntity.setImage(String.valueOf(dto.getImage()));

        vehicleEntity = vehicleService.addVehicle(vehicleEntity);

        if (vehicleEntity == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        EBikeEntity bikeEntity = new EBikeEntity();
        bikeEntity.setIdVehicle(vehicleEntity.getIdVehicle());
        bikeEntity.setVehicle(vehicleEntity);
        bikeEntity.setAutonomy(dto.getAutonomy());

        bikeEntity = saveBike(bikeEntity);

        if (bikeEntity == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    public EBikeEntity saveBike(EBikeEntity bikeEntity) {
        return repository.saveAndFlush(bikeEntity);
    }

    public EBikeDTO getEBikeById(String id) {
        return repository.getEBikeDTOByIdVehicle(id);
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteEBike(String id) {
        repository.deleteEBikeEntityByIdVehicle(id);
        vehicleService.deleteVehicle(id);

        if(!repository.existsEBikeEntityByIdVehicle(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /*
    * vozilo ce uvijek postojati iz razloga sto ce biti odabrano sa forme
    * pa ne moram da provjeravam da li postoji
    * */
    @Transactional
    public ResponseEntity<HttpStatus> updateEBike(EBikeDTO dto) {
        Optional<VehicleEntity> vehicleEntityOptional = vehicleService.vehicleEntityRepository.findByIdVehicle(dto.getIdVehicle());
        Optional<EBikeEntity> eBikeEntityOptional = repository.findEBikeEntityByIdVehicle(dto.getIdVehicle());

        if(vehicleEntityOptional.isPresent() && eBikeEntityOptional.isPresent()) {
            VehicleEntity vehicleEntity = vehicleEntityOptional.get();

            vehicleEntity.setIdVehicle(dto.getIdVehicle());
            vehicleEntity.setIdManufacturer(manufacturerService.getManufacturer(dto.getIdManufacturer()));
            vehicleEntity.setModel(dto.getModel());
            vehicleEntity.setPurchasePrice(dto.getPurchasePrice());
            vehicleEntity.setIsRented(dto.getIsRented());
            vehicleEntity.setIsBroken(dto.getIsBroken());
            vehicleEntity.setImage(String.valueOf(dto.getImage()));

            vehicleEntity = vehicleService.updateVehicle(vehicleEntity);

            EBikeEntity eBikeEntity = eBikeEntityOptional.get();
            eBikeEntity.setIdVehicle(vehicleEntity.getIdVehicle());
            eBikeEntity.setVehicle(vehicleEntity);
            eBikeEntity.setAutonomy(dto.getAutonomy());

            repository.saveAndFlush(eBikeEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
