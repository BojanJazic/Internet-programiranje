package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.EScooterDTO;
import etf.unibl.org.ip.entities.EScooterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EScooterRepository extends JpaRepository<EScooterEntity, Integer> {

    @Query("select new etf.unibl.org.ip.dto.EScooterDTO(s.idVehicle, v.idManufacturer.id, v.idManufacturer.name, " +
            " v.model, v.purchasePrice, v.isRented, v.isBroken, v.image, s.maxSpeed) from EScooterEntity s " +
            " join s.vehicle v join v.idManufacturer")
    public List<EScooterDTO> getAllEScooters();

    @Query("select new etf.unibl.org.ip.dto.EScooterDTO(s.idVehicle, v.idManufacturer.id, v.idManufacturer.name," +
            " v.model, v.purchasePrice, v.isRented, v.isBroken, v.image, s.maxSpeed) " +
            "from EScooterEntity s join s.vehicle v join v.idManufacturer where s.idVehicle=:id")
    public EScooterDTO getEScooterDTOByIdVehicle(String id);

    void deleteEScooterEntityByIdVehicle(String idVehicle);

    boolean existsEScooterEntityByIdVehicle(String id);

    Optional<EScooterEntity> findEScooterEntityByIdVehicle(String id);

}
