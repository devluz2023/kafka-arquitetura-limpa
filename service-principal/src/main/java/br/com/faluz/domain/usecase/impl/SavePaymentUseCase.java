package br.com.faluz.domain.usecase.impl;

import br.com.faluz.domain.entity.EPayment;
import br.com.faluz.domain.gateway.IPaymentGateway;
import br.com.faluz.domain.usecase.ISavePaymentUseCase;
import br.com.faluz.infra.db.repository.IPaymentRepository;
import br.com.faluz.infra.kafka.producer.PaymentEventProducer;
import br.com.faluz.app.dto.DeviceReleasedEventDTO;
import br.com.faluz.app.dto.ErrorEnum;
import br.com.faluz.app.dto.RetryEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SavePaymentUseCase implements ISavePaymentUseCase {

    private final IPaymentRepository paymentRepository;
    private final IPaymentGateway paymentGateway;
    private final PaymentEventProducer paymentEventProducer;

    @Autowired
    public SavePaymentUseCase(IPaymentRepository paymentRepository, IPaymentGateway paymentGateway, PaymentEventProducer paymentEventProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentGateway = paymentGateway;
        this.paymentEventProducer = paymentEventProducer;
    }

    @Override
    @Transactional
    public void savePayment(EPayment payment) {
        try {
            paymentRepository.save(payment);
            // Publish success event to Kafka
            DeviceReleasedEventDTO event = new DeviceReleasedEventDTO(payment.getDevice());
            paymentEventProducer.sendDeviceReleasedEvent(event);
        } catch (Exception e) {
            // Publish retry event to Kafka
            RetryEventDTO retryEvent = new RetryEventDTO(payment.getDevice(), ErrorEnum.DATABASEERROR, e.getMessage());
            paymentEventProducer.sendRetryEvent(retryEvent);
            // Optionally, you can throw the exception again to signal the caller
            throw e;
        }
    }
}
