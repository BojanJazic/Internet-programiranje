package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.EBikeDTO;
import etf.unibl.org.ip.service.EBikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/e-bikes")
public class EBikeEntityController {

    private final EBikeService ebikeService;


    public EBikeEntityController(EBikeService ebikeService) {
        this.ebikeService = ebikeService;
    }

    @GetMapping
    public List<EBikeDTO> getEBikes() {
        return ebikeService.getAllEBikes();
    }

    @GetMapping("/{idVehicle}")
    public EBikeDTO getEBikeById(@PathVariable String idVehicle) {
        return ebikeService.getEBikeById(idVehicle);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addEBike(@RequestBody EBikeDTO ebikeDTO) {
        return ebikeService.addNewEBike(ebikeDTO);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteEBike(@RequestParam String idVehicle) {
        return ebikeService.deleteEBike(idVehicle);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateEBike(@RequestBody EBikeDTO ebikeDTO) {
        return ebikeService.updateEBike(ebikeDTO);
    }

}
