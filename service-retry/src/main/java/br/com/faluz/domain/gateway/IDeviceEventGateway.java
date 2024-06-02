package br.com.faluz.domain.gateway;

import br.com.faluz.domain.entity.DeviceEvent;

public interface IDeviceEventGateway {

    /**
     * Save a device event.
     *
     * @param deviceEvent the device event to save
     */
    void save(DeviceEvent deviceEvent);

    /**
     * Call an external API with the device event.
     *
     * @param deviceEvent the device event to send
     */
    void callExternalApi(DeviceEvent deviceEvent);
}
