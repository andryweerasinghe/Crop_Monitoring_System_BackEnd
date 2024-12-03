package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.MonitoringLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringLogDao extends JpaRepository<MonitoringLog,String> {
}
