package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.FieldStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(String fieldID, String fieldName, String fieldLocation, String fieldSize, String base67FieldImg01, String base67FieldImg02) throws Exception;
    List<FieldDTO> getAllFields() throws Exception;
    FieldStatus getField(String fieldCode) throws Exception;
    void deleteField(String fieldCode) throws Exception;
    void updateField(String fieldCode, FieldDTO fieldDTO) throws Exception;
}
