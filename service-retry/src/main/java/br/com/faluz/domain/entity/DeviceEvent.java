package br.com.faluz.domain.entity;



import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class Device {
    @Id
    private String device;
    private boolean releaseForUse;
    private String clientName;
    private LocalDate releaseDate;

    // Getters and setters
}
