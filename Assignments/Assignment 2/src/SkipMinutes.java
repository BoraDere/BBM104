import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Skips the time by the given minute amount.
 */
public interface SkipMinutes {
    /**
     * @param strips is the split form of given command line.
     * @param currentTime is the LocalDateTime variable which has the current date-time value.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param switchTimeList is the arraylist that contains switch times (String).
     * @param dateFormatter is the instance of the DateFormatter class.
     * @param fileName is the name of the output file.
     * @return it returns currentTime so time flow will not be interrupted throughout the program.
     */
    static LocalDateTime skipMinutes(String[] strips, LocalDateTime currentTime, ArrayList<SmartDevice> deviceList, ArrayList<String> switchTimeList, DateFormatter dateFormatter, String fileName) {
        try {
            if (Long.parseLong(strips[1]) > 0) {
                LocalDateTime tempTime = currentTime;
                int n = 0;                                                      // same operations as the ones in the SetTime interface.
                for (int i = 0; i < switchTimeList.size(); i++) {
                    if (currentTime.plusMinutes(Long.parseLong(strips[1])).isAfter(dateFormatter.getDateTime(switchTimeList.get(i)))) {
                        n++;
                    }
                }
                if (n > 0) {
                    for (int i = 0; i < n; i++) {
                        currentTime = Nop.nop(dateFormatter, switchTimeList, currentTime, deviceList, fileName);
                    }
                }
                currentTime = tempTime.plusMinutes(Long.parseLong(strips[1]));
                for (SmartDevice device : deviceList) {                         // updating consumptions
                    if (device.getClass().getSimpleName().equals("SmartPlug") && device.isPlugged() && device.getStatus().equals("On")) {
                        device.setTempConsumption(device.getTempConsumption() + device.getAmpere()*220*Long.parseLong(strips[1])/60);
                    }
                    if (device.getClass().getSimpleName().equals("SmartCamera") && device.getStatus().equals("On")) {
                        device.setTempConsumption(device.getTempConsumption() + device.getMegabytes()*Long.parseLong(strips[1]));
                    }
                }
            }
            else if (Long.parseLong(strips[1]) == 0) {
                FileWrite.fileWrite("ERROR: Skipping 0 minutes will not change the time!\n", fileName); // that was not included in yours
            }
            else {
                FileWrite.fileWrite("ERROR: Time cannot be reversed!\n", fileName); // that was not included in yours
            }
        }
        catch (Exception e) {
            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
        }
        return currentTime;
    }
}
