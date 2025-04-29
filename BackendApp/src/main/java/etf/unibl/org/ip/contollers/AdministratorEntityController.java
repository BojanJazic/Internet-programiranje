package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.AdministratorDTO;
import etf.unibl.org.ip.dto.LoginDTO;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.service.AdministratorService;
import etf.unibl.org.ip.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/administrators")
public class AdministratorEntityController {

    final
    AdministratorService administratorService;
    private final PersonService personService;


    public AdministratorEntityController(AdministratorService administratorService, PersonService personService) {
        this.administratorService = administratorService;
        this.personService = personService;
    }

    @GetMapping
    public List<AdministratorDTO> getAllAdministrators() {
        return administratorService.getAllAdministrators();
    }

    /*@GetMapping("/byId/{id}")
    public AdministratorEntity getAdministratorById(@PathVariable("id") int id) {
        return administratorService.getAdministratorById(id);
    }*/

    /*
    moze i ovako, ali onda ide poziv byId/?id=broj

    * * */
    @GetMapping("/byId/{id}")
    public AdministratorDTO getAdministratorById(@PathVariable int id) {
        return administratorService.getAdministratorById(id);
    }

    @GetMapping("/byUsername/{username}")
    public AdministratorDTO getAdministratorByUsername(@PathVariable String username) {
        return personService.getAdministratorByUsername(username);
    }


    /*
    * treba ovdje vrsiti provjeru prilikom registracije da li postoji osoba
    * ovako nije dobro, jer ce uvijek vratiti status 201
    *
    * */
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createAdministrator(@RequestBody PersonEntity person) {
        return administratorService.saveAdministrator(person);

    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginDTO loginDTO) {
        return personService.login(loginDTO);
    }

    //potrebno izmijeniti da bude isto kao za operator i manager
    @PutMapping
    public ResponseEntity<HttpStatus> updateAdministrator(@RequestBody AdministratorDTO administratorDTO) {
        return administratorService.updateAdministrator(administratorDTO);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAdministrator(@RequestParam("id") int id) {
        return administratorService.deleteAdministrator(id);
    }

    //dohvatanje svake vrste vozila

}
