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
@Table(name = "monitoringLog")
public class MonitoringLog implements SuperEntity {
    @Id
    private String logCode;
    private String logDate;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @OneToMany(mappedBy = "monitoringLog")
    private List<LogField> fieldLogs;
    @OneToMany(mappedBy = "monitoringLog")
    private List<LogCrop> cropLogs;
    @OneToMany(mappedBy = "monitoringLog")
    private List<LogStaff> staffLogs;
}
