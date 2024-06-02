package br.com.faluz.infra.db.repository;

import br.com.faluz.infra.db.model.DeviceTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceTable, String> {
}