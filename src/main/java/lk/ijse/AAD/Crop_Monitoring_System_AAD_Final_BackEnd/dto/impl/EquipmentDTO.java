/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/14/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO {
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    private String id;
    private String fieldCode;
}
