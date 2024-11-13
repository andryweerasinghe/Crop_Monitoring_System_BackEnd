/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/4/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.Role;
import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements SuperEntity {
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
