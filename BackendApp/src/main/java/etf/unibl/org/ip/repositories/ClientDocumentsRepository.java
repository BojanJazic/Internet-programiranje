package etf.unibl.org.ip.repositories;

import etf.unibl.org.ip.dto.ClientDocumentDTO;
import etf.unibl.org.ip.dto.ClientDocumentResponseDTO;
import etf.unibl.org.ip.entities.ClientDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDocumentsRepository extends JpaRepository<ClientDocumentEntity, Integer> {


    /**
     * potrebno napisati izraz koji ce spojiti tabele client_documents, client i person
     * u ClientDTO klasi imam ClientDocumentDTO, a onda samo moram dodati jos i atribute iz klase Person u ClientDTO
     *
     * -----------------------
     * mada, mozda bi bolje bilo da u klasi ClientDocumentDTO imam ClientDTO i ove podatke sto trebaju
     * najlakse mi je preko nje doci do svih bitnih informacija
     *
     * */
    @Query("select new etf.unibl.org.ip.dto.ClientDocumentResponseDTO(cd.id, p.name, p.surname, p.username, " +
            "c.email, c.phone, c.avatar, c.isBlocked, cd.idCard, cd.passport, cd.driverLicenseNumber)" +
            " from ClientDocumentEntity cd join cd.client c join c.person p")
    List<ClientDocumentResponseDTO> getAllClientDocumentEntities();

    @Query("select new etf.unibl.org.ip.dto.ClientDocumentResponseDTO(cd.id, p.name, p.surname, p.username, " +
            "c.email, c.phone, c.avatar, c.isBlocked, cd.idCard, cd.passport, cd.driverLicenseNumber) " +
            "from ClientDocumentEntity cd join cd.client c join c.person p where cd.id=:id")
    ClientDocumentResponseDTO getClientDocumentEntityById(Integer id);

}
