package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientEntityRepository extends JpaRepository<ClientEntity, Integer> {



}
