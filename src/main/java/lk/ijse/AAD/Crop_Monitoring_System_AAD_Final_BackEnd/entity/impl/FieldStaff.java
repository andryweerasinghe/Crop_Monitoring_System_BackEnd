/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/4/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl;

import jakarta.persistence.*;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.FieldStaffId;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field_staff")
public class FieldStaff implements SuperEntity {
    @Id
    private FieldStaffId field_staff_id;

    @ManyToOne
    @JoinColumn(name = "field_code", nullable = false)
    private Field field;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Staff staff;

    private String hours_per_week;
    private String assigned_date;
    private String job_status;
}
