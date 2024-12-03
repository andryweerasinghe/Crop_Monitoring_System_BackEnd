/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 12/3/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropLogDTO implements SuperDTO {
    private String logCropId;
    private String cropCode;
    private String logCode;
    private String cropCondition;
    private String comments;
    private String logDate;
    private String image;
}
