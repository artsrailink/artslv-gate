package co.id.artslv.repository;

import co.id.artslv.lib.boarding.Boarding;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 26/09/16.
 */
public interface BoardingRepository extends JpaRepository<Boarding,String>{
    Boarding findByTicketnumAndStatus(String ticketnum,String status);
    List<Boarding> findByTicketnum(String ticketnum);
}
