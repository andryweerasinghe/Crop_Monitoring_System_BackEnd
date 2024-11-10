/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/8/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.controller;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.CropService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        @RequestPart ("season") String season
    ) {
        String base64Image = "";
        try {
            byte[] imageBytes = image.getBytes();
            base64Image = AppUtil.imageToBase64(imageBytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
