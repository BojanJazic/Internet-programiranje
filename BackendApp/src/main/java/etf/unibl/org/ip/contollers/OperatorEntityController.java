package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.LoginDTO;
import etf.unibl.org.ip.dto.OperatorDTO;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.service.OperatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/operators")
public class OperatorEntityController {


    private final OperatorService operatorService;

    public OperatorEntityController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping
    public List<OperatorDTO> getAllOperators() {
        return operatorService.getAllOperators();
    }

    @GetMapping("/byId")
    public OperatorDTO getOperatorById(@RequestParam int id) {
        return operatorService.getOperatorById(id);
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createOperator(@RequestBody PersonEntity personEntity) {
        return operatorService.saveOperator(personEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody LoginDTO loginDTO) {
        return operatorService.login(loginDTO);
    }


    /*
    * mislim da nije dobro ovako iz razloga sto ce se trebati i sifra mijenjati,
    * ali moze sad trenutno da ostane za potrebe tesitranja
    *
    * */
    @PutMapping
    public ResponseEntity<HttpStatus> updateOperator(@RequestBody OperatorDTO operatorDTO) {
        return operatorService.updateOperator(operatorDTO);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteOperator(@RequestParam int id) {
        return operatorService.deleteOperator(id);
    }

}
