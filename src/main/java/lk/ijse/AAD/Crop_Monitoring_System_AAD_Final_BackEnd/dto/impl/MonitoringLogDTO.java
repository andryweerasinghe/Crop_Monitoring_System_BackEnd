/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/22/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO {
    private String logCode;
    private String logDate;
    private String image;
}
