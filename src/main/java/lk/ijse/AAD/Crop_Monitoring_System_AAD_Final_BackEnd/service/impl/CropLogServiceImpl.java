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
        monitoringLog.setLogCode(cropLogDTO.getLogCode());
        monitoringLog.setLogDate(cropLogDTO.getLogDate());
        monitoringLog.setImage(cropLogDTO.getImage());

        // Save MonitoringLog
        MonitoringLog savedLog = monitoringLogDao.save(monitoringLog);

        // Create and populate LogCrop
        LogCrop logCrop = new LogCrop();
        logCrop.setLogCropId(cropLogDTO.getLogCropId());
        logCrop.setCropCondition(cropLogDTO.getCropCondition());
        logCrop.setComments(cropLogDTO.getComments());
        logCrop.setMonitoringLog(savedLog); // Establish the relationship

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
            dto.setLogCropId(logCrop.getLogCropId());
            dto.setCropCondition(logCrop.getCropCondition());
            dto.setComments(logCrop.getComments());

            // Map related MonitoringLog fields
            MonitoringLog monitoringLog = logCrop.getMonitoringLog();
            if (monitoringLog != null) {
                dto.setLogCode(monitoringLog.getLogCode());
                dto.setLogDate(monitoringLog.getLogDate());
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
        dto.setLogCropId(selectedCropLog.getLogCropId());
        dto.setCropCondition(selectedCropLog.getCropCondition());
        dto.setComments(selectedCropLog.getComments());

        MonitoringLog monitoringLog = selectedCropLog.getMonitoringLog();
        if (monitoringLog != null) {
            dto.setLogCode(monitoringLog.getLogCode());
            dto.setLogDate(monitoringLog.getLogDate());
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
        MonitoringLog monitoringLog = cropLog.getMonitoringLog();

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
            existingCropLog.get().setLogCropId(cropLogDTO.getLogCropId());
            existingCropLog.get().setCropCondition(cropLogDTO.getCropCondition());
            existingCropLog.get().setComments(cropLogDTO.getComments());
            MonitoringLog monitoringLog = existingCropLog.get().getMonitoringLog();

        }
    }
}
