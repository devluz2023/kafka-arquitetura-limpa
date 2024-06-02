package br.com.faluz.infra.db.repository;

import br.com.faluz.infra.db.model.Payment;
import br.com.faluz.infra.db.model.PaymentEntity;
import org.springframework.stereotype.Repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {

    private final IPaymentRepository deviceEventRepository;

    public void save(PaymentEntity payment) {
        deviceEventRepository.save(payment);
    }

}