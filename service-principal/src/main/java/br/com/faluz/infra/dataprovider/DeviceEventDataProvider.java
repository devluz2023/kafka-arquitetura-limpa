package br.com.faluz.infra.dataprovider;

import br.com.faluz.domain.entity.Device;
import br.com.faluz.domain.gateway.IDeviceEventGateway;
import br.com.faluz.infra.db.model.DeviceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceEventDataProvider implements IDeviceEventGateway {



    @Override
    public void save(Device deviceEvent) {

    }

    @Override
    public void callExternalApi(Device deviceEvent) {

    }
}
