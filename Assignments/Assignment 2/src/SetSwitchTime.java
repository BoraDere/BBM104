import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * SetSwitchTime interface takes care of switch time-setting operations.
 */
public interface SetSwitchTime {
    /**
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param strips is the split form of given command line.
     * @param dateFormatter is the instance of the DateFormatter class.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param switchTimeList is the arraylist that contains switch times (String).
     * @param currentTime is the LocalDateTime variable which has the current date-time value.
     * @param fileName is the name of the output file.
     */
    static void setSwitchTime(ArrayList<String> nameList, String[] strips, DateFormatter dateFormatter,
                              ArrayList<SmartDevice> deviceList, ArrayList<String> switchTimeList, LocalDateTime currentTime, String fileName) {
        if (!nameList.contains(strips[1])) {
            FileWrite.fileWrite("ERROR: Device not found!\n", fileName);
        }
        else {
            try {
                LocalDateTime time = dateFormatter.getDateTime(strips[2]);
                if (time.isAfter(currentTime)) {
                    for (SmartDevice device : deviceList) {
                        if (device.getName().equals(strips[1])) {
                            device.setSwitchTime(time);
                            if (!switchTimeList.contains(strips[2])) {
                                switchTimeList.add(strips[2]);
                                Collections.sort(switchTimeList);
                            }
                        }
                    }
                }
                else {
                    FileWrite.fileWrite("ERROR: Switch time cannot be earlier than the current time!\n", fileName); // that was not included in yours
                }
            }
            catch (Exception e) {
                FileWrite.fileWrite("ERROR: Time format is not correct!\n", fileName);
            }
        }
    }
}
