/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/22/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.controller;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.VehicleStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.VehicleDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.VehicleNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.VehicleService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.AppUtil;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicle(
            @RequestPart("plateNumber") String plateNumber,
            @RequestPart("vehicleCategory") String vehicleCategory,
            @RequestPart("fuelType") String fuelType,
            @RequestPart("status") String status,
            @RequestPart("remarks") String remarks
    ){
        String vehicleCode = AppUtil.generateVehicleCode();

        VehicleDTO buildVehicleDTO = new VehicleDTO();
        buildVehicleDTO.setVehicleCode(vehicleCode);
        buildVehicleDTO.setVehicleCategory(vehicleCategory);
        buildVehicleDTO.setFuelType(fuelType);
        buildVehicleDTO.setStatus(status);
        buildVehicleDTO.setRemarks(remarks);
        buildVehicleDTO.setPlateNumber(plateNumber);
        try {
            vehicleService.saveVehicle(buildVehicleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{vehicleCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleStatus getSelectedVehicle(@PathVariable("vehicleCode") String vehicleCode) throws Exception {
        if (!RegexProcess.vehicleCodeMatcher(vehicleCode)) {
            return new SelectedErrorStatus(1, "Vehicle code is not valid");
        }
        return vehicleService.getVehicle(vehicleCode);
    }
    @DeleteMapping(value = "/{vehicleCode}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicleCode") String vehicleCode) {
        if (!RegexProcess.vehicleCodeMatcher(vehicleCode)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles() throws Exception {
        return vehicleService.getAllVehicles();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{vehicleCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateVehicle(
            @RequestPart("plateNumber") String plateNumber,
            @RequestPart("vehicleCategory") String vehicleCategory,
            @RequestPart("fuelType") String fuelType,
            @RequestPart("status") String status,
            @RequestPart("remarks") String remarks,
            @PathVariable("vehicleCode") String vehicleCode
    ) throws Exception {
        VehicleDTO buildVehicleDTO = new VehicleDTO();
        buildVehicleDTO.setVehicleCategory(vehicleCategory);
        buildVehicleDTO.setFuelType(fuelType);
        buildVehicleDTO.setStatus(status);
        buildVehicleDTO.setRemarks(remarks);
        buildVehicleDTO.setPlateNumber(plateNumber);
        vehicleService.updateVehicle(vehicleCode, buildVehicleDTO);
    }
}
