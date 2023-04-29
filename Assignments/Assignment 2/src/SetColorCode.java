import java.util.ArrayList;

/**
 * This is pretty much alike with SetColor, the difference is this interface has nothing to do with brightness.
 */
public interface SetColorCode {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     */
    static void setColorCode(String[] strips, ArrayList<String> nameList, ArrayList<SmartDevice> deviceList, String fileName) {
        if (nameList.contains(strips[1])) {
            for (SmartDevice device : deviceList) {
                if (device.getName().equals(strips[1])) {
                    if (device.getClass().getSimpleName().equals("SmartColorLamp")) {
                        if (device.getIsColored()) {
                            try {
                                if (Integer.parseInt(strips[2].substring(2), 16) >= 0 && Integer.parseInt(strips[2].substring(2), 16) <= 16777215) {
                                    device.setHexCode(strips[2]);
                                } else {
                                    FileWrite.fileWrite("ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n", fileName);
                                }
                            }
                            catch (Exception e) {
                                FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                            }
                        }
                        else {
                            FileWrite.fileWrite("ERROR: Smart lamp is not in color mode!\n", fileName); // that was not included in yours
                        }
                    }
                    else {
                        FileWrite.fileWrite("ERROR: This device is not a smart color lamp!\n", fileName);
                    }
                }
            }
        }
        else {
            FileWrite.fileWrite("ERROR: There is not such a device!\n", fileName);
        }
    }
}
