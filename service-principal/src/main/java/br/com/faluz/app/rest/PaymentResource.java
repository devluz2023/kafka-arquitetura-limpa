package br.com.faluz.app.rest;

import br.com.faluz.app.dto.DeviceContractedEventDTO;
import br.com.faluz.app.dto.PaymentResponseDTO;
import br.com.faluz.app.service.PaymentServiceFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    private final PaymentServiceFacade paymentServiceFacade;

    public PaymentResource(PaymentServiceFacade paymentServiceFacade) {
        this.paymentServiceFacade = paymentServiceFacade;
    }

    @PostMapping("/device/contracted")
    public ResponseEntity<PaymentResponseDTO> handleDeviceContracted(@RequestBody DeviceContractedEventDTO request) {
        PaymentResponseDTO response = paymentServiceFacade.processPayment(request);
        return ResponseEntity.status(response.getStatus().equals("OK") ? 201 : 500).body(response);
    }
}
