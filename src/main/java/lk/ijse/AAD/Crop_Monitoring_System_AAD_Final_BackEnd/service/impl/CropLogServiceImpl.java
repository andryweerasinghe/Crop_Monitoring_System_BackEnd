/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 12/3/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.CropLogDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.MonitoringLogDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.CropLogStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.CropLogDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.LogCrop;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.MonitoringLog;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.CropLogService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CropLogServiceImpl implements CropLogService {
    @Autowired
    private CropLogDao cropLogDao;
    @Autowired
    private Mapping mapping;
    @Autowired
    private MonitoringLogDao monitoringLogDao;

    @Override
    public void saveCropLog(CropLogDTO cropLogDTO) throws Exception {
        // Create and populate MonitoringLog
        MonitoringLog monitoringLog = new MonitoringLog();
        monitoringLog.setLog_code(cropLogDTO.getLogCode());
        monitoringLog.setLog_date(cropLogDTO.getLogDate());
        monitoringLog.setImage(cropLogDTO.getImage());

        // Save MonitoringLog
        MonitoringLog savedLog = monitoringLogDao.save(monitoringLog);

        // Create and populate LogCrop
        LogCrop logCrop = new LogCrop();
        logCrop.setLog_crop_id(cropLogDTO.getLogCropId());
        logCrop.setCrop_condition(cropLogDTO.getCropCondition());
        logCrop.setComments(cropLogDTO.getComments());
        logCrop.setMonitoring_log(savedLog); // Establish the relationship

        // Save LogCrop
        cropLogDao.save(logCrop);
    }

    @Override
    public List<CropLogDTO> getAllCropLogs() throws Exception {
        // Fetch all LogCrop entities from the database
        List<LogCrop> allCropLogs = cropLogDao.findAll();

        // Map each LogCrop entity to a CropLogDTO
        return allCropLogs.stream().map(logCrop -> {
            CropLogDTO dto = new CropLogDTO();
            dto.setLogCropId(logCrop.getLog_crop_id());
            dto.setCropCondition(logCrop.getCrop_condition());
            dto.setComments(logCrop.getComments());

            // Map related MonitoringLog fields
            MonitoringLog monitoringLog = logCrop.getMonitoring_log();
            if (monitoringLog != null) {
                dto.setLogCode(monitoringLog.getLog_code());
                dto.setLogDate(monitoringLog.getLog_date());
                dto.setImage(monitoringLog.getImage());
            }
            return dto;
        }).toList();
    }

    @Override
    public CropLogStatus getCropLog(String logCropId) throws Exception {
        LogCrop selectedCropLog = cropLogDao.getReferenceById(logCropId);
    }

    @Override
    public void deleteCropLog(String logCropId) throws Exception {

    }

    @Override
    public void updateCropLog(String logCropId, CropLogDTO cropLogDTO) throws Exception {

    }
}