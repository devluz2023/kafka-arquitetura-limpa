package br.com.faluz.app.service;

import br.com.faluz.app.dto.DeviceContractedEventDTO;
import br.com.faluz.app.dto.PaymentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceFacade {

    private final PaymentService paymentService;

    public PaymentServiceFacade(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentResponseDTO processPayment(DeviceContractedEventDTO request) {
        return paymentService.handlePayment(request);
    }
}
