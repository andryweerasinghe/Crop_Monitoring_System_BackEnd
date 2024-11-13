/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/14/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes.SelectedErrorStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao.StaffDao;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.StaffStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl.StaffDTO;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Staff;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.DataPersistException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.exception.MemberNotFoundException;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service.StaffService;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveMember(StaffDTO staffDTO) throws Exception {
        Staff savedMember = staffDao.save(mapping.toStaff(staffDTO));
        if (savedMember == null) {
            throw new DataPersistException("Failed to save member");
        }
    }

    @Override
    public List<StaffDTO> getAllStaffMembers() throws Exception {
        List<Staff> allStaffMembers = staffDao.findAll();
        return mapping.asStaffDTOList(allStaffMembers);
    }

    @Override
    public StaffStatus getMember(String id) throws Exception {
        if (staffDao.existsById(id)) {
            Staff selectedMember = staffDao.getReferenceById(id);
            return (StaffStatus) mapping.toStaffDTO(selectedMember);
        } else {
            return new SelectedErrorStatus(2, "Staff Member with id" + id + " not found");
        }
    }

    @Override
    public void deleteMember(String id) throws Exception {
        Optional<Staff> existingMember = staffDao.findById(id);
        if (!existingMember.isPresent()) {
            throw new MemberNotFoundException("Member with id" + id + " not found");
        } else {
            staffDao.deleteById(id);
        }
    }

    @Override
    public void updateMember(String id, StaffDTO staffDTO) throws Exception {
        Optional<Staff> existingMember = staffDao.findById(id);
        if (existingMember.isPresent()) {
            existingMember.get().setId(staffDTO.getId());
            existingMember.get().setFirstName(staffDTO.getFirstName());
            existingMember.get().setSecondName(staffDTO.getSecondName());
            existingMember.get().setDesignation(staffDTO.getDesignation());
            existingMember.get().setGender(staffDTO.getGender());
            existingMember.get().setJoinedDate(staffDTO.getJoinedDate());
            existingMember.get().setDob(staffDTO.getDob());
            existingMember.get().setAddressLine1(staffDTO.getAddressLine1());
            existingMember.get().setAddressLine2(staffDTO.getAddressLine2());
            existingMember.get().setAddressLine3(staffDTO.getAddressLine3());
            existingMember.get().setAddressLine4(staffDTO.getAddressLine4());
            existingMember.get().setAddressLine5(staffDTO.getAddressLine5());
            existingMember.get().setPhoneNumber(staffDTO.getPhoneNumber());
            existingMember.get().setEmail(staffDTO.getEmail());
        }
    }
}
