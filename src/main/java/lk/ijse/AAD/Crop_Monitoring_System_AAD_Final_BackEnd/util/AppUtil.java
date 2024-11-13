/*
 * Author  : Mr.electrix
 * Project : Crop_Monitoring_System_AAD_Final
 * Date    : 11/10/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util;

import java.awt.*;
import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateCropCode() {return "CROP-" + UUID.randomUUID();}
    public static String imageToBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }
}
