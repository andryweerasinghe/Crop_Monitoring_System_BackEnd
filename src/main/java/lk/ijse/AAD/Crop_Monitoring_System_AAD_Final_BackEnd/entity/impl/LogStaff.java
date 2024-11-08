/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/4/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.LogStaffId;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "logStaff")
public class LogStaff implements SuperEntity {
    @EmbeddedId
    private LogStaffId logStaffId;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id", nullable = false)
    private Staff staff;

    @ManyToOne
    @MapsId("logCode")
    @JoinColumn(name = "logCode", nullable = false)
    private MonitoringLogService log;

    private String activity;
    private String comments;
}
