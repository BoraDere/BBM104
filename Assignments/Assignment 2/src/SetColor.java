import java.util.ArrayList;

/**
 * Interface SetColor sets the color value and brightness of Smart Color Lamps.
 */
public interface SetColor {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     */
    static void setColor(String[] strips, ArrayList<String> nameList, ArrayList<SmartDevice> deviceList, String fileName) {
        if (nameList.contains(strips[1])) {
            for (SmartDevice device : deviceList) {
                if (device.getName().equals(strips[1])) {
                    if (device.getClass().getSimpleName().equals("SmartColorLamp")) { // since it is exclusive to Smart Color Lamp
                        try {
                            if (Integer.parseInt(strips[2].substring(2), 16) >= 0 && Integer.parseInt(strips[2].substring(2), 16) <= 16777215) {
                                if (0 <= Integer.parseInt(strips[3]) && Integer.parseInt(strips[3]) <= 100) {
                                    device.setHexCode(strips[2]);
                                    device.setBrightness(Integer.parseInt(strips[3]));
                                    device.setIsColored(true);
                                }
                                else {
                                    FileWrite.fileWrite("ERROR: Brightness must be in range of 0%-100%!\n", fileName);
                                }
                            }
                            else {
                                FileWrite.fileWrite("ERROR: Color code value must be in range of 0x0-0xFFFFFF!\n", fileName);
                            }
                        }
                        catch (Exception e) {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
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
