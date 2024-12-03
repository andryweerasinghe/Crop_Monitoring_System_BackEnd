/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/8/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.*;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public Crop toCrop(CropDTO cropDTO) {
        return modelMapper.map(cropDTO, Crop.class);
    }
    public CropDTO toCropDTO(Crop crop) {return modelMapper.map(crop, CropDTO.class);}
    public List<CropDTO> asCropDTOList(List<Crop> crops) {
        return modelMapper.map(crops, new TypeToken<List<CropDTO>>() {}.getType());
    }

    public Staff toStaff(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, Staff.class);
    }
    public StaffDTO toStaffDTO(Staff staff) {return modelMapper.map(staff, StaffDTO.class);}
    public List<StaffDTO> asStaffDTOList(List<Staff> staffList) {
        return modelMapper.map(staffList, new TypeToken<List<StaffDTO>>() {}.getType());
    }

    public Equipment toEquipment(EquipmentDTO equipmentDTO) {
        return modelMapper.map(equipmentDTO, Equipment.class);
    }
    public EquipmentDTO toEquipmentDTO(Equipment equipment) {return modelMapper.map(equipment, EquipmentDTO.class);}
    public List<EquipmentDTO> asEquipmentDTOList(List<Equipment> equipmentList) {
        return modelMapper.map(equipmentList, new TypeToken<List<EquipmentDTO>>() {}.getType());
    }

    public Field toField(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, Field.class);
    }
    public FieldDTO toFieldDTO(Field field) {return modelMapper.map(field, FieldDTO.class);}
    public List<FieldDTO> asFieldDTOList(List<Field> fieldList) {
        return modelMapper.map(fieldList, new TypeToken<List<FieldDTO>>() {}.getType());
    }

    public Vehicle toVehicle(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }
    public VehicleDTO toVehicleDTO(Vehicle vehicle) {return modelMapper.map(vehicle, VehicleDTO.class);}
    public List<VehicleDTO> asVehicleDTOList(List<Vehicle> vehicleList) {
        return modelMapper.map(vehicleList, new TypeToken<List<VehicleDTO>>() {}.getType());
    }

    public MonitoringLog toMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
        return modelMapper.map(monitoringLogDTO, MonitoringLog.class);
    }
    public MonitoringLogDTO toMonitoringLogDTO(MonitoringLog monitoringLog) {return modelMapper.map(monitoringLog, MonitoringLogDTO.class);}
    public List<MonitoringLogDTO> asMonitoringLogDTOList(List<MonitoringLog> monitoringLogList) {
        return modelMapper.map(monitoringLogList, new TypeToken<List<MonitoringLogDTO>>() {}.getType());
    }
}
