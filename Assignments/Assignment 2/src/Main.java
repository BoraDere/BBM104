import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Main class is the driver and initializer for the code.
 */
public class Main {

    /**
     * This is the comparator for acquiring the necessary print sequence.
     */
    static Comparator<SmartDevice> deviceComparator = (d1, d2) -> {
        if (d1.getSwitchTime() != null && d2.getSwitchTime() != null) {
            return d1.getSwitchTime().compareTo(d2.getSwitchTime()); // comparator for 2 devices with switchTimes other than null
        }
        else if (d1.getSwitchTime() != null) { // if the second one's switchTime is null, first one will be placed before
            return -1;
        } else if (d2.getSwitchTime() != null) { // if the first one's switchTime is null, second one will be placed before
            return 1;
        }
        else { // if both of their switchTimes are null
            return 0;
        }
    };

    /**
     * @param args are given from command line.
     */
    public static void main(String[] args) {
        String[] lines = ReadFile.readFile(args[0], true, true);
        String fileName = args[1];
        LocalDateTime currentTime = LocalDateTime.now(); // just initializing
        ArrayList<SmartDevice> deviceList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> switchTimeList = new ArrayList<>();
        DateFormatter dateFormatter = new DateFormatter("yyyy[-M][-d]_HH:mm:ss"); // so it takes care of 03 being represented as 3
        SmartDevice deviceToOperate = null; // so I can use for-each
        for (String line : lines) {
            String[] strips = line.split("\t");
            FileWrite.fileWrite("COMMAND: " + line + "\n", fileName);
            if (!lines[0].startsWith("SetInitialTime")) {
                FileWrite.fileWrite("ERROR: First command must be set initial time! Program is going to terminate!\n", fileName);
                break;
            }
            else if (line.startsWith("SetInitialTime") && strips.length == 2) { // a small process, I think it can stay like this in the main class
                if (Arrays.asList(lines).indexOf(line) != 0) {
                    FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                }
                else {
                    try {
                        currentTime = dateFormatter.getDateTime(line.split("\t")[1]);
                        FileWrite.fileWrite("SUCCESS: Time has been set to " + dateFormatter.getFormattedDate(currentTime) + "!\n", fileName);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        FileWrite.fileWrite("ERROR: First command must be set initial time! Program is going to terminate!\n", fileName);
                        break;                          // I actually cannot remember why I put this, but I will not remove it
                                                        // since it probably catches an exception which I have tested in the early stages
                    }
                }
            }
            else if (line.startsWith("SetTime") && strips.length == 2) {
                currentTime = SetTime.setTime(dateFormatter, currentTime, line.split("\t")[1], deviceList, switchTimeList, fileName); // I thought this way would be much cleaner.
            }
            else if (line.startsWith("Add")) {
                Add.add(strips, nameList, deviceList, fileName);
            }
            else if (line.startsWith("SetSwitchTime") && strips.length == 3) {
                SetSwitchTime.setSwitchTime(nameList, strips, dateFormatter, deviceList, switchTimeList, currentTime, fileName);
            }
            else if (line.startsWith("SkipMinutes") && strips.length == 2) {
                currentTime = SkipMinutes.skipMinutes(strips, currentTime, deviceList, switchTimeList, dateFormatter, fileName);
            }
            else if (line.startsWith("Nop") && strips.length == 1) {
                currentTime = Nop.nop(dateFormatter, switchTimeList, currentTime, deviceList, fileName);
            }
            else if (line.startsWith("Remove") && strips.length == 2) {
                Remove.remove(nameList, strips, deviceToOperate, deviceList, fileName); // deviceToOperate explained in the interface
            }
            else if (line.startsWith("Switch") && strips.length == 3) {
                Switch.switcher(strips, nameList, deviceList, switchTimeList, dateFormatter, fileName);
            }
            else if (line.startsWith("ChangeName") && strips.length == 3) { // a small process, I think it can stay like this in the main class
                if (strips[1].equals(strips[2])) {
                    FileWrite.fileWrite("ERROR: Both of the names are the same, nothing changed!\n", fileName);
                }
                else {
                    if (!nameList.contains(strips[1])) {
                        FileWrite.fileWrite("ERROR: There is not a smart device with that name!\n", fileName);
                    }
                    else if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        for (SmartDevice device : deviceList) {
                            if (device.getName().equals(strips[1])) {
                                device.setName(strips[2]);
                                nameList.remove(strips[1]);
                                nameList.add(strips[2]);
                            }
                        }
                    }
                }
            }
            else if (line.startsWith("PlugIn") && strips.length == 3) {
                PlugIn.plugIn(strips, nameList, deviceList, fileName);
            }
            else if (line.startsWith("PlugOut") && strips.length == 2) {
                PlugOut.plugOut(strips, nameList, deviceList, fileName);
            }
            else if (line.startsWith("SetKelvin") && strips.length == 3) {
                SetKelvin.setKelvin(strips, nameList, deviceList, fileName);
            }
            else if (line.startsWith("SetBrightness") && strips.length == 3) {
                SetBrightness.setBrightness(strips, nameList, deviceList, fileName);
            }
            else if (line.startsWith("SetColorCode") && strips.length == 3) {
                SetColorCode.setColorCode(strips, nameList, deviceList, fileName);
            }
            else if (line.startsWith("SetWhite") && strips.length == 4) {
                SetWhite.setWhite(strips, nameList, deviceList, fileName);
            }
            else if (line.startsWith("SetColor") && strips.length == 4) {
                SetColor.setColor(strips, nameList, deviceList, fileName);
            }
            else if (line.startsWith("ZReport") && strips.length == 1) { // a small process, I think it can stay like this in the main class
                deviceList.sort(deviceComparator);
                FileWrite.fileWrite("Time is:\t" + dateFormatter.getFormattedDate(currentTime) + "\n", fileName);
                for (SmartDevice device : deviceList) {
                    FileWrite.fileWrite(device.toString() + "\n", fileName);
                }
            }
            else {
                FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
            }
        }
        if (!lines[lines.length-1].equals("ZReport")) { // pdfe yaz
            deviceList.sort(deviceComparator);
            FileWrite.fileWrite("Time is:\t" + dateFormatter.getFormattedDate(currentTime) + "\n", fileName);
            for (SmartDevice device : deviceList) {
                FileWrite.fileWrite(device.toString() + "\n", fileName);
            }
        }
    }
}
