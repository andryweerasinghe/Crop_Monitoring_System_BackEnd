package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.EquipmentStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO) throws Exception;
    List<EquipmentDTO> getAllEquipment() throws Exception;
    EquipmentStatus getEquipment(String equipmentId) throws Exception;
    void deleteEquipment(String equipmentId) throws Exception;
    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) throws Exception;
}
