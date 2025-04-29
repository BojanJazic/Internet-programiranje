package etf.unibl.org.ip.contollers;

import etf.unibl.org.ip.dto.LoginDTO;
import etf.unibl.org.ip.dto.PersonDTO;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("persons")
public class PersonEntityController {

    private final PersonService personService;

    public PersonEntityController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    List<PersonEntity> findAll() {
        return personService.findAll();
    }

    @GetMapping("/byId/")
    ResponseEntity<PersonEntity> findById(@RequestParam int id) {
        PersonEntity personEntity = personService.findById(id);
        if(personEntity != null) {
            return new ResponseEntity<>(personEntity, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/employees")
    public List<PersonDTO> findAllEmployees() {
        return personService.getAllEmployees();
    }

    @PostMapping
    ResponseEntity<PersonEntity> create(@RequestBody PersonEntity personEntity) {
        PersonEntity savedPersonEntity = personService.save(personEntity);
        return new ResponseEntity<>(savedPersonEntity, HttpStatus.CREATED);
    }

    @PostMapping("/registration")
    ResponseEntity<HttpStatus> registration(@RequestBody PersonEntity personEntity) {
        return personService.registerNewPerson(personEntity);
    }

    @PostMapping("/login")
    //NAPRAVITI MOZDA LOGINDTO KOJI CE IMATI SAMO USERNAME I PASSWORD I TO SLATI
    ResponseEntity<HttpStatus> login(@RequestBody LoginDTO loginDTO) {
       return personService.login(loginDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        return personService.deleteById(id);
    }

    /**
     *
     * OVDJE NASTAVI
     *
     */

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable Integer id, @RequestBody PersonEntity updatedPerson) {
        PersonEntity updated = personService.updatePerson(id, updatedPerson);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
