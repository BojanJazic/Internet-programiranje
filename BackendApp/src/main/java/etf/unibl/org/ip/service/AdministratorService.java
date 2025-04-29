package etf.unibl.org.ip.service;

import etf.unibl.org.ip.dto.AdministratorDTO;
import etf.unibl.org.ip.dto.LoginDTO;
import etf.unibl.org.ip.entities.AdministratorEntity;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.repositories.AdministratorEntityRepository;
import etf.unibl.org.ip.repositories.PersonEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    final
    AdministratorEntityRepository administratorEntityRepository;
    private final PersonService personService;
    private final PersonEntityRepository personEntityRepository;

    public AdministratorService(AdministratorEntityRepository administratorEntityRepository, PersonService personService, PersonEntityRepository personEntityRepository) {
        this.administratorEntityRepository = administratorEntityRepository;
        this.personService = personService;
        this.personEntityRepository = personEntityRepository;
    }

    public List<AdministratorDTO> getAllAdministrators() {
        return administratorEntityRepository.findAllAdministrators();
    }

    public AdministratorDTO getAdministratorById(Integer id) {
        Optional<AdministratorDTO> administratorDTO = administratorEntityRepository.findAdministratorById(id);
        if (administratorDTO.isPresent()) {
            return administratorDTO.get();
        }
        return null;

    }

    @Transactional
    public ResponseEntity<HttpStatus> saveAdministrator(PersonEntity personEntity) {

        //OVDJE POZVATI SERVIS IZ KLASE PERSONSERVICE DA PROVJERI DA LI POSTOJI KORISNIK SA USERNAME

        ResponseEntity<HttpStatus> httpStatus = personService.registerNewPerson(personEntity);
        if (httpStatus.getStatusCode() == HttpStatus.CREATED) {
            AdministratorEntity administratorEntity = new AdministratorEntity();
            administratorEntity.setPerson(personEntity);
            administratorEntityRepository.save(administratorEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else
            return httpStatus;

       //ovaj dio ne smije biti, jer u klasi AdministratorEntity ima @MapId koja ovo
        //automatski radi
      // administratorEntity.setId(person.getId());

    }

    @Transactional
    public ResponseEntity<HttpStatus> updateAdministrator(AdministratorDTO administratorDTO) {
        Optional<AdministratorEntity> administratorEntityOptional = administratorEntityRepository.findById(administratorDTO.getId());
        if(administratorEntityOptional.isPresent()) {
            Optional<PersonEntity> personEntityOptional = personEntityRepository.findById(administratorDTO.getId());

            PersonEntity personEntity = personEntityOptional.get();
            personEntity.setName(administratorDTO.getName());
            personEntity.setSurname(administratorDTO.getSurname());
            personEntity.setUsername(administratorDTO.getUsername());
            personEntity.setRole(administratorDTO.getRole());

            personEntityRepository.save(personEntity);


            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }



    //treba obrisati u tabeli administrator i person
    //treba da dobavim id i da na osnovu toga obrisem u obe tabele zapis
    @Transactional
    public ResponseEntity<HttpStatus> deleteAdministrator(int id) {
        AdministratorDTO administratorDTO = getAdministratorById(id);
        if (administratorDTO != null) {
            Integer identifier = administratorDTO.getId();
            administratorEntityRepository.deleteById(identifier);
           ResponseEntity<HttpStatus> statusResponseEntity = personService.deleteById(identifier);
           return statusResponseEntity;
        }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HttpStatus> login(LoginDTO loginDTO) {
        return personService.login(loginDTO);
    }

}
