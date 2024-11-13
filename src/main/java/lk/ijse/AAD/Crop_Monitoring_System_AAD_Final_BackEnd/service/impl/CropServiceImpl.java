/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/8/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.CropDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.CropStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.CropDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Crop;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.CropNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.CropService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) throws Exception {
        Crop savedCrop = cropDao.save(mapping.toCrop(cropDTO));
        if (savedCrop == null) {
            throw new DataPersistException("Failed to save crop");
        }
    }
    @Override
    public List<CropDTO> getAllCrops() throws Exception {
        List<Crop> allCrops = cropDao.findAll();
        return mapping.asCropDTOList(allCrops);
    }
    @Override
    public CropStatus getCrop(String cropCode) throws Exception {
        if (cropDao.existsById(cropCode)) {
            Crop selectedCrop = cropDao.getReferenceById(cropCode);
            return (CropStatus) mapping.toCropDTO(selectedCrop);
        } else {
            return new SelectedErrorStatus(2, "Crop with code " + cropCode + " not found");
        }
    }
    @Override
    public void deleteCrop(String cropCode) throws Exception {
        Optional<Crop> existedCrop = cropDao.findById(cropCode);
        if (!existedCrop.isPresent()) {
            throw new CropNotFoundException("Crop with code " + cropCode + " not found");
        } else {
            cropDao.deleteById(cropCode);
        }
    }
    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) throws Exception {
        Optional<Crop> existedCrop = cropDao.findById(cropCode);
        if (existedCrop.isPresent()) {
            existedCrop.get().setCropCode(cropDTO.getCropCode());
            existedCrop.get().setCommonName(cropDTO.getCommonName());
            existedCrop.get().setScientificName(cropDTO.getScientificName());
            existedCrop.get().setImage(cropDTO.getImage());
            existedCrop.get().setCategory(cropDTO.getCategory());
            existedCrop.get().setSeason(cropDTO.getSeason());
        }
    }
}
