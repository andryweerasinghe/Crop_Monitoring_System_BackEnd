/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/4/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.LogFieldId;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "logField")
public class LogField implements SuperEntity {
    @EmbeddedId
    private LogFieldId logFieldId;

    @ManyToOne
    @MapsId("fieldCode")
    @JoinColumn(name = "fieldCode", nullable = false)
    private Field field;

    @ManyToOne
    @MapsId("logCode")
    @JoinColumn(name = "logCode", nullable = false)
    private MonitoringLogService log;

    private String dateMonitored;
    private String comments;
}
