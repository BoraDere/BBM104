import java.util.ArrayList;

/**
 * Switch interface does the manuel switching operations.
 */
public interface Switch {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param switchTimeList is the arraylist that contains switch times (String).
     * @param dateFormatter is the instance of the DateFormatter class.
     * @param fileName is the name of the output file.
     */
    static void switcher(String[] strips, ArrayList<String> nameList, ArrayList<SmartDevice> deviceList, ArrayList<String> switchTimeList, DateFormatter dateFormatter, String fileName) {
        if (nameList.contains(strips[1])) {
            if (strips[2].equals("Off") || strips[2].equals("On")) {
                for (SmartDevice device : deviceList) {
                    if (device.getName().equals(strips[1])) {
                        if (device.getStatus().equals(strips[2]) && strips[2].equals("On")) {
                            FileWrite.fileWrite("ERROR: This device is already switched on!\n", fileName);
                        }
                        else if (device.getStatus().equals(strips[2]) && strips[2].equals("Off")) {
                            FileWrite.fileWrite("ERROR: This device is already switched off!\n", fileName);
                        }
                        else {
                            if (device.getClass().getSimpleName().equals("SmartPlug") || device.getClass().getSimpleName().equals("SmartCamera")) {
                                if (device.getStatus().equals("On")) {
                                    device.setConsumption(device.getTempConsumption() + device.getConsumption()); // consumption setting
                                    device.setTempConsumption(0);
                                }
                            }
                            device.setStatus(strips[2]);
                            if (device.getSwitchTime() != null) {
                                switchTimeList.remove(dateFormatter.getFormattedDate(device.getSwitchTime()));
                                device.setSwitchTime(null);
                            }
                        }
                    }
                }
            }
            else {
                FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
            }
        }
        else {
            FileWrite.fileWrite("ERROR: There is not such a device!\n", fileName);
        }
    }
}
