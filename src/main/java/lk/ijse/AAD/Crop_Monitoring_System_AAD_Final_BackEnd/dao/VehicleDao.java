package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.dao;

import lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.entity.impl.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle,String> {
}
