package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.CarDTO;
import etf.unibl.org.ip.entities.CarEntity;
import etf.unibl.org.ip.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cars")
public class CarEntityController {

    final
    CarService carService;

    public CarEntityController(CarService carService) {
        this.carService = carService;
    }

    /*
    * prepraviti klasu CarDTO da vraca naziv proizvodjaca, a ne njegov id
    * */

    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{idVehicle}")
    public CarDTO getCarById(@PathVariable String idVehicle) {
        return carService.getCarById(idVehicle);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addCar(@RequestBody CarDTO carDTO) {
       return carService.addNewCar(carDTO);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteCar(@RequestParam String idVehicle) {
        return carService.deleteCar(idVehicle);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateCar(@RequestBody CarDTO carDTO) {
        return carService.updateCar(carDTO);
    }




}
