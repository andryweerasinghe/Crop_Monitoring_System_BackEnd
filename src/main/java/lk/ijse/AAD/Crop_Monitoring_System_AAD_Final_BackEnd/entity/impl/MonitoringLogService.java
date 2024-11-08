/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/3/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "monitoringLogService")
public class MonitoringLogService implements SuperEntity {
    @Id
    private String logCode;
    private String logDate;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @ManyToMany(mappedBy = "field")
    private List<Field> fields;
    @ManyToMany(mappedBy = "crop")
    private List<Crop> crops;
    @ManyToMany(mappedBy = "staff")
    private List<Staff> staff;
}
