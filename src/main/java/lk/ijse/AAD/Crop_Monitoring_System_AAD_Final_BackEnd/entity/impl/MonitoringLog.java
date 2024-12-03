/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/3/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "monitoring_log")
public class MonitoringLog implements SuperEntity {
    @Id
    private String log_code;
    private String log_date;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @OneToMany(mappedBy = "monitoring_log")
    private List<LogField> fieldLogs;
    @OneToMany(mappedBy = "monitoring_log")
    private List<LogCrop> cropLogs;
    @OneToMany(mappedBy = "monitoring_log")
    private List<LogStaff> staffLogs;
}
