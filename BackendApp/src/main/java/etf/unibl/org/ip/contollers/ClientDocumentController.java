package etf.unibl.org.ip.contollers;


import etf.unibl.org.ip.dto.ClientDocumentDTO;
import etf.unibl.org.ip.dto.ClientDocumentResponseDTO;
import etf.unibl.org.ip.repositories.ClientDocumentsRepository;
import etf.unibl.org.ip.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clients")
public class ClientDocumentController {

    private final ClientService clientService;


    public ClientDocumentController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public List<ClientDocumentResponseDTO> getClients() {
        return clientService.getAllClientDocuments();
    }

    //mislim da ovo nece trebati, ali nek  stoji zasad
    @GetMapping("/byId/{id}")
    public ClientDocumentResponseDTO getClientById(@PathVariable int id) {
        return clientService.getClientDocumentById(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createClient(@RequestBody ClientDocumentDTO clientDocumentDTO) {
        return clientService.addNewClient(clientDocumentDTO);
    }

    @PutMapping("/{idPerson}")
    public ResponseEntity<HttpStatus> updateClient(@PathVariable int idPerson) {
        return clientService.updateClientStatus(idPerson);
    }





}
