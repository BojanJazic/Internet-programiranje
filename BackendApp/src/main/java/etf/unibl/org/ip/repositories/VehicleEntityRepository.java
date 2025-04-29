package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.VehicleResponseDTO;
import etf.unibl.org.ip.dto.VehicleResponseWithPriceDTO;
import etf.unibl.org.ip.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.HttpHeadersReturnValueHandler;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleEntityRepository extends JpaRepository<VehicleEntity, Integer> {

    boolean existsByIdVehicle(String idVehicle);

    Optional<VehicleEntity> findByIdVehicle(String idVehicle);

    void deleteByIdVehicle(String idVehicle);



    @Query("select new etf.unibl.org.ip.dto.VehicleResponseDTO(v.idVehicle, m.name, v.model, v.rentalPrice) from VehicleEntity v join v.idManufacturer m")
    List<VehicleResponseDTO> getAllVehicles();


    @Query("select new etf.unibl.org.ip.dto.VehicleResponseWithPriceDTO(v.idVehicle, m.name, v.model, v.rentalPrice) from VehicleEntity v join v.idManufacturer m")
    List<VehicleResponseWithPriceDTO> getAllVehiclesWithPrice();

    @Modifying
    @Transactional
    @Query("update VehicleEntity v set v.rentalPrice = :price where v.idVehicle = :idVehicle")
    Integer updateVehicle(String idVehicle, int price);

    @Modifying
    @Transactional
    @Query("update VehicleEntity v set v.isBroken = 1 where v.idVehicle = :idVehicle")
    Integer updateVehicleMalfunction(String idVehicle);

    @Modifying
    @Transactional
    @Query("update VehicleEntity v set v.isBroken = 0 where v.idVehicle = :idVehicle")
    Integer repairVehicle(String idVehicle);
}
