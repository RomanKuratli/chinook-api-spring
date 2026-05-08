package ch.romkur.chinook_api_spring.controllers;

import ch.romkur.chinook_api_spring.dto.CustomerDto;
import ch.romkur.chinook_api_spring.dto.InvoiceLineDto;
import ch.romkur.chinook_api_spring.model.Customer;
import ch.romkur.chinook_api_spring.model.Invoice;
import ch.romkur.chinook_api_spring.model.InvoiceLine;
import ch.romkur.chinook_api_spring.repos.CustomerRepo;
import ch.romkur.chinook_api_spring.repos.InvoiceLineRepo;
import ch.romkur.chinook_api_spring.repos.InvoiceRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BusinessController {
    private final CustomerRepo custRepo;
    private final InvoiceRepo invoiceRepo;
    private final InvoiceLineRepo invoiceLineRepo;

    public BusinessController(CustomerRepo custRepo, InvoiceRepo invRepo, InvoiceLineRepo invLineRepo) {
        this.custRepo = custRepo;
        this.invoiceRepo = invRepo;
        this.invoiceLineRepo = invLineRepo;
    }

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers(
            @RequestParam(value = "search", required = false) String search
    ) {
        List<Customer> customers;
        if (search != null && !search.trim().isEmpty()) {
            customers = custRepo.findByLastnameContainingIgnoreCase(search);
        }
        else {
            customers = custRepo.findAll();
        }
        return customers.stream()
            .map(Customer::toDto)
            .toList();
    }

    @GetMapping("/customers/{id}/invoices")
    public List<Invoice> getInvoicesByCustomer(@PathVariable Integer id) {
        return invoiceRepo.findByCustomerId(id);
    }

    @GetMapping("/invoices/{id}/lines")
    public List<InvoiceLineDto> getInvoiceLinesByInvoice(@PathVariable Integer id) {
        return invoiceLineRepo.findByInvoiceId(id).stream()
                .map(InvoiceLine::toDto)
                .toList();
    }
}
