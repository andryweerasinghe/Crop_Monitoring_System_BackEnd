package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDao extends JpaRepository<Crop,String> {

}
