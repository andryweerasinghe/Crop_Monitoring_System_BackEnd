/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/13/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.controller;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.StaffStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.StaffDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.MemberNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.StaffService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.AppUtil;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342/")
@RestController
@RequestMapping("api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMember(
        @RequestPart ("firstName") String firstName,
        @RequestPart ("secondName") String secondName,
        @RequestPart ("designation") String designation,
        @RequestPart ("gender") String gender,
        @RequestPart ("joinedDate") Date joinedDate,
        @RequestPart ("dob") Date dob,
        @RequestPart ("addressLine1") String addressLine1,
        @RequestPart ("addressLine2") String addressLine2,
        @RequestPart ("addressLine3") String addressLine3,
        @RequestPart ("addressLine4") String addressLine4,
        @RequestPart ("addressLine5") String addressLine5,
        @RequestPart ("phoneNumber") String phoneNumber,
        @RequestPart ("email") String email
    ) {
        String memberId = AppUtil.generateStaffMemberId();

        StaffDTO buildStaffDTO = new StaffDTO();
        buildStaffDTO.setId(memberId);
        buildStaffDTO.setFirstName(firstName);
        buildStaffDTO.setSecondName(secondName);
        buildStaffDTO.setDesignation(designation);
        buildStaffDTO.setGender(gender);
        buildStaffDTO.setJoinedDate(joinedDate);
        buildStaffDTO.setDob(dob);
        buildStaffDTO.setAddressLine1(addressLine1);
        buildStaffDTO.setAddressLine2(addressLine2);
        buildStaffDTO.setAddressLine3(addressLine3);
        buildStaffDTO.setAddressLine4(addressLine4);
        buildStaffDTO.setAddressLine5(addressLine5);
        buildStaffDTO.setPhoneNumber(phoneNumber);
        buildStaffDTO.setEmail(email);
        try {
            staffService.saveMember(buildStaffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffStatus getSelectedMember(@PathVariable ("id") String id) throws Exception {
        if (!RegexProcess.staffIdMatcher(id)) {
            return new SelectedErrorStatus(1, "Member Not Found");
        }
        return staffService.getMember(id);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable ("id") String id) {
        try {
            if (!RegexProcess.staffIdMatcher(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            staffService.deleteMember(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllMembers() throws Exception {
        return staffService.getAllStaffMembers();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateMember(
            @RequestPart ("firstName") String firstName,
            @RequestPart ("secondName") String secondName,
            @RequestPart ("designation") String designation,
            @RequestPart ("gender") String gender,
            @RequestPart ("joinedDate") Date joinedDate,
            @RequestPart ("dob") Date dob,
            @RequestPart ("addressLine1") String addressLine1,
            @RequestPart ("addressLine2") String addressLine2,
            @RequestPart ("addressLine3") String addressLine3,
            @RequestPart ("addressLine4") String addressLine4,
            @RequestPart ("addressLine5") String addressLine5,
            @RequestPart ("phoneNumber") String phoneNumber,
            @RequestPart ("email") String email,
            @PathVariable ("id") String id
    ) throws Exception {
        StaffDTO buildStaffDTO = new StaffDTO();
        buildStaffDTO.setId(id);
        buildStaffDTO.setFirstName(firstName);
        buildStaffDTO.setSecondName(secondName);
        buildStaffDTO.setDesignation(designation);
        buildStaffDTO.setGender(gender);
        buildStaffDTO.setJoinedDate(joinedDate);
        buildStaffDTO.setDob(dob);
        buildStaffDTO.setAddressLine1(addressLine1);
        buildStaffDTO.setAddressLine2(addressLine2);
        buildStaffDTO.setAddressLine3(addressLine3);
        buildStaffDTO.setAddressLine4(addressLine4);
        buildStaffDTO.setAddressLine5(addressLine5);
        buildStaffDTO.setPhoneNumber(phoneNumber);
        buildStaffDTO.setEmail(email);
        staffService.updateMember(id, buildStaffDTO);
    }
}
