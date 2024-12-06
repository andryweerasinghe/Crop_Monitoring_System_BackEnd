/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/8/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.CropDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.FieldDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.CropStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.CropDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Crop;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Field;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.CropNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.FieldNotFoundException;
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
    @Autowired
    private FieldDao fieldDao;

    public void saveCrop(String cropCode, String commonName, String scientificName, String category, String base67FieldImg, String season, String field_code) {
        CropDTO dto = getCropDto(cropCode,commonName,scientificName,category,season,field_code);

        Crop save = cropDao.save(mapping.toCrop(dto));
        if (save == null) {
            throw new DataPersistException("Failed to save crop");
        }
    }
    @Override
    public List<CropDTO> getAllCrops() throws Exception {
        System.out.println("Get all crops");
        List<Crop> allCrops = cropDao.findAll();
        return mapping.asCropDTOList(allCrops);
    }
    @Override
    public CropStatus getCrop(String cropCode) throws Exception {
        if (cropDao.existsById(cropCode)) {
            Crop crop = cropDao.getReferenceById(cropCode);
            return (CropStatus) mapping.toCropDTO(crop);
        } else {
            throw new CropNotFoundException("Crop not found");
        }
    }
    @Override
    public void deleteCrop(String cropCode) throws Exception {
        Optional<Crop> existedCrop = cropDao.findById(cropCode);
        if (existedCrop.isEmpty()) {
            throw new CropNotFoundException("Crop with code " + cropCode + " not found");
        } else {
            cropDao.deleteById(cropCode);
        }
    }
    @Override
    public void updateCrop(String cropCode, String commonName, String scientificName, String category, String season, String fieldCode) {
        CropDTO dto = getCropDto(cropCode, commonName, scientificName, category, season, fieldCode);
        Optional<Crop> crop = cropDao.findById(cropCode);
        if (crop.isPresent()) {
            crop.get().setCommonName(dto.getCommonName());
            crop.get().setScientificName(dto.getScientificName());
            crop.get().setCategory(dto.getCategory());
//            crop.get().setImage(dto.getImage());
            crop.get().setSeason(dto.getSeason());

            Optional<Field> field = fieldDao.findById(fieldCode);
            if (field.isPresent()) {
                crop.get().setField(field.get());
            } else {
                throw new FieldNotFoundException("Field not found");
            }
            crop.get().setCropCode(cropCode);
        }
    }
    private CropDTO getCropDto(String cropCode, String commonName, String scientificName, String category, String season, String field_code) {
        CropDTO cropDto = new CropDTO();
        cropDto.setCropCode(cropCode);
        cropDto.setCommonName(commonName);
        cropDto.setScientificName(scientificName);
        cropDto.setCategory(category);
//        cropDto.setImage(base67FieldImg);
        cropDto.setSeason(season);
        cropDto.setFieldCode(field_code);
        return cropDto;
    }
}
