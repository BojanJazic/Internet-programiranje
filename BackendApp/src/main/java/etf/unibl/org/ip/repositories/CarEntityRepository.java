package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.CarDTO;
import etf.unibl.org.ip.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarEntityRepository extends JpaRepository<CarEntity, Integer> {


    /*@Query("select new etf.unibl.org.ip.dto.AdministratorDTO(a.id, new etf.unibl.org.ip.dto.PersonDTO(p.id, p.name, p.surname," +
            "p.username)) from AdministratorEntity a join a.person p")*/

    /*@Query("select c from CarEntity  c " +
            "join fetch c.vehicle v " +
            "join fetch v.idManufacturer m")*/
    @Query("select new etf.unibl.org.ip.dto.CarDTO(c.idVehicle, v.idManufacturer.id, v.idManufacturer.name, v.model, v.purchasePrice, " +
            "v.isRented, v.isBroken, v.image, c.purchaseDate, c.description) from CarEntity c " +
            "join c.vehicle v join v.idManufacturer")
    List<CarDTO> getAllCars();

    @Query("select new etf.unibl.org.ip.dto.CarDTO(c.idVehicle, v.idManufacturer.id, v.idManufacturer.name, v.model, v.purchasePrice, " +
            "v.isRented, v.isBroken, v.image, c.purchaseDate, c.description) from CarEntity c " +
            "join c.vehicle v join v.idManufacturer where c.idVehicle=:idVehicle")
    CarDTO getCarById(String idVehicle);

    void deleteCarByIdVehicle(String idVehicle);

    boolean existsCarByIdVehicle(String idVehicle);

    Optional<CarEntity> findCarByIdVehicle(String idVehicle);



}
