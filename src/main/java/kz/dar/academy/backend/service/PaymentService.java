package kz.dar.academy.backend.service;

import kz.dar.academy.backend.model.PaymentRequest;
import kz.dar.academy.backend.model.PaymentResponse;
import kz.dar.academy.backend.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);
    PaymentResponse updatePayment(PaymentRequest paymentRequest);
    PaymentResponse getPaymentById(String paymentId);
    void deletePayment(String paymentId);
    Page<PaymentResponse> getPaymentsByClientId(String clientId, Pageable pageable);

}
