package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.AdministratorDTO;
import etf.unibl.org.ip.dto.PersonDTO;
import etf.unibl.org.ip.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonEntityRepository extends JpaRepository<PersonEntity, Integer> {

    @Override
    Optional<PersonEntity> findById(Integer integer);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPasswordAndRole(String username, String password, String role);

    @Query("select new etf.unibl.org.ip.dto.AdministratorDTO(p.id, p.name, p.surname, p.username, p.role) from PersonEntity p where p.username=:username")
    AdministratorDTO getAdministratorByUsername(String username);

    @Query("select new etf.unibl.org.ip.dto.PersonDTO(p.id, p.name, p.surname, p.username, p.role) from PersonEntity p where p.role != 'CLIENT'")
    List<PersonDTO> getAllPersonDtos();
}
