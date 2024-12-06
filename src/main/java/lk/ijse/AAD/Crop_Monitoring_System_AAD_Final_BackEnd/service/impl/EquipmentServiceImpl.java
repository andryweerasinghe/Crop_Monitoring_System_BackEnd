/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/14/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.EquipmentDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.EquipmentStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.EquipmentDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Equipment;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.EquipmentNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.EquipmentService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) throws Exception {
        Equipment savedEquipment = equipmentDao.save(mapping.toEquipment(equipmentDTO));
        if (savedEquipment == null) {
            throw new DataPersistException("Failed to save equipment");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() throws Exception {
        List<Equipment> allEquipment = equipmentDao.findAll();
        return mapping.asEquipmentDTOList(allEquipment);
    }

    @Override
    public EquipmentStatus getEquipment(String equipmentId) throws Exception {
        if (equipmentDao.existsById(equipmentId)) {
            Equipment selectedEquipment = equipmentDao.getReferenceById(equipmentId);
            return (EquipmentStatus) mapping.toEquipmentDTO(selectedEquipment);
        } else {
            return new SelectedErrorStatus(2, "Equipment with id " + equipmentId + " is not found");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) throws Exception {
        Optional<Equipment> existingEquipmentId = equipmentDao.findById(equipmentId);
        if (!existingEquipmentId.isPresent()) {
            throw new EquipmentNotFoundException("Equipment with id " + equipmentId + " is not found");
        } else {
            equipmentDao.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) throws Exception {
        Optional<Equipment> existingEquipment = equipmentDao.findById(equipmentId);
        if (existingEquipment.isPresent()) {
            existingEquipment.get().setEquipmentId(equipmentDTO.getEquipmentId());
            existingEquipment.get().setEquipmentName(equipmentDTO.getEquipmentName());
            existingEquipment.get().setEquipmentType(equipmentDTO.getEquipmentType());
            existingEquipment.get().setStatus(equipmentDTO.getStatus());
        }
    }
}
