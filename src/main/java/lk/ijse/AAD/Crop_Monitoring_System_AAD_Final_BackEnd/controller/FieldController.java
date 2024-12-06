/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/15/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.controller;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.FieldStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.FieldDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.FieldNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.FieldService;
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

@CrossOrigin(origins = "http://localhost:63342/")
@RestController
@RequestMapping("api/v1/fields")
public class FieldController {
    @Autowired
    private FieldService fieldService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestPart ("fieldCode") String fieldCode,
            @RequestPart ("fieldName") String fieldName,
            @RequestPart ("size") String size,
            @RequestPart ("fieldLocation") String fieldLocation,
            @RequestPart ("image1") MultipartFile image1,
            @RequestPart ("image2") MultipartFile image2
            ){

        try {
            byte[] image1Bytes = image1.getBytes();
            byte[] image2Bytes = image2.getBytes();

            String base64Image1 = AppUtil.imageToBase64(image1Bytes);
            String base64Image2 = AppUtil.imageToBase64(image2Bytes);

            fieldService.saveField(fieldCode, fieldName, fieldLocation, size, base64Image1, base64Image2);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldDTO getSelectedField(@PathVariable("fieldCode") String fieldCode) throws Exception {
        return (FieldDTO) fieldService.getField(fieldCode);
    }
    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable ("fieldCode") String fieldCode) {
        if (!RegexProcess.fieldCodeMatcher(fieldCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDTO> getAllFields() throws Exception {
        return fieldService.getAllFields();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{fieldCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateField(
            @RequestPart("fieldName") String fieldName,
            @RequestPart("size") String size,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("image1") MultipartFile image1,
            @RequestPart("image2") MultipartFile image2,
            @PathVariable("fieldCode") String fieldCode
    ) throws Exception {
        String base64Image1="";
        String base64Image2="";
        try {
            byte[] image1Bytes = image1.getBytes();
            byte[] image2Bytes = image2.getBytes();
            base64Image1 = AppUtil.imageToBase64(image1Bytes);
            base64Image2 = AppUtil.imageToBase64(image2Bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FieldDTO buildFieldDTO = new FieldDTO();
        buildFieldDTO.setFieldName(fieldName);
        buildFieldDTO.setSize(size);
        buildFieldDTO.setFieldLocation(fieldLocation);
        buildFieldDTO.setImage1(base64Image1);
        buildFieldDTO.setImage2(base64Image2);
        fieldService.updateField(fieldCode, buildFieldDTO);
    }
}
