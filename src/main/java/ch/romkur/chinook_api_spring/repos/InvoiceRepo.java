package ch.romkur.chinook_api_spring.repos;
import ch.romkur.chinook_api_spring.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByCustomerId(Integer customerId);
}
