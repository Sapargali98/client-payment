package kz.dar.academy.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends ElasticsearchRepository<PaymentEntity,String> {
    PaymentEntity getPaymentEntityByPaymentId(String paymentId);
    Page<PaymentEntity> getPaymentEntityByClientId(String clientId, Pageable pageable);
    void deletePaymentEntityByPaymentId(String paymentId);
}
