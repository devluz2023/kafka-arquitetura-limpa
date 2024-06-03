package br.com.faluz.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Device {
    private String device;
    private Boolean released;
    public Device(String device, boolean released) {
        this.device = device;
        this.released = released; // Default value, change as necessary
    }
    public Device(){

    }

    // Constructors, getters, setters
    // Add any additional fields or methods as needed
}
