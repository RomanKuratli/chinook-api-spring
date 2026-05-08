package ch.romkur.chinook_api_spring.repos;

import ch.romkur.chinook_api_spring.model.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceLineRepo extends JpaRepository<InvoiceLine, Integer> {
    List<InvoiceLine> findByInvoiceId(Integer invoiceId);
}
