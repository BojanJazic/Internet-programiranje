package etf.unibl.org.ip.service;


import etf.unibl.org.ip.entities.ManufacturerEntity;
import etf.unibl.org.ip.repositories.ManufacturerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerEntityRepository manufacturerEntityRepository;


    public ManufacturerService(ManufacturerEntityRepository manufacturerEntityRepository) {
        this.manufacturerEntityRepository = manufacturerEntityRepository;
    }

    public boolean manufacturerExists(int id) {
        return manufacturerEntityRepository.existsById(id);
    }

    public ManufacturerEntity getManufacturer(int id) {
        return manufacturerEntityRepository.findById(id).get();
    }

    //all manufacturers
    public List<ManufacturerEntity> getAllManufacturers() {
        return manufacturerEntityRepository.findAll();
    }

    //manufacturer by id
    public ManufacturerEntity getManufacturerById(int id) {
        return manufacturerEntityRepository.findById(id).get();
    }

    //add new
    public ResponseEntity<HttpStatus> addManufacturer(ManufacturerEntity manufacturer) {
        manufacturerEntityRepository.save(manufacturer);
        if(manufacturerEntityRepository.existsById(manufacturer.getId())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //update
    public ResponseEntity<HttpStatus> updateManufacturer(ManufacturerEntity manufacturer) {

        Optional<ManufacturerEntity> manufacturerOptional = manufacturerEntityRepository.findById(manufacturer.getId());

        if(manufacturerOptional.isPresent()) {
            ManufacturerEntity manufacturerEntity = manufacturerOptional.get();
            manufacturerEntity.setId(manufacturer.getId());
            manufacturerEntity.setName(manufacturer.getName());
            manufacturerEntity.setState(manufacturer.getState());
            manufacturerEntity.setAddress(manufacturer.getAddress());
            manufacturerEntity.setPhone(manufacturer.getPhone());
            manufacturerEntity.setFax(manufacturer.getFax());
            manufacturerEntity.setEmail(manufacturer.getEmail());

            manufacturerEntityRepository.save(manufacturerEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //delete
    public ResponseEntity<HttpStatus> deleteManufacturer(int id) {
        manufacturerEntityRepository.deleteById(id);

        if(manufacturerEntityRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
