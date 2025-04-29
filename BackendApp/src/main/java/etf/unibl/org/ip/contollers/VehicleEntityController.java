package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.VehicleResponseDTO;
import etf.unibl.org.ip.dto.VehicleResponseWithPriceDTO;
import etf.unibl.org.ip.entities.VehicleEntity;
import etf.unibl.org.ip.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleEntityController {

    private final VehicleService vehicleService;

    public VehicleEntityController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<VehicleResponseDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/withPrice")
    public List<VehicleResponseWithPriceDTO> getVehiclesWithPrice() {
        return vehicleService.getAllVehiclesWithPrice();
    }

    @PutMapping("/{idVehicle}/{price}")
    public ResponseEntity<HttpStatus> updateVehicle(@PathVariable String idVehicle, @PathVariable int price) {
        return vehicleService.updateVehicle(idVehicle, price);
    }

    @PutMapping("/{idVehicle}")
    public ResponseEntity<HttpStatus> updateVehicleMalfunction(@PathVariable String idVehicle) {
        return vehicleService.updateVehicleMalfunction(idVehicle);
    }

    @PutMapping("/repair/{idVehicle}")
    public ResponseEntity<HttpStatus> repairVehicle(@PathVariable String idVehicle) {
        return vehicleService.repairVehicle(idVehicle);
    }

}
