package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.RentalResponseDTO;
import etf.unibl.org.ip.entities.RentalEntity;
import etf.unibl.org.ip.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalEntityRepository extends CrudRepository<RentalEntity, Integer> {

    @Query("select new etf.unibl.org.ip.dto.RentalResponseDTO(r.id, v.idVehicle, m.name, v.model , p.person.name, p.person.surname, CAST(r.dateTime as string), " +
            "r.rentingDuration, new etf.unibl.org.ip.dto.LocationDTO(pl.latitude, pl.longitude), " +
            "new etf.unibl.org.ip.dto.LocationDTO(dl.latitude, dl.longitude), r.rentalPrice) " +
            "from RentalEntity r join r.idVehicle v join r.idPerson p join r.pickupLocation pl join r.dropoffLocation dl join v.idManufacturer m where r.idVehicle.idVehicle=:idVehicle order by r.id asc")
    List<RentalResponseDTO> getAllRentals(String idVehicle);


    @Query("select new etf.unibl.org.ip.dto.RentalResponseDTO(r.id, v.idVehicle, m.name, v.model , p.person.name, p.person.surname, CAST(r.dateTime as string), " +
            "             r.rentingDuration, new etf.unibl.org.ip.dto.LocationDTO(pl.latitude, pl.longitude), " +
            "            new etf.unibl.org.ip.dto.LocationDTO(dl.latitude, dl.longitude), r.rentalPrice)" +
            " from RentalEntity r join r.idVehicle v join r.idPerson p join r.pickupLocation pl join r.dropoffLocation dl join v.idManufacturer m")
    List<RentalResponseDTO> getAllRentals();

    @Query("SELECT r FROM RentalEntity r")
    List<RentalEntity> getAllRentalEntities();
}
