/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/22/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.controller;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.MonitoringLogStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.MonitoringLogDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.FieldNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.MonitoringLogNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.MonitoringLogService;
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
@RequestMapping("api/v1/monitoringLogs")
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(
            @RequestPart ("logDate") String logDate,
            @RequestPart ("image") MultipartFile image
    ){
        String logCode = AppUtil.generateLogCode();

        try {
            byte[] imageBytes = image.getBytes();

            String base64Image = AppUtil.imageToBase64(imageBytes);

            MonitoringLogDTO buildLogDTO = new MonitoringLogDTO();
            buildLogDTO.setLogDate(logDate);
            buildLogDTO.setLogCode(logCode);
            buildLogDTO.setImage(base64Image);
            monitoringLogService.saveMonitoringLog(buildLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{logCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogStatus getMonitoringLog(@PathVariable ("logCode") String logCode) throws Exception {
        if (!RegexProcess.logCodeMatcher(logCode)) {
            return new SelectedErrorStatus(1, "LogCode is not valid");
        }
        return monitoringLogService.getMonitoringLog(logCode);
    }
    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteMonitoringLog(@PathVariable ("logCode") String logCode) {
        if (!RegexProcess.logCodeMatcher(logCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            monitoringLogService.deleteMonitoringLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MonitoringLogNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllMonitoringLogs() throws Exception {
        return monitoringLogService.getAllMonitoringLogs();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{logCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMonitoringLog(
            @RequestPart ("logDate") String logDate,
            @RequestPart ("image") MultipartFile image,
            @PathVariable ("logCode") String logCode
    )throws Exception {
        String base64Image="";
        try {
            byte[] imageBytes = image.getBytes();
            base64Image = AppUtil.imageToBase64(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MonitoringLogDTO buildLogDTO = new MonitoringLogDTO();
        buildLogDTO.setLogDate(logDate);
        buildLogDTO.setLogCode(logCode);
        buildLogDTO.setImage(base64Image);
        monitoringLogService.updateMonitoringLog(logCode, buildLogDTO);
    }
}
