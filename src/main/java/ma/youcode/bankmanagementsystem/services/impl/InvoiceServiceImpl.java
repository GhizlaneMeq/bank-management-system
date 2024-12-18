package ma.youcode.bankmanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.bankmanagementsystem.dtos.invoice.CreateInvoiceRequestDTO;
import ma.youcode.bankmanagementsystem.dtos.invoice.InvoiceResponseDTO;
import ma.youcode.bankmanagementsystem.entities.Invoice;
import ma.youcode.bankmanagementsystem.enums.InvoiceStatus;
import ma.youcode.bankmanagementsystem.mappers.InvoiceMapper;
import ma.youcode.bankmanagementsystem.repositories.InvoiceRepository;
import ma.youcode.bankmanagementsystem.services.InvoiceService;
import ma.youcode.bankmanagementsystem.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final UserService userService;

    @Override
    public InvoiceResponseDTO createInvoice(CreateInvoiceRequestDTO request) {
        Invoice invoice = invoiceMapper.toEntity(request);
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice.setUser(userService.getCurrentUser());
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceResponseDTO getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDTO)
                .orElse(null);
    }

    @Override
    public Page<InvoiceResponseDTO> getMyInvoices(Pageable pageable) {
        return invoiceRepository.findByUserId(userService.getCurrentUser().getId(), pageable)
                .map(invoiceMapper::toDTO);
    }

    @Override
    public InvoiceResponseDTO payInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) {
            return null;
        }
        invoice.setStatus(InvoiceStatus.PAID);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceResponseDTO cancelInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) {
            return null;
        }
        invoice.setStatus(InvoiceStatus.CANCELLED);
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(invoice);
    }

    /**
     * cron <=> "second minute hour day month day-of-week"
     * Available values: 0-59 0-59 0-23 1-31 1-12 0-7 or * for all values
     */
    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void processOverdueInvoices() {
        invoiceRepository.findByStatusAndDueDateBefore(InvoiceStatus.PENDING, LocalDate.now())
                .forEach(invoice -> {
                    invoice.setStatus(InvoiceStatus.OVERDUE);
                    invoiceRepository.save(invoice);
                });
    }
}
