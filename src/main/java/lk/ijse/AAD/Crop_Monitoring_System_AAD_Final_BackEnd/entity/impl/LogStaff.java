/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/4/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.LogStaffId;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "log_staff")
public class LogStaff implements SuperEntity {
    @Id
    private String logStaffId;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "logCode", referencedColumnName = "logCode")
    private MonitoringLog monitoringLog;

    private String activity;
    private String comments;
}
