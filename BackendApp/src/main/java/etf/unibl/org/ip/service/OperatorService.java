package etf.unibl.org.ip.service;

import etf.unibl.org.ip.dto.LoginDTO;
import etf.unibl.org.ip.dto.OperatorDTO;
import etf.unibl.org.ip.entities.OperatorEntity;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.repositories.OperatorEntityRepository;
import etf.unibl.org.ip.repositories.PersonEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OperatorService {

    private final OperatorEntityRepository operatorEntityRepository;

    private final PersonService personService;
    private final PersonEntityRepository personEntityRepository;

    public OperatorService(OperatorEntityRepository operatorEntityRepository, PersonService personService, PersonEntityRepository personEntityRepository) {
        this.operatorEntityRepository = operatorEntityRepository;
        this.personService = personService;
        this.personEntityRepository = personEntityRepository;
    }

    public List<OperatorDTO> getAllOperators() {
        return operatorEntityRepository.findAllOperators();
    }


    /*
    * ovdje skontati sta da se vrati ukoliko ne postoji osoba
    *
    * */
    public OperatorDTO getOperatorById(int id) {
        Optional<OperatorDTO> operatorDTO = operatorEntityRepository.findOperatorEntityById(id);
        if (operatorDTO.isPresent()) {
            return operatorDTO.get();
        }

        return null;
    }

    @Transactional
    public ResponseEntity<HttpStatus> saveOperator(PersonEntity personEntity) {
        ResponseEntity<HttpStatus> httpStatus = personService.registerNewPerson(personEntity);
        if(httpStatus.getStatusCode() == HttpStatus.CREATED) {
            OperatorEntity operatorEntity = new OperatorEntity();
            operatorEntity.setPerson(personEntity);

            operatorEntityRepository.save(operatorEntity);
            if(personEntityRepository.existsById(personEntity.getId())) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @Transactional
    public ResponseEntity<HttpStatus> updateOperator(OperatorDTO operatorDTO) {
        Optional<OperatorEntity> operatorEntityOptional = operatorEntityRepository.findById(operatorDTO.getId());
        if(operatorEntityOptional.isPresent()) {
            Optional<PersonEntity> personEntityOptional = personEntityRepository.findById(operatorDTO.getId());

            PersonEntity personEntity = personEntityOptional.get();
            personEntity.setName(operatorDTO.getName());
            personEntity.setSurname(operatorDTO.getSurname());
            personEntity.setUsername(operatorDTO.getUsername());

            personEntityRepository.save(personEntity);


            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteOperator(int id) {
        personService.deleteById(id);
        if(!operatorEntityRepository.findOperatorEntityById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HttpStatus> login(LoginDTO loginDTO) {
        return personService.login(loginDTO);
    }

}
