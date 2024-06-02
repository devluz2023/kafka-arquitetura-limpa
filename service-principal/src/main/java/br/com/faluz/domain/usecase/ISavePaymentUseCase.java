package br.com.faluz.domain.usecase;


import br.com.faluz.domain.entity.EPayment;

public interface ISavePaymentUseCase {
    void savePayment(EPayment payment);
}