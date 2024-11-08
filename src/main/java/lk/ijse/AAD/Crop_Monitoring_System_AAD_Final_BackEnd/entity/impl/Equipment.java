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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class Equipment implements SuperEntity {
    @Id
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Staff staffDetails;
    @ManyToOne
    @JoinColumn(name = "fieldCode", nullable = false)
    private Field fieldDetails;
}
