/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/22/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.CropDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.MonitoringLogDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.MonitoringLogStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.MonitoringLogDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.MonitoringLog;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.MonitoringLogNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.MonitoringLogService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {
    @Autowired
    private MonitoringLogDao monitoringLogDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    private CropDao cropDao;

    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) throws Exception {
        MonitoringLog savedMonitoringLog = monitoringLogDao.save(mapping.toMonitoringLog(monitoringLogDTO));
        if (savedMonitoringLog == null) {
            throw new DataPersistException("Failed to save monitoring log");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitoringLogs() throws Exception {
        List<MonitoringLog> allMonitoringLogs = monitoringLogDao.findAll();
        return mapping.asMonitoringLogDTOList(allMonitoringLogs);
    }

    @Override
    public MonitoringLogStatus getMonitoringLog(String logCode) throws Exception {
        if (monitoringLogDao.existsById(logCode)) {
            MonitoringLog existingMonitoringLog = monitoringLogDao.getReferenceById(logCode);
            return (MonitoringLogStatus) mapping.toMonitoringLogDTO(existingMonitoringLog);
        } else {
            return new SelectedErrorStatus(2, "Monitoring Log with log code " + logCode + " not found");
        }
    }

    @Override
    public void deleteMonitoringLog(String logCode) throws Exception {
        Optional<MonitoringLog> existingMonitoringLog = monitoringLogDao.findById(logCode);
        if (!existingMonitoringLog.isPresent()) {
            throw new MonitoringLogNotFoundException("Monitoring Log with log code " + logCode + " not found");
        } else {
            monitoringLogDao.deleteById(logCode);
        }
    }

    @Override
    public void updateMonitoringLog(String logCode, MonitoringLogDTO monitoringLogDTO) throws Exception {
        Optional<MonitoringLog> existingMonitoringLog = monitoringLogDao.findById(logCode);
        if (existingMonitoringLog.isPresent()) {
            existingMonitoringLog.get().setLog_code(monitoringLogDTO.getLogCode());
            existingMonitoringLog.get().setLog_date(monitoringLogDTO.getLogDate());
            existingMonitoringLog.get().setImage(monitoringLogDTO.getImage());
        }
    }
}
