package br.com.faluz.domain.gateway;


import br.com.faluz.domain.entity.Device;

public interface IDeviceEventGateway {

    void callExternalApi(Device deviceEvent);
}
