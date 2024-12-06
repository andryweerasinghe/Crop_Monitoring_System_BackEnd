package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

import java.util.Map;

public interface JWTService {
    String getUserEmail(String extractedJwtToken);
    boolean isTokenValid(String extractedJwtToken, UserDetails userDetails);
    String generateJwtToken(Map<String,Object> extractClaims, UserDetails userDetails);
    String generateJwtToken(UserDetails userEntity);
}
