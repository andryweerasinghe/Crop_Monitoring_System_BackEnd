/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/4/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "log_crop")
public class LogCrop implements SuperEntity {

    @Id
    private String logCropId;

    @ManyToOne
    @JoinColumn(name = "cropCode", referencedColumnName = "cropCode")
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "logCode", referencedColumnName = "logCode")
    private MonitoringLog monitoringLog;

    private String cropCondition;
    private String comments;
}
