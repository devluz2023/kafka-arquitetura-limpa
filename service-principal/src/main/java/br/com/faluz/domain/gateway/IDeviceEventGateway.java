package br.com.faluz.domain.gateway;

import br.com.faluz.domain.entity.Device;


public interface IDeviceEventGateway {

    /**
     * Save a device event.
     *
     * @param deviceEvent the device event to save
     */
    void save(Device deviceEvent);

    /**
     * Call an external API with the device event.
     *
     * @param deviceEvent the device event to send
     */
    void callExternalApi(Device deviceEvent);
}
