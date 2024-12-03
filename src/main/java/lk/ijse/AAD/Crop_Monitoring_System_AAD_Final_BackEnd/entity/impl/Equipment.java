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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class Equipment implements SuperEntity {
    @Id
    private String equipment_id;
    private String equipment_name;
    private String equipment_type;
    private String status;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Staff staff_details;
    @ManyToOne
    @JoinColumn(name = "field_code", nullable = false)
    private Field field_details;
}
