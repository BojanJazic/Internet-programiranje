package etf.unibl.org.ip.service;


import etf.unibl.org.ip.dto.LoginDTO;
import etf.unibl.org.ip.dto.ManagerDTO;
import etf.unibl.org.ip.entities.ManagerEntity;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.repositories.ManagerEntityRepository;
import etf.unibl.org.ip.repositories.PersonEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerEntityRepository managerEntityRepository;
    private final PersonService personService;
    private final PersonEntityRepository peopleEntityRepository;
    private final PersonEntityRepository personEntityRepository;

    public ManagerService(ManagerEntityRepository managerEntityRepository, PersonService personService, PersonEntityRepository peopleEntityRepository, PersonEntityRepository personEntityRepository) {
        this.managerEntityRepository = managerEntityRepository;
        this.personService = personService;
        this.peopleEntityRepository = peopleEntityRepository;
        this.personEntityRepository = personEntityRepository;
    }

    public List<ManagerDTO> getAllManagers() {
        return managerEntityRepository.findAllManagers();
    }

    public ManagerDTO getManagerById(int id) {
        Optional<ManagerDTO> managerDTO = managerEntityRepository.findManagerEntityById(id);
         if (managerDTO.isPresent()) {
             return managerDTO.get();
         }

         return null;
    }

    @Transactional
    public ResponseEntity<HttpStatus> createManager(PersonEntity personEntity) {
        ResponseEntity<HttpStatus> response = personService.registerNewPerson(personEntity);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            ManagerEntity managerEntity = new ManagerEntity();
            managerEntity.setPerson(personEntity);

            managerEntityRepository.save(managerEntity);
            if(personEntityRepository.existsById(personEntity.getId())) {
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseEntity<HttpStatus> updateManager(ManagerDTO managerDTO) {
        Optional<ManagerEntity> managerEntityOptional = managerEntityRepository.findById(managerDTO.getId());

        if (managerEntityOptional.isPresent()) {
            PersonEntity personEntity = managerEntityOptional.get().getPerson();
            personEntity.setName(managerDTO.getName());
            personEntity.setSurname(managerDTO.getSurname());
            personEntity.setUsername(managerDTO.getUsername());

            personEntityRepository.save(personEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteManager(int id) {
        personService.deleteById(id);
        if(!managerEntityRepository.findManagerEntityById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HttpStatus> login(LoginDTO loginDTO) {
        return personService.login(loginDTO);
    }

}
