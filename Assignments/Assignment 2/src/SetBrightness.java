import java.util.ArrayList;

/**
 * SetBrightness interface is responsible for setting the brightness value of smart lamps.
 */
public interface SetBrightness {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     */
    static void setBrightness(String[] strips, ArrayList<String> nameList, ArrayList<SmartDevice> deviceList, String fileName) {
        if (nameList.contains(strips[1])) {
            for (SmartDevice device : deviceList) {
                if (device.getName().equals(strips[1])) {
                    if (device.getClass().getSimpleName().equals("SmartLamp") || device.getClass().getSimpleName().equals("SmartColorLamp")) { // since it is applicable
                        try {                                                                                                                  // only for these two devices
                            if (0 <= Integer.parseInt(strips[2]) && Integer.parseInt(strips[2]) <= 100) {
                                device.setBrightness(Integer.parseInt(strips[2]));
                            }
                            else {
                                FileWrite.fileWrite("ERROR: Brightness must be in range of 0%-100%!\n", fileName);
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
