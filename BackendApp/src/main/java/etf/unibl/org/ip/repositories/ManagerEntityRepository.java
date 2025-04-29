package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.ManagerDTO;
import etf.unibl.org.ip.entities.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagerEntityRepository extends JpaRepository<ManagerEntity, Integer> {

    @Query("select new etf.unibl.org.ip.dto.ManagerDTO(m.id, p.name, p.surname, p.username)" +
            " from ManagerEntity m join m.person p")
    List<ManagerDTO> findAllManagers();

    @Query("select new etf.unibl.org.ip.dto.ManagerDTO(m.id, p.name, p.surname, p.username)" +
            " from ManagerEntity m join m.person p where m.id=:id")
    Optional<ManagerDTO> findManagerEntityById(int id);
}
