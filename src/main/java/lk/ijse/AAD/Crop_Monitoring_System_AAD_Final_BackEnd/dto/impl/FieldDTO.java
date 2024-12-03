/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/15/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO {
    private String fieldCode;
    private String fieldName;
    private String size;
    private String fieldLocation;
    private String image1;
    private String image2;
    private String staffId;
}
