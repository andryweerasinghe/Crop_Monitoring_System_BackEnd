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
@Table(name = "crop")
public class Crop implements SuperEntity {
    @Id
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String category;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    private String season;
    @ManyToOne
    @JoinColumn(name = "fieldCode", nullable = false)
    private Field field;
    @OneToMany(mappedBy = "crop")
    private List<LogCrop> logCrops;
}
