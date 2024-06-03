package br.com.faluz.domain.usecase;

import br.com.faluz.domain.entity.Device;
import org.springframework.http.ResponseEntity;

public interface IAPICallUseCase {
    ResponseEntity<Device> callAPI(Device request);
}
