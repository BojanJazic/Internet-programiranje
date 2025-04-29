package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.entities.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerEntityRepository extends JpaRepository<ManufacturerEntity, Integer> {

}
