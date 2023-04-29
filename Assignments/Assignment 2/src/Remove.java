import java.util.ArrayList;

/**
 * Interface Remove is responsible for removing devices and while doing so, it also sets consumption values of devices.
 */
public interface Remove {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceToOperate is to use for-each.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     */
    static void remove(ArrayList<String> nameList, String[] strips, SmartDevice deviceToOperate, ArrayList<SmartDevice> deviceList, String fileName) { // actually no need to give
        if (nameList.contains(strips[1])) {                                                                                           // deviceToOperate but I wanted to
            for (SmartDevice device : deviceList) {                                                                                   // keep it alike with others
                if (device.getName().equals(strips[1])) {
                    deviceToOperate = device;
                }
            }
            if (deviceToOperate.getClass().getSimpleName().equals("SmartPlug") || deviceToOperate.getClass().getSimpleName().equals("SmartCamera")) { // because
                deviceToOperate.setConsumption(deviceToOperate.getTempConsumption() + deviceToOperate.getConsumption());                              // we turn the device off
                deviceToOperate.setTempConsumption(0);
            }
            FileWrite.fileWrite("SUCCESS: Information about removed smart device is as follows:\n", fileName);
            FileWrite.fileWrite(deviceToOperate + "\n", fileName);
            deviceToOperate.setStatus("Off");
            deviceList.remove(deviceToOperate);
            nameList.remove(strips[1]);
        }
        else {
            FileWrite.fileWrite("ERROR: There is not a device with given name!\n", fileName);
        }
    }
}
