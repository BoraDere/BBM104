import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class and its method are invoked when Nop command is called and changes the currentTime to the next switchTime.
 */
public interface Nop {
    /**
     * @param dateFormatter is the instance of the DateFormatter class.
     * @param switchTimeList is the arraylist that contains switch times (String).
     * @param currentTime is the LocalDateTime variable which has the current date-time value.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     * @return it returns currentTime so time flow will not be interrupted throughout the program.
     */
    static LocalDateTime nop(DateFormatter dateFormatter, ArrayList<String> switchTimeList, LocalDateTime currentTime, ArrayList<SmartDevice> deviceList, String fileName) {
        if (!switchTimeList.isEmpty()) {
            SetTime.setTime(dateFormatter, currentTime, switchTimeList.get(0), deviceList, switchTimeList, fileName); // changing current time since Nop kinda does this operation
            for (SmartDevice device : deviceList) {
                if (switchTimeList.get(0).equals(dateFormatter.getFormattedDate(device.getSwitchTime()))) {
                    if (device.getStatus().equals("On")) { // consumption updating
                        if (device.getClass().getSimpleName().equals("SmartPlug") && device.isPlugged()) {
                            device.setConsumption(device.getTempConsumption() + device.getConsumption());
                            device.setTempConsumption(0);
                        }
                        if (device.getClass().getSimpleName().equals("SmartCamera")) {
                            device.setConsumption(device.getTempConsumption() + device.getConsumption());
                            device.setTempConsumption(0);
                        }
                        device.setStatus("Off");
                    }
                    else if (device.getStatus().equals("Off")) {
                        device.setStatus("On");
                    }
                    device.setSwitchTime(null);
                }
            }
            currentTime = dateFormatter.getDateTime(switchTimeList.get(0)); // since earliest time is at the beginning of the arraylist
            switchTimeList.remove(0);
        }
        else {
            FileWrite.fileWrite("ERROR: There is nothing to switch!\n", fileName);
        }
        return currentTime;
    }
}
