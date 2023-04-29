import java.util.ArrayList;

/**
 * Interface PlugIn plugs the specified plugs in.
 */
public interface PlugIn {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     */
    static void plugIn(String[] strips, ArrayList<String> nameList, ArrayList<SmartDevice> deviceList, String fileName) {
        if (nameList.contains(strips[1])) {
            for (SmartDevice device : deviceList) {
                if (device.getName().equals(strips[1])) {
                    if (device.getClass().getSimpleName().equals("SmartPlug")) {
                        if (device.isPlugged()) {
                            FileWrite.fileWrite("ERROR: There is already an item plugged in to that plug!\n", fileName);
                        }
                        else {
                            try {
                                if (Double.parseDouble(strips[2]) > 0) {
                                    device.setPlugged(true);
                                    device.setAmpere(Double.parseDouble(strips[2]));
                                }
                                else {
                                    FileWrite.fileWrite("ERROR: Ampere value must be a positive number!\n", fileName);
                                }
                            }
                            catch (Exception e) {
                                FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                            }
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
