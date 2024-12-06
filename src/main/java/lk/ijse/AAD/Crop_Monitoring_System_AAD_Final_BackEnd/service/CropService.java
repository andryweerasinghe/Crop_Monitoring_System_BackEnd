package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.CropStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(String cropCode, String commonName, String scientificName, String category, String base67FieldImg, String season, String field_code) throws Exception;
    List<CropDTO> getAllCrops() throws Exception;
    CropStatus getCrop(String cropCode) throws Exception;
    void deleteCrop(String cropCode) throws Exception;
    void updateCrop(String cropCode, String commonName, String scientificName, String category, String season, String fieldCode);
}
