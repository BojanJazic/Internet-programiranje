package etf.unibl.org.ip.service;

import etf.unibl.org.ip.dto.MalfunctionDTO;
import etf.unibl.org.ip.dto.MalfunctionResponseDTO;
import etf.unibl.org.ip.entities.MalfunctionEntity;
import etf.unibl.org.ip.entities.VehicleEntity;
import etf.unibl.org.ip.repositories.MalfunctionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MalfunctionService {

    private final MalfunctionEntityRepository malfunctionEntityRepository;
    private final VehicleService vehicleService;

    public MalfunctionService(MalfunctionEntityRepository malfunctionEntityRepository, VehicleService vehicleService) {
        this.malfunctionEntityRepository = malfunctionEntityRepository;
        this.vehicleService = vehicleService;
    }

    public List<MalfunctionResponseDTO> getAllMalfunctions() {
        List<MalfunctionEntity> malfunctions = malfunctionEntityRepository.findAll();
        return malfunctions.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private MalfunctionResponseDTO convertToResponseDTO(MalfunctionEntity malfunctionEntity) {
        MalfunctionResponseDTO responseDTO = new MalfunctionResponseDTO();
        responseDTO.setIdMalfunction(malfunctionEntity.getId());
        responseDTO.setIdVehicle(malfunctionEntity.getIdVehicle().getIdVehicle()); // Pretpostavka da VehicleEntity ima getIdVehicle()
        responseDTO.setDescription(malfunctionEntity.getDescription());
        responseDTO.setDateTime(malfunctionEntity.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return responseDTO;
    }

    public ResponseEntity<HttpStatus> addNewMalfunction(MalfunctionDTO malfunctionDTO) {

//        if(malfunctionDTO.getDateTime() != null) {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }

        System.out.println("Primjen id = " + malfunctionDTO.getIdVehicle());

        MalfunctionEntity malfunctionEntity = new MalfunctionEntity();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(malfunctionDTO.getDateTime(), formatter);

        Optional<VehicleEntity> vehicleEntity = vehicleService.vehicleEntityRepository.findByIdVehicle(malfunctionDTO.getIdVehicle());
        if(vehicleEntity.isPresent()) {
            malfunctionEntity.setIdVehicle(vehicleEntity.get());
            malfunctionEntity.setDescription(malfunctionDTO.getDescription());
            malfunctionEntity.setDateTime(dateTime);
            malfunctionEntityRepository.save(malfunctionEntity);
            if (malfunctionEntityRepository.existsById(malfunctionEntity.getId())) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<HttpStatus> deleteMalfunction(Integer idMalfunction) {
        malfunctionEntityRepository.deleteById(idMalfunction);
        if(!malfunctionEntityRepository.existsById(idMalfunction)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<MalfunctionResponseDTO> getMalfunctionsByVehicleId(String idVehicle) {
        return malfunctionEntityRepository.getMalfunctionEntityByIdVehicleId(idVehicle);
    }


    public Map<String, Integer> getNumberOfMalfunctionsPerVehicle() {
        List<MalfunctionEntity> malfunctions = malfunctionEntityRepository.getAllRentalEntities();

        return malfunctions.stream()
                .collect(Collectors.groupingBy(
                        malfunction -> malfunction.getIdVehicle().getIdVehicle(), // Grupiši po idVehicle
                        Collectors.collectingAndThen(
                                Collectors.counting(), // Prebroj kvarove
                                Long::intValue // Konvertuj Long u Integer
                        )
                ));
    }


}
