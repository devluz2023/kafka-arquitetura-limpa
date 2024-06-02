package br.com.faluz.infra.db.repository;


import br.com.faluz.infra.db.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<PaymentEntity,
        String> {
}
