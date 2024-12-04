/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 12/3/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.CropLogDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.MonitoringLogDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.CropLogStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.CropLogDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.LogCrop;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.MonitoringLog;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.CropLogNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.CropLogService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        Optional<LogCrop> optionalLogCrop = cropLogDao.findById(logCropId);

        if (optionalLogCrop.isEmpty()) {
            throw new EntityNotFoundException("Crop Log with ID " + logCropId + " not found");
        }

        LogCrop selectedCropLog = optionalLogCrop.get();

        CropLogDTO dto = new CropLogDTO();
        dto.setLogCropId(selectedCropLog.getLog_crop_id());
        dto.setCropCondition(selectedCropLog.getCrop_condition());
        dto.setComments(selectedCropLog.getComments());

        MonitoringLog monitoringLog = selectedCropLog.getMonitoring_log();
        if (monitoringLog != null) {
            dto.setLogCode(monitoringLog.getLog_code());
            dto.setLogDate(monitoringLog.getLog_date());
            dto.setImage(monitoringLog.getImage());
        }

        return (CropLogStatus) dto; // Returning as CropStatus
    }

    @Override
    public void deleteCropLog(String logCropId) throws Exception {
        Optional<LogCrop> optionalCropLog = cropLogDao.findById(logCropId);

        // If not present, throw an exception
        if (optionalCropLog.isEmpty()) {
            throw new CropLogNotFoundException("Crop Log with ID " + logCropId + " not found");
        }

        // Retrieve the CropLog entity
        LogCrop cropLog = optionalCropLog.get();

        // Retrieve the associated MonitoringLog (if any)
        MonitoringLog monitoringLog = cropLog.getMonitoring_log();

        // Delete the CropLog
        cropLogDao.delete(cropLog);

        // Delete the MonitoringLog if it exists
        if (monitoringLog != null) {
            monitoringLogDao.delete(monitoringLog);
        }
    }

    @Override
    public void updateCropLog(String logCropId, CropLogDTO cropLogDTO) throws Exception {
        Optional<LogCrop> existingCropLog = cropLogDao.findById(logCropId);
        if (existingCropLog.isPresent()) {
            existingCropLog.get().setLog_crop_id(cropLogDTO.getLogCropId());
            existingCropLog.get().setCrop_condition(cropLogDTO.getCropCondition());
            existingCropLog.get().setComments(cropLogDTO.getComments());
            MonitoringLog monitoringLog = existingCropLog.get().getMonitoring_log();

        }
    }
}
