package br.com.faluz.infra.db.model;
import java.time.LocalDate;

public class DeviceEvent {
    private String device;
    private LocalDate releaseDate;
    private String clientName;

    // Default constructor
    public DeviceEvent() {}

    // Parameterized constructor
    public DeviceEvent(String device) {
        this.device = device;
    }

    // Parameterized constructor with all fields
    public DeviceEvent(String device, LocalDate releaseDate, String clientName) {
        this.device = device;
        this.releaseDate = releaseDate;
        this.clientName = clientName;
    }

    // Getters and setters
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}