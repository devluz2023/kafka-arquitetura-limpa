package br.com.faluz.infra.dataprovider;

import br.com.faluz.domain.gateway.IDeviceEventGateway;
import br.com.faluz.infra.db.model.DeviceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceEventDataProvider implements IDeviceEventGateway {

    private final DeviceEventRepository deviceEventRepository;
    private final ExternalApiService externalApiService;

    @Override
    public void save(br.com.faluz.domain.entity.DeviceEvent deviceEvent) {
        DeviceEvent entity = new DeviceEvent(deviceEvent.getId(), deviceEvent.getDevice(), deviceEvent.getReleased());
        deviceEventRepository.save(entity);
    }

    @Override
    public void callExternalApi(br.com.faluz.domain.entity.DeviceEvent deviceEvent) {
        externalApiService.callExternalApi(deviceEvent.getDevice());
    }
}
