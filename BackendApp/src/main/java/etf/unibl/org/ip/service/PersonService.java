package etf.unibl.org.ip.service;

import etf.unibl.org.ip.dto.AdministratorDTO;
import etf.unibl.org.ip.dto.LoginDTO;
import etf.unibl.org.ip.dto.PersonDTO;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.repositories.PersonEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonEntityRepository personEntityRepository;

    public PersonService(PersonEntityRepository personEntityRepository) {
        this.personEntityRepository = personEntityRepository;
    }

    public PersonEntity save(PersonEntity personEntity) {
        return personEntityRepository.save(personEntity);
    }

    public List<PersonEntity> findAll() {
        return personEntityRepository.findAll();
    }

    public PersonEntity findById(int id) {
        return personEntityRepository.findById(id).orElse(null);
    }

    public ResponseEntity<HttpStatus> deleteById(int id) {
        Optional<PersonEntity> personEntityOptional = personEntityRepository.findById(id);
        if (personEntityOptional.isPresent()) {
            personEntityRepository.deleteById(personEntityOptional.get().getId());

            if(!personEntityRepository.existsById(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    public PersonEntity updatePerson(Integer id, PersonEntity updatedPerson) {
        Optional<PersonEntity> existingPersonOpt = personEntityRepository.findById(id);

        if (existingPersonOpt.isPresent()) {
            PersonEntity existingPerson = existingPersonOpt.get();

            existingPerson.setName(updatedPerson.getName());
            existingPerson.setSurname(updatedPerson.getSurname());
            existingPerson.setUsername(updatedPerson.getUsername());
            existingPerson.setPassword(existingPerson.getPassword()); // Razmisli o enkodiranju lozinke!
            existingPerson.setRole(updatedPerson.getRole());
            System.out.println("ROLE: " + updatedPerson.getRole());

            return personEntityRepository.save(existingPerson);
        } else {
            return null; // Možeš baciti i custom izuzetak
        }
    }


    //sifra ce stici u obliku hash-a
    //na frontend dijelu prilikom preuzimanja vrijednosti lozinke napravicu hash

    //REGISTRACIJA NOVOG KORISNIKA
    public ResponseEntity<HttpStatus> registerNewPerson(PersonEntity personEntity) {
        //provjeriti da li postoji osoba sa ovim korisnickim imenom
        if(personEntityRepository.existsByUsername(personEntity.getUsername())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else{
            //potrebno dodati u tabelu
            String password = personEntity.getPassword();

            String salt = PasswordHasher.generateSalt();
            String hashedPassword = PasswordHasher.hashPassword(password);
            personEntity.setPassword(hashedPassword);
            save(personEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }


    //PRIJAVA KORISNIKA
    public ResponseEntity<HttpStatus> login(LoginDTO loginDTO) {
        //provjera da li su ispravni username i lozinka

        String hashedPassword = PasswordHasher.hashPassword(loginDTO.getPassword());
        if(personEntityRepository.existsByUsernameAndPasswordAndRole(loginDTO.getUsername(), hashedPassword, loginDTO.getRole())){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    //dohvatanje svake vrste vozila

    public AdministratorDTO getAdministratorByUsername(String username) {
        return personEntityRepository.getAdministratorByUsername(username);
    }

    public List<PersonDTO> getAllEmployees(){
        return personEntityRepository.getAllPersonDtos();
    }


}
