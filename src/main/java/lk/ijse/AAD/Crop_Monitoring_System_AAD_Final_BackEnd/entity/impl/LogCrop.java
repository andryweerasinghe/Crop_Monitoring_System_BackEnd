/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/4/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "logCrop")
public class LogCrop implements SuperEntity {

    @EmbeddedId
    private String log_crop_id;
    @ManyToOne
    @MapsId("cropCode")
    @JoinColumn(name = "cropCode", nullable = false)
    private Crop crop;

    @ManyToOne
    @MapsId("logCode")
    @JoinColumn(name = "logCode", nullable = false)
    private MonitoringLogService log;

    private String condition;
    private String comments;
}
