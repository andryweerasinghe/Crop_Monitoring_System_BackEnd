/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/8/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.controller;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.CropStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.CropDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.CropNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.CropService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.AppUtil;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
        @RequestPart ("commonName") String commonName,
        @RequestPart ("scientificName") String scientificName,
        @RequestPart ("image") MultipartFile image,
        @RequestPart ("category") String category,
        @RequestPart ("season") String season,
        @RequestPart ("fieldCode") String fieldCode
    ) {
        String base64Image = "";
        try {
            byte[] imageBytes = image.getBytes();
            base64Image = AppUtil.imageToBase64(imageBytes);

            String cropCode = AppUtil.generateCropCode();

            CropDTO buildCropDTO = new CropDTO();
            buildCropDTO.setCropCode(cropCode);
            buildCropDTO.setCommonName(commonName);
            buildCropDTO.setScientificName(scientificName);
            buildCropDTO.setImage(base64Image);
            buildCropDTO.setCategory(category);
            buildCropDTO.setSeason(season);
            buildCropDTO.setFieldCode(fieldCode);
            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{cropCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable ("cropCode") String cropCode) throws Exception {
        if (!RegexProcess.cropCodeMatcher(cropCode)) {
            return new SelectedErrorStatus(1, "Crop Code Not Found");
        }
        return cropService.getCrop(cropCode);
    }
    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropCode") String cropCode) {
        try {
            if (!RegexProcess.cropCodeMatcher(cropCode)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDTO> getAllCrops() throws Exception {
        return cropService.getAllCrops();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{cropCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateCrop(
            @RequestPart ("commonName") String commonName,
            @RequestPart ("scientificName") String scientificName,
            @RequestPart ("image") MultipartFile image,
            @RequestPart ("category") String category,
            @RequestPart ("season") String season,
            @RequestPart ("fieldCode") String fieldCode,
            @PathVariable ("cropCode") String cropCode
    ) throws Exception {
        String base64Image = "";
        try {
            byte[] imageBytes = image.getBytes();
            base64Image = AppUtil.imageToBase64(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CropDTO buildCropDTO = new CropDTO();
        buildCropDTO.setCropCode(cropCode);
        buildCropDTO.setCommonName(commonName);
        buildCropDTO.setScientificName(scientificName);
        buildCropDTO.setImage(base64Image);
        buildCropDTO.setCategory(category);
        buildCropDTO.setSeason(season);
        buildCropDTO.setFieldCode(fieldCode);
        cropService.updateCrop(cropCode, buildCropDTO);
    }

}
