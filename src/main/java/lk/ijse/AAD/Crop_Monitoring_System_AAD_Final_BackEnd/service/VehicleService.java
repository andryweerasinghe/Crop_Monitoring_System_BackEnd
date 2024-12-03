package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.VehicleStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO) throws Exception;
    List<VehicleDTO> getAllVehicles() throws Exception;
    VehicleStatus getVehicle(String vehicleCode) throws Exception;
    void deleteVehicle(String vehicleCode) throws Exception;
    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) throws Exception;
}
