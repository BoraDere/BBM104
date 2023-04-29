import java.util.ArrayList;

/**
 * PlugOut interface does the plugging out operations for SmartPlugs.
 */
public interface PlugOut {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     */
    static void plugOut(String[] strips, ArrayList<String> nameList, ArrayList<SmartDevice> deviceList, String fileName) {
        if (nameList.contains(strips[1])) {
            for (SmartDevice device : deviceList) {
                if (device.getName().equals(strips[1])) {
                    if (device.getClass().getSimpleName().equals("SmartPlug")) {
                        if (!device.isPlugged()) {
                            FileWrite.fileWrite("ERROR: This plug has no item to plug out from that plug!\n", fileName);
                        }
                        else {
                            device.setConsumption(device.getTempConsumption() + device.getConsumption());
                            device.setTempConsumption(0);
                            device.setStatus("Off"); // It must be closed after plugging out
                            device.setPlugged(false);
                        }
                    }
                    else {
                        FileWrite.fileWrite("ERROR: This device is not a smart plug!\n", fileName);
                    }
                }
            }
        }
        else {
            FileWrite.fileWrite("ERROR: There is not such a device!\n", fileName);
        }
    }
}
