package br.com.faluz.domain.usecase.impl;

import br.com.faluz.domain.entity.Device;
import br.com.faluz.domain.usecase.IAPICallUseCase;
import br.com.faluz.infra.dataprovider.DeviceEventDataProvider;
import org.springframework.stereotype.Component;

@Component
public class APICallUseCase implements IAPICallUseCase {

    private final DeviceEventDataProvider deviceEventDataProvider;

    public APICallUseCase(DeviceEventDataProvider deviceEventDataProvider) {
        this.deviceEventDataProvider = deviceEventDataProvider;
    }

    @Override
    public Device callAPI(Device request) {
        return deviceEventDataProvider.callExternalApi(request);
    }


}
