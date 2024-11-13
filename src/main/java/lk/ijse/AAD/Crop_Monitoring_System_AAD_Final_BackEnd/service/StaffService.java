package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.StaffStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveMember(StaffDTO staffDTO) throws Exception;
    List<StaffDTO> getAllStaffMembers() throws Exception;
    StaffStatus getMember(String id) throws Exception;
    void deleteMember(String id) throws Exception;
    void updateMember(String id, StaffDTO staffDTO) throws Exception;
}
