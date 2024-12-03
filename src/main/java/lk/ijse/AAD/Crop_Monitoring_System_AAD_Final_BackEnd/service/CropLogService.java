package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.CropLogStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.CropLogDTO;

import java.util.List;

public interface CropLogService {
    void saveCropLog(CropLogDTO cropLogDTO) throws Exception;
    List<CropLogDTO> getAllCropLogs() throws Exception;
    CropLogStatus getCropLog(String logCropId) throws Exception;
    void deleteCropLog(String logCropId) throws Exception;
    void updateCropLog(String logCropId, CropLogDTO cropLogDTO) throws Exception;
}
