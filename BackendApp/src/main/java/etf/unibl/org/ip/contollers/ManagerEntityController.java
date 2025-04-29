package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.LoginDTO;
import etf.unibl.org.ip.dto.ManagerDTO;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/managers")
public class ManagerEntityController {

    private final ManagerService managerService;

    public ManagerEntityController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public List<ManagerDTO> getManagers() {
        return managerService.getAllManagers();
    }

    @GetMapping("/byId")
    public ManagerDTO getManagerById(@RequestParam int id) {
        return managerService.getManagerById(id);
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createManager(@RequestBody PersonEntity personEntity) {
        return managerService.createManager(personEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginDTO loginDTO) {
        return managerService.login(loginDTO);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateManager(@RequestBody ManagerDTO managerDTO) {
        return managerService.updateManager(managerDTO);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteManager(@RequestParam int id) {
        return managerService.deleteManager(id);
    }

}
