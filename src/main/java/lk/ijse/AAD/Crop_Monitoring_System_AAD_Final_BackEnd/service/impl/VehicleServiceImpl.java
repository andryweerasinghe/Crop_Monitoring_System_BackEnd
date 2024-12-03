/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/22/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.VehicleDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.VehicleStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.VehicleDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Vehicle;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.VehicleNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.VehicleService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) throws Exception {
        Vehicle savedVehicle = vehicleDao.save(mapping.toVehicle(vehicleDTO));
        if (savedVehicle == null) {
            throw new Exception("Failed to save vehicle");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() throws Exception {
        List<Vehicle> allVehicles = vehicleDao.findAll();
        return mapping.asVehicleDTOList(allVehicles);
    }

    @Override
    public VehicleStatus getVehicle(String vehicleCode) throws Exception {
        if (vehicleDao.existsById(vehicleCode)) {
            Vehicle selectedVehicle = vehicleDao.getReferenceById(vehicleCode);
            return (VehicleStatus) mapping.toVehicleDTO(selectedVehicle);
        } else {
            return new SelectedErrorStatus(2, "Vehicle with code " + vehicleCode + " not found");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) throws Exception {
        Optional<Vehicle> existingVehicle = vehicleDao.findById(vehicleCode);
        if (!existingVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle with code " + vehicleCode + " not found");
        } else {
            vehicleDao.deleteById(vehicleCode);
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) throws Exception {
        Optional<Vehicle> existingVehicle = vehicleDao.findById(vehicleCode);
        if (existingVehicle.isPresent()) {
            existingVehicle.get().setVehicle_code(vehicleDTO.getVehicleCode());
            existingVehicle.get().setPlate_number(vehicleDTO.getPlateNumber());
            existingVehicle.get().setVehicle_category(vehicleDTO.getVehicleCategory());
            existingVehicle.get().setFuel_type(vehicleDTO.getFuelType());
            existingVehicle.get().setStatus(vehicleDTO.getStatus());
            existingVehicle.get().setRemarks(vehicleDTO.getRemarks());
        }
    }
}
