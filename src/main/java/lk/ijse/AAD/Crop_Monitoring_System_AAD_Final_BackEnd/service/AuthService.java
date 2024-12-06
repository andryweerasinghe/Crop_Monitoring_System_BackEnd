package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.service;

public interface AuthService {
    JWTAuthResponse signUp(UserDtoImpl userDto);
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse refreshToken(String existingToken);
}
