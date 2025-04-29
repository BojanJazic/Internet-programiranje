package etf.unibl.org.ip.contollers;

import etf.unibl.org.ip.dto.MalfunctionDTO;
import etf.unibl.org.ip.dto.MalfunctionResponseDTO;
import etf.unibl.org.ip.entities.MalfunctionEntity;
import etf.unibl.org.ip.service.MalfunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/malfunctions")
public class MalfunctionEntityController {

    private final MalfunctionService malfunctionService;

    public MalfunctionEntityController(MalfunctionService malfunctionService) {
        this.malfunctionService = malfunctionService;
    }

    @GetMapping
    public List<MalfunctionResponseDTO> getAllMalfunctions() {
        return malfunctionService.getAllMalfunctions();
    }

    @GetMapping("/{idVehicle}")
    public List<MalfunctionResponseDTO> getMalfunctionById(@PathVariable String idVehicle) {
        return malfunctionService.getMalfunctionsByVehicleId(idVehicle);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addNewMalfunction(@RequestBody MalfunctionDTO malfunction) {
        return malfunctionService.addNewMalfunction(malfunction);
    }

    @DeleteMapping("/{idMalfunction}")
    public ResponseEntity<HttpStatus> deleteMalfunction(@PathVariable Integer idMalfunction) {
        return malfunctionService.deleteMalfunction(idMalfunction);
    }

    @GetMapping("/perVehicle")
    public Map<String, Integer> malfunctionsPerVehicle() {
        return malfunctionService.getNumberOfMalfunctionsPerVehicle();
    }

}
