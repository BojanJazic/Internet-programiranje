package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.MalfunctionDTO;
import etf.unibl.org.ip.dto.MalfunctionResponseDTO;
import etf.unibl.org.ip.entities.MalfunctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MalfunctionEntityRepository extends JpaRepository<MalfunctionEntity, Integer> {

//    @Query("select new etf.unibl.org.ip.dto.MalfunctionResponseDTO(m.id, m.idVehicle.idVehicle, m.description, m.dateTime) from MalfunctionEntity m")
//    List<MalfunctionDTO> findAllMalfunctions();
    boolean getMalfunctionEntityById(Integer id);

    @Query("select new etf.unibl.org.ip.dto.MalfunctionResponseDTO(m.id, m.idVehicle.idVehicle, m.description, CAST(m.dateTime AS string)) from MalfunctionEntity m where m.idVehicle.idVehicle=:vehicleId")
        List<MalfunctionResponseDTO> getMalfunctionEntityByIdVehicleId(String vehicleId);

    @Query("select m from MalfunctionEntity m")
    List<MalfunctionEntity> getAllRentalEntities();



}
