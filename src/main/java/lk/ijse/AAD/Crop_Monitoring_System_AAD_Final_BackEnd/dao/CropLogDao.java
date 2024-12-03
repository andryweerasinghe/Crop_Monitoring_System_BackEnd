package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.LogCrop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropLogDao extends JpaRepository<LogCrop, String> {
}
