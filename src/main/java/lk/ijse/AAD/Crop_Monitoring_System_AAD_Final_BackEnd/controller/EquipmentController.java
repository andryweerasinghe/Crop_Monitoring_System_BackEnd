/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/14/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.controller;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.EquipmentStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.EquipmentDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Equipment;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.EquipmentNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.EquipmentService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.AppUtil;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342/")
@RestController
@RequestMapping("api/v1/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(
            @RequestPart ("equipmentName") String equipmentName,
            @RequestPart ("equipmentType") String equipmentType,
            @RequestPart ("status") String status,
            @RequestPart ("id") String staffId,
            @RequestPart ("fieldCode") String fieldCode
    ) {
        String equipmentId = AppUtil.generateEquipmentId();

        EquipmentDTO buildEquipmentDTO = new EquipmentDTO();
        buildEquipmentDTO.setEquipmentId(equipmentId);
        buildEquipmentDTO.setEquipmentName(equipmentName);
        buildEquipmentDTO.setEquipmentType(equipmentType);
        buildEquipmentDTO.setStatus(status);
        buildEquipmentDTO.setId(staffId);
        buildEquipmentDTO.setFieldCode(fieldCode);
        try {
            equipmentService.saveEquipment(buildEquipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{equipmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentStatus getSelectedEquipment(@PathVariable ("equipmentId") String equipmentId) throws Exception {
        if (!RegexProcess.equipmentIdMatcher(equipmentId)) {
            return new SelectedErrorStatus(1, "Equipment Id not found");
        }
        return equipmentService.getEquipment(equipmentId);
    }
    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable ("equipmentId") String equipmentId) {
        try {
            if (!RegexProcess.equipmentIdMatcher(equipmentId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipments() throws Exception {
        return equipmentService.getAllEquipment();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{equipmentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateEquipment(
            @RequestPart ("equipmentName") String equipmentName,
            @RequestPart ("equipmentType") String equipmentType,
            @RequestPart ("status") String status,
            @RequestPart ("fieldCode") String fieldCode,
            @RequestPart ("id") String staffId,
            @PathVariable ("equipmentId") String equipmentId
    ) throws Exception {
        EquipmentDTO buildEquipmentDTO = new EquipmentDTO();
        buildEquipmentDTO.setEquipmentId(equipmentId);
        buildEquipmentDTO.setEquipmentName(equipmentName);
        buildEquipmentDTO.setEquipmentType(equipmentType);
        buildEquipmentDTO.setStatus(status);
        buildEquipmentDTO.setFieldCode(fieldCode);
        buildEquipmentDTO.setId(staffId);
        equipmentService.updateEquipment(equipmentId, buildEquipmentDTO);
    }
}
