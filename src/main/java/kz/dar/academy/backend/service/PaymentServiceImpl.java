package kz.dar.academy.backend.service;

import kz.dar.academy.backend.model.PaymentRequest;
import kz.dar.academy.backend.model.PaymentResponse;
import kz.dar.academy.backend.repository.PaymentEntity;
import kz.dar.academy.backend.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    static ModelMapper modelMapper=new ModelMapper();
    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        paymentRequest.setPaymentId(UUID.randomUUID().toString());
        PaymentEntity paymentEntity=modelMapper.map(paymentRequest,PaymentEntity.class);
        paymentEntity=paymentRepository.save(paymentEntity);
        return modelMapper.map(paymentEntity,PaymentResponse.class);
    }

    @Override
    public PaymentResponse updatePayment(PaymentRequest paymentRequest) {
        PaymentEntity paymentEntity=modelMapper.map(paymentRequest,PaymentEntity.class);
        PaymentEntity dbEntity=paymentRepository.getPaymentEntityByPaymentId(paymentRequest.getPaymentId());
        dbEntity.setClientId(paymentRequest.getClientId());
        dbEntity.setType(paymentRequest.getType());
        dbEntity.setDescription(paymentRequest.getDescription());
        paymentEntity=paymentRepository.save(dbEntity);
        return modelMapper.map(paymentEntity,PaymentResponse.class);
    }

    @Override
    public PaymentResponse getPaymentById(String paymentId) {
        PaymentEntity paymentEntity=paymentRepository.getPaymentEntityByPaymentId(paymentId);
        return modelMapper.map(paymentEntity,PaymentResponse.class);
    }

    @Override
    public void deletePayment(String paymentId) {
        paymentRepository.deletePaymentEntityByPaymentId(paymentId);

    }

    @Override
    public Page<PaymentResponse> getPaymentsByClientId(String clientId, Pageable pageable) {

        return paymentRepository.getPaymentEntityByClientId(clientId, pageable)
                .map(payment-> modelMapper.map(payment,PaymentResponse.class));
    }
}
