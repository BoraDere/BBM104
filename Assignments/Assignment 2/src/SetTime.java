import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * This interface sets the currentTime to the required time.
 */
public interface SetTime {
    /**
     * @param dateFormatter is the instance of the DateFormatter class.
     * @param currentTime is the LocalDateTime variable which has the current date-time value.
     * @param line is the selected element of String array lines.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param switchTimeList is the arraylist that contains switch times (String).
     * @param fileName is the name of the output file.
     * @return it returns currentTime so time flow will not be interrupted throughout the program.
     */
    static LocalDateTime setTime(DateFormatter dateFormatter, LocalDateTime currentTime, String line, ArrayList<SmartDevice> deviceList, ArrayList<String> switchTimeList, String fileName) {
        try {
            LocalDateTime tempDate = dateFormatter.getDateTime(line);
            if (tempDate.isAfter(currentTime)) {
                int n = 0;                                                                              // doing these
                for (int i = 0; i < switchTimeList.size(); i++) {                                       // operations so
                    if (tempDate.isAfter(dateFormatter.getDateTime(switchTimeList.get(i)))) {           // if the time to be set
                        n++;                                                                            // comes after from
                    }                                                                                   // a switchTime, that
                }                                                                                       // switchTime will be
                if (n > 0) {                                                                            // processed too.
                    for (int i = 0; i < n; i++) {
                        currentTime = Nop.nop(dateFormatter, switchTimeList, currentTime, deviceList, fileName);
                    }
                }
                double diff = currentTime.until(tempDate, ChronoUnit.MINUTES);
                currentTime = tempDate;
                for (SmartDevice device : deviceList) {                                                 // consumption calculations
                    if (device.getClass().getSimpleName().equals("SmartPlug") && device.isPlugged() && device.getStatus().equals("On")) {
                        device.setTempConsumption(device.getTempConsumption() + device.getAmpere()*220*diff/60);
                    }
                    if (device.getClass().getSimpleName().equals("SmartCamera") && device.getStatus().equals("On")) {
                        device.setTempConsumption(device.getTempConsumption() + device.getMegabytes()*diff);
                    }
                }
            }
            else if (tempDate.isEqual(currentTime)) {
                FileWrite.fileWrite("ERROR: Provided time is the same!\n", fileName); // that was not included in yours
            }
            else {
                FileWrite.fileWrite("ERROR: Time cannot be reversed!\n", fileName);
            }
        }
        catch (Exception e) {
            FileWrite.fileWrite("ERROR: Time format is not correct!\n", fileName);
        }
        return currentTime;
    }
}
