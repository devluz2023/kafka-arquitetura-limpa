package br.com.faluz.domain.entity;

import br.com.faluz.app.dto.DeviceRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "device")
@Entity(name = "device")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Device {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String device;
    private boolean releaseForUse;
    private String clientName;
    private LocalDate releaseDate;

    public Device(DeviceRequestDTO data){
        this.id = data.id();
        this.device = data.device();
        this.releaseForUse = data.releaseForUse();
        this.clientName = data.clientName();
        this.releaseDate = data.releaseDate();
    }
}
