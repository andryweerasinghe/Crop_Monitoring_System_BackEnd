/*
 * Author  : Mr.electrix
 * Project : NotesCollector
 * Date    : 10/5/24

 */

package lk.ijse.AAD.Crop_Monitoring_System_AAD_Final_BackEnd.util;

import java.util.regex.Pattern;

public class RegexProcess {
    public static boolean cropCodeMatcher(String cropCode) {
        String regexForCropCode = "^CROP-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForCropCode);
        return regexPattern.matcher(cropCode).matches();
    }
    public static boolean staffIdMatcher(String userId) {
        String regexForStaffID = "^STAFF-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForStaffID);
        return regexPattern.matcher(userId).matches();
    }
    public static boolean equipmentIdMatcher(String equipmentId) {
        String regexForEquipmentID = "^EQUIPMENT-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForEquipmentID);
        return regexPattern.matcher(equipmentId).matches();
    }
    public static boolean fieldCodeMatcher(String fieldCode) {
        String regexForFieldCode = "^FIELD-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForFieldCode);
        return regexPattern.matcher(fieldCode).matches();
    }
    public static boolean vehicleCodeMatcher(String vehicleCode) {
        String regexForVehicleCode = "^VEHICLE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForVehicleCode);
        return regexPattern.matcher(vehicleCode).matches();
    }
    public static boolean logCodeMatcher(String logCode) {
        String regexForLogCode = "^LOG-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForLogCode);
        return regexPattern.matcher(logCode).matches();
    }
}
