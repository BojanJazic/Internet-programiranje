package etf.unibl.org.ip.service;

import etf.unibl.org.ip.dto.CarDTO;
import etf.unibl.org.ip.entities.CarEntity;
import etf.unibl.org.ip.entities.ManufacturerEntity;
import etf.unibl.org.ip.entities.VehicleEntity;
import etf.unibl.org.ip.repositories.CarEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    final
    CarEntityRepository carEntityRepository;

    private final VehicleService vehicleService;

    private final ManufacturerService manufacturerService;

    public CarService(CarEntityRepository carEntityRepository, VehicleService vehicleService, ManufacturerService manufacturerService) {
        this.carEntityRepository = carEntityRepository;
        this.vehicleService = vehicleService;
        this.manufacturerService = manufacturerService;
    }

    public List<CarDTO> getAllCars() {
        return carEntityRepository.getAllCars();
    }

    /*
    * potrebno provjeriti da li postoji id vec u tabeli posto se id rucno unosi
    *
    * */

    //@Transactional
    public ResponseEntity<HttpStatus> addNewCar(CarDTO carDTO) {

        if (carDTO.getIdManufacturer() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Ili neki drugi odgovarajući status
        }

        if (!manufacturerService.manufacturerExists(carDTO.getIdManufacturer())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(vehicleService.vehicleEntityRepository.existsByIdVehicle(carDTO.getIdVehicle())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        ManufacturerEntity manufacturerEntity = manufacturerService.getManufacturer(carDTO.getIdManufacturer());

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setIdVehicle(carDTO.getIdVehicle());
        vehicleEntity.setIdManufacturer(manufacturerEntity);
        vehicleEntity.setModel(carDTO.getModel());
        vehicleEntity.setPurchasePrice(carDTO.getPurchasePrice());
        vehicleEntity.setIsRented(carDTO.getIsRented());
        vehicleEntity.setIsBroken(carDTO.getIsBroken());
        vehicleEntity.setImage(String.valueOf(carDTO.getImage()));

        vehicleEntity = vehicleService.addVehicle(vehicleEntity);

        if (vehicleEntity == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        CarEntity carEntity = new CarEntity();
        carEntity.setIdVehicle(vehicleEntity.getIdVehicle());
        carEntity.setVehicle(vehicleEntity);
        carEntity.setPurchaseDate(carDTO.getPurchaseDate());
        carEntity.setDescription(carDTO.getDescription());

        carEntity = saveCar(carEntity);
        if (carEntity == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    public CarEntity saveCar(CarEntity carEntity) {
        return carEntityRepository.saveAndFlush(carEntity);
    }


    public CarDTO getCarById(String id) {
        return carEntityRepository.getCarById(id);
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteCar(String idVehicle) {
        carEntityRepository.deleteCarByIdVehicle(idVehicle);
        vehicleService.deleteVehicle(idVehicle);

        if(!carEntityRepository.existsCarByIdVehicle(idVehicle)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //azuriranje
    /*
    * SKONTAJ NA KOJI NACIN NAJBOLJE DA PROVJERIS DA LI JE AZURIRANJE USPJESNO - auto ce uvijek postojati,
    * jer ce se iz tabele izabrati postojece auto
    * */
    @Transactional
    public ResponseEntity<HttpStatus> updateCar(CarDTO carDTO) {

        Optional<VehicleEntity> vehicleEntityOptional = vehicleService.vehicleEntityRepository.findByIdVehicle(carDTO.getIdVehicle());

        Optional<CarEntity> carEntityOptional = carEntityRepository.findCarByIdVehicle(carDTO.getIdVehicle());

        if(vehicleEntityOptional.isPresent() && carEntityOptional.isPresent()) {
            VehicleEntity vehicleEntity = vehicleEntityOptional.get();

            vehicleEntity.setIdVehicle(carDTO.getIdVehicle());
            vehicleEntity.setIdManufacturer(manufacturerService.getManufacturer(carDTO.getIdManufacturer()));
            vehicleEntity.setModel(carDTO.getModel());
            vehicleEntity.setPurchasePrice(carDTO.getPurchasePrice());
            vehicleEntity.setIsBroken(carDTO.getIsBroken());
            vehicleEntity.setIsRented(carDTO.getIsRented());
            vehicleEntity.setImage(carDTO.getImage());

            vehicleEntity = vehicleService.updateVehicle(vehicleEntity);


            CarEntity carEntity = carEntityOptional.get();

            carEntity.setVehicle(vehicleEntity);
            carEntity.setPurchaseDate(carDTO.getPurchaseDate());
            carEntity.setDescription(carDTO.getDescription());


            carEntityRepository.save(carEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        }

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

}
