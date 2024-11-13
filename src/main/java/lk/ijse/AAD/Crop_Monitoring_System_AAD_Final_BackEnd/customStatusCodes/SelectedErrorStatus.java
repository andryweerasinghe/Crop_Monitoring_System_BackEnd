/*
 * Author  : Mr.electrix
 * Project : NotesCollector
 * Date    : 10/5/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.customStatusCodes;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.CropStatus;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CropStatus, StaffStatus {
    private int statusCode;
    private String statusMessage;
}
