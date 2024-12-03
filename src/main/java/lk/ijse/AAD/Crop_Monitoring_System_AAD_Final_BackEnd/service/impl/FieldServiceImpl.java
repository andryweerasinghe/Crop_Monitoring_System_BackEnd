/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/15/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.FieldDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.FieldStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.FieldDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Field;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.FieldNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.FieldService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void saveField(FieldDTO fieldDTO) throws Exception {
        Field savedField = fieldDao.save(mapping.toField(fieldDTO));
        if (savedField == null) {
            throw new DataPersistException("Failed to save field");
        }
    }

    @Override
    public List<FieldDTO> getAllFields() throws Exception {
        List<Field> allFields = fieldDao.findAll();
        return mapping.asFieldDTOList(allFields);
    }

    @Override
    public FieldStatus getField(String fieldCode) throws Exception {
        if (fieldDao.existsById(fieldCode)) {
            Field selectedField = fieldDao.getReferenceById(fieldCode);
            return (FieldStatus) mapping.toFieldDTO(selectedField);
        } else {
            return new SelectedErrorStatus(2, "Field with id " + fieldCode + " not found");
        }
    }

    @Override
    public void deleteField(String fieldCode) throws Exception {
        Optional<Field> existingField = fieldDao.findById(fieldCode);
        if (!existingField.isPresent()) {
            throw new FieldNotFoundException("Field with id " + fieldCode + " not found");
        } else {
            fieldDao.deleteById(fieldCode);
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) throws Exception {
        Optional<Field> existingField = fieldDao.findById(fieldCode);
        if (existingField.isPresent()) {
            existingField.get().setField_name(fieldDTO.getFieldName());
            existingField.get().setSize(fieldDTO.getSize());
            existingField.get().setField_location(fieldDTO.getFieldLocation());
            existingField.get().setImage1(fieldDTO.getImage1());
            existingField.get().setImage2(fieldDTO.getImage2());
        }
    }
}
