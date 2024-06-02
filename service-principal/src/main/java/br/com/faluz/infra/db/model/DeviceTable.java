package br.com.faluz.infra.db.model;


import br.com.faluz.app.dto.DevicetDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name="devices")
@Table(name="devices")
public class DeviceTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String device;

    private String clientName;

    @Column(name = "releasedate")
    private LocalDate releaseDate;

    @Column(name="releaseforuse")
    private Boolean releaseForUse ;


    public DeviceTable(DevicetDTO data){
        this.id= data.id();
        this.device= data.device();
        this.releaseDate = data.releaseDate();
        this.releaseForUse= data.releaseForUse();
    }

    public DeviceTable() {

    }
}