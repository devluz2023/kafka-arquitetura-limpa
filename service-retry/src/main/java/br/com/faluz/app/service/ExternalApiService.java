package br.com.faluz.app.service;

import br.com.faluz.domain.entity.Device;
import br.com.faluz.domain.usecase.impl.APICallUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    private final APICallUseCase apiCallUseCase;

    @Autowired
    public ExternalApiService(APICallUseCase apiCallUseCase) {
        this.apiCallUseCase = apiCallUseCase;
    }

    public ResponseEntity<Device>
    callExternalApi(Device request) {
        return apiCallUseCase.callAPI(request);
    }
}
