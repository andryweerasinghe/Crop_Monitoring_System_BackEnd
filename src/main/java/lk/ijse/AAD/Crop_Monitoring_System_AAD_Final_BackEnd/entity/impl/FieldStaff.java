/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/4/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.FieldStaffId;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "fieldStaff")
public class FieldStaff implements SuperEntity {
    @EmbeddedId
    private FieldStaffId fieldStaffId;

    @ManyToOne
    @MapsId("fieldCode")
    @JoinColumn(name = "fieldCode", nullable = false)
    private Field field;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id", nullable = false)
    private Staff staff;

    private String hoursPerWeek;
    private String assignedDate;
    private String jobStatus;
}
