import java.util.ArrayList;

/**
 * This interface changes kelvin and brightness values for smart lamps.
 */
public interface SetWhite {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     */
    static void setWhite(String[] strips, ArrayList<String> nameList, ArrayList<SmartDevice> deviceList, String fileName) {
        if (nameList.contains(strips[1])) {
            for (SmartDevice device : deviceList) {
                if (device.getName().equals(strips[1])) {
                    if (device.getClass().getSimpleName().equals("SmartLamp") || device.getClass().getSimpleName().equals("SmartColorLamp")) {
                        try {
                            if (2000 <= Integer.parseInt(strips[2]) && Integer.parseInt(strips[2]) <= 6500) {
                                if (0 <= Integer.parseInt(strips[3]) && Integer.parseInt(strips[3]) <= 100) {
                                    device.setKelvin(Integer.parseInt(strips[2]));
                                    device.setBrightness(Integer.parseInt(strips[3]));
                                }
                                else {
                                    FileWrite.fileWrite("ERROR: Brightness must be in range of 0%-100%!\n", fileName);
                                }
                            }
                            else {
                                FileWrite.fileWrite("ERROR: Kelvin value must be in range of 2000K-6500K!\n", fileName);
                            }
                        }
                        catch (Exception e) {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                        }
                    }
                    else {
                        FileWrite.fileWrite("ERROR: This device is not a smart lamp!\n", fileName);
                    }
                }
            }
        }
        else {
            FileWrite.fileWrite("ERROR: There is not such a device!\n", fileName);
        }
    }
}
