package etf.unibl.org.ip.service;

import ch.qos.logback.core.net.server.Client;
import etf.unibl.org.ip.dto.ClientDocumentDTO;
import etf.unibl.org.ip.dto.ClientDocumentResponseDTO;
import etf.unibl.org.ip.entities.ClientDocumentEntity;
import etf.unibl.org.ip.entities.ClientEntity;
import etf.unibl.org.ip.entities.PersonEntity;
import etf.unibl.org.ip.repositories.ClientDocumentsRepository;
import etf.unibl.org.ip.repositories.ClientEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

   private final ClientDocumentsRepository clientDocumentsRepository;

    private final PersonService personService;
    private final ClientEntityRepository clientEntityRepository;

    public ClientService(ClientDocumentsRepository clientDocumentsRepository, PersonService personService, ClientEntityRepository clientEntityRepository) {
        this.clientDocumentsRepository = clientDocumentsRepository;
        this.personService = personService;
        this.clientEntityRepository = clientEntityRepository;
    }

    public List<ClientDocumentResponseDTO> getAllClientDocuments() {
        return clientDocumentsRepository.getAllClientDocumentEntities();
    }

    public ClientDocumentResponseDTO getClientDocumentById(int id) {
        return clientDocumentsRepository.getClientDocumentEntityById(id);
    }

    /*
    * bice potrebna dva dto za dokumenta, jer prilikom prijave ce trebati i lozinku poslati,
    * a prilikom odgovora ona ne treba biti sadrzana
    * */

    @Transactional
    public ResponseEntity<HttpStatus> addNewClient(ClientDocumentDTO clientDocumentDTO) {
        //potrebno kreirati novu osobu, klijenta i dokument

        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(clientDocumentDTO.getName());
        personEntity.setSurname(clientDocumentDTO.getSurname());
        personEntity.setUsername(clientDocumentDTO.getUsername());
        personEntity.setPassword(clientDocumentDTO.getPassword());
        personEntity.setRole("CLIENT");

        ResponseEntity<HttpStatus> response = personService.registerNewPerson(personEntity);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            ClientEntity clientEntity = new ClientEntity();
            //clientEntity.setId(personEntity.getId());
            clientEntity.setPerson(personEntity);
            clientEntity.setEmail(clientDocumentDTO.getEmail());
            clientEntity.setPhone(clientDocumentDTO.getPhone());
            clientEntity.setAvatar(clientDocumentDTO.getAvatar());
            clientEntity.setIsBlocked((byte) 0);

            clientEntityRepository.save(clientEntity);

            ClientDocumentEntity clientDocumentEntity = new ClientDocumentEntity();
           // clientDocumentEntity.setId(clientEntity.getId());
            clientDocumentEntity.setClient(clientEntity);
            clientDocumentEntity.setIdCard(clientDocumentDTO.getIdCard());
            clientDocumentEntity.setPassport(clientDocumentDTO.getPassport());
            clientDocumentEntity.setDriverLicenseNumber(clientDocumentDTO.getDriverLicenseNumber());

            clientDocumentsRepository.save(clientDocumentEntity);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return response;
    }

    public ResponseEntity<HttpStatus> updateClientStatus(int idPerson){
        ClientEntity clientEntity = clientEntityRepository.getReferenceById(idPerson);

        if(clientEntity.getIsBlocked() == (byte)0){
            clientEntity.setIsBlocked((byte) 1);
            clientEntityRepository.save(clientEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } else if(clientEntity.getIsBlocked() == (byte)1){
            clientEntity.setIsBlocked((byte) 0);
            clientEntityRepository.save(clientEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
