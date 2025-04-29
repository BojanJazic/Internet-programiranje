package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.EScooterDTO;
import etf.unibl.org.ip.service.EBikeService;
import etf.unibl.org.ip.service.EScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/e-scooters")
public class EScooterEntityController {


    private final EScooterService escooterService;


    public EScooterEntityController(EScooterService escooterService) {
        this.escooterService = escooterService;
    }

    @GetMapping
    public List<EScooterDTO> getAllScooters() {
        return escooterService.getAllScooters();
    }

    @GetMapping("/{idVehicle}")
    public EScooterDTO getScooterById(@PathVariable String idVehicle) {
        return escooterService.getScooterById(idVehicle);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewScooter(@RequestBody EScooterDTO escooterDTO) {
        return escooterService.addNewEScooter(escooterDTO);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateScooter(@RequestBody EScooterDTO escooterDTO) {
        return escooterService.updateEScooter(escooterDTO);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteScooterById(@RequestParam String idVehicle) {
        return escooterService.deleteEScooter(idVehicle);
    }
}
