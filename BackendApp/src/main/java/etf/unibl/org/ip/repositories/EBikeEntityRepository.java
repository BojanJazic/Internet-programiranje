package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.EBikeDTO;
import etf.unibl.org.ip.entities.EBikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EBikeEntityRepository extends JpaRepository<EBikeEntity, Integer> {

    @Query("select new etf.unibl.org.ip.dto.EBikeDTO(b.idVehicle, v.idManufacturer.id, v.idManufacturer.name, v.model, v.purchasePrice," +
            " v.isRented, v.isBroken, v.image, b.autonomy) from " +
            " EBikeEntity b join b.vehicle v join v.idManufacturer")
    public List<EBikeDTO> getAllEBikeDTO();

    @Query("select new etf.unibl.org.ip.dto.EBikeDTO(b.idVehicle, v.idManufacturer.id, v.idManufacturer.name, v.model, " +
            " v.purchasePrice, v.isRented, v.isBroken, v.image, b.autonomy)" +
            " from EBikeEntity b join b.vehicle v join v.idManufacturer where b.idVehicle=:id")
    EBikeDTO getEBikeDTOByIdVehicle(String id);

    void deleteEBikeEntityByIdVehicle(String id);

    boolean existsEBikeEntityByIdVehicle(String id);

    Optional<EBikeEntity> findEBikeEntityByIdVehicle(String id);

}
