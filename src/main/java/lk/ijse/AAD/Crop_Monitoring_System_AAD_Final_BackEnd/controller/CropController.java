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

@CrossOrigin(origins = "http://localhost:63342/")
@RestController
@RequestMapping("api/v1/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
        @RequestPart ("cropCode") String cropCode,
        @RequestPart ("commonName") String commonName,
        @RequestPart ("scientificName") String scientificName,
        @RequestPart ("image") MultipartFile image,
        @RequestPart ("category") String category,
        @RequestPart ("season") String season,
        @RequestPart ("fieldCode") String fieldCode
    ) {
        String base67FieldImg = "";

        try {
            byte[] image01Bytes = image.getBytes();
            base67FieldImg = AppUtil.imageToBase64(image01Bytes);

            cropService.saveCrop(cropCode,commonName,scientificName,category,base67FieldImg,season,fieldCode);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping(value = "/{cropCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CropStatus getSelectedCrop(@PathVariable ("cropCode") String cropCode) throws Exception {
        return cropService.getCrop(cropCode);
    }
    @DeleteMapping(value = "/{cropCode}")
    public ResponseEntity<Void> deleteCrop(@PathVariable ("cropCode") String cropCode) {
        try {
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
//    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PutMapping(value = "/{cropCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCrops(@RequestPart("commonName") String common_name, @RequestPart("scientificName") String scientific_name, @RequestPart("category") String category,
                                            @RequestPart("season") String season,@RequestPart("fieldCode") String field_code,@PathVariable("cropCode") String crop_code) {
        String base67FieldImg = "";

        //            byte[] image01Bytes = img.getBytes();
//            base67FieldImg = AppUtil.imageToBase64(image01Bytes);

        cropService.updateCrop(crop_code,common_name,scientific_name,category,season,field_code);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
