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
    private String log_crop_id;

    @ManyToOne
    @JoinColumn(name = "crop_code", referencedColumnName = "crop_code")
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "log_code", referencedColumnName = "log_code")
    private MonitoringLog monitoring_log;

    private String crop_condition;
    private String comments;
}
