package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.AdministratorDTO;
import etf.unibl.org.ip.entities.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministratorEntityRepository extends JpaRepository<AdministratorEntity, Integer> {

    @Query("select new etf.unibl.org.ip.dto.AdministratorDTO(m.id, p.name, p.surname, p.username, p.role)" +
            " from ManagerEntity m join m.person p")
    List<AdministratorDTO> findAllAdministrators();


    //sa :id pristupam argumentu koji saljem za filtriranje
    @Query("select new etf.unibl.org.ip.dto.AdministratorDTO(m.id, p.name, p.surname, p.username, p.role)" +
            " from ManagerEntity m join m.person p where m.id=:id")
    Optional<AdministratorDTO> findAdministratorById(@Param("id") Integer id);

}
