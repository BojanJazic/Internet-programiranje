package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.OperatorDTO;
import etf.unibl.org.ip.entities.OperatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperatorEntityRepository extends JpaRepository<OperatorEntity, Integer> {

    @Query("select new etf.unibl.org.ip.dto.OperatorDTO(o.id, p.name, p.surname, " +
            " p.username) " +
            " from OperatorEntity o join o.person p")
    List<OperatorDTO> findAllOperators();

    @Query("select new etf.unibl.org.ip.dto.OperatorDTO(o.id, p.name, p.surname, p.username) " +
            " from OperatorEntity o join o.person p where o.id=:id")
    Optional<OperatorDTO> findOperatorEntityById(int id);

}
