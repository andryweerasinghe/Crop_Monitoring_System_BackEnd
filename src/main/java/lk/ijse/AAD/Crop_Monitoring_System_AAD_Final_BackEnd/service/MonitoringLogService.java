package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.MonitoringLogStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) throws Exception;
    List<MonitoringLogDTO> getAllMonitoringLogs() throws Exception;
    MonitoringLogStatus getMonitoringLog(String logCode) throws Exception;
    void deleteMonitoringLog(String logCode) throws Exception;
    void updateMonitoringLog(String logCode, MonitoringLogDTO monitoringLogDTO) throws Exception;
}
