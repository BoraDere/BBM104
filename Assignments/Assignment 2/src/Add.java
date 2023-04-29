import java.util.ArrayList;

/**
 * Interface Add is responsible for all the adding operations.
 */
public interface Add {
    /**
     * @param strips is the split form of given command line.
     * @param nameList is the arraylist that contains names (String) of all devices.
     * @param deviceList is the arraylist that contains all devices (SmartDevice).
     * @param fileName is the name of the output file.
     */
    static void add(String[] strips, ArrayList<String> nameList, ArrayList<SmartDevice> deviceList, String fileName) { // Method to do adding operations.
        if (strips.length < 3 ) { // checking the length of the command and controlling the flow according to the given command
            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
        }
        else {
            if (strips[1].equals("SmartLamp")) {
                if (strips.length == 3) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        deviceList.add(new SmartLamp(strips[2]));
                        nameList.add(strips[2]);
                    }
                }
                else if (strips.length == 4) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        if (strips[3].equals("On") || strips[3].equals("Off")) {
                            deviceList.add(new SmartLamp(strips[2], strips[3]));
                            nameList.add(strips[2]);
                        }
                        else {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                        }
                    }
                }
                else if (strips.length == 6) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        if (strips[3].equals("On") || strips[3].equals("Off")) {
                            try {
                                if (2000 <= Integer.parseInt(strips[4]) && Integer.parseInt(strips[4]) <= 6500) {
                                    if (0 <= Integer.parseInt(strips[5]) && Integer.parseInt(strips[5]) <= 100) {
                                        deviceList.add(new SmartLamp(strips[2], strips[3], Integer.parseInt(strips[4]), Integer.parseInt(strips[5])));
                                        nameList.add(strips[2]);
                                    } else {
                                        FileWrite.fileWrite("ERROR: Brightness must be in range of 0%-100%!\n", fileName);
                                    }
                                } else {
                                    FileWrite.fileWrite("ERROR: Kelvin value must be in range of 2000K-6500K!\n", fileName);
                                }
                            } catch (Exception e) {
                                FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                            }
                        } else {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                        }
                    }
                }
                else {
                    FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                }
            }
            else if (strips[1].equals("SmartPlug")) {
                if (strips.length == 3) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        deviceList.add(new SmartPlug(strips[2]));
                        nameList.add(strips[2]);
                    }
                }
                else if (strips.length == 4) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        if (strips[3].equals("On") || strips[3].equals("Off")) {
                            deviceList.add(new SmartPlug(strips[2], strips[3]));
                            nameList.add(strips[2]);
                        }
                        else {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                        }
                    }
                }
                else if (strips.length == 5) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        if (strips[3].equals("On") || strips[3].equals("Off")) {
                            try {
                                if (Double.parseDouble(strips[4]) > 0) {
                                    deviceList.add(new SmartPlug(strips[2], strips[3], Double.parseDouble(strips[4])));
                                    nameList.add(strips[2]);
                                } else {
                                    FileWrite.fileWrite("ERROR: Ampere value must be a positive number!\n", fileName);
                                }
                            }
                            catch (Exception e) {
                                FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                            }
                        }
                        else {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                        }
                    }
                }
                else {
                    FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                }
            }
            else if (strips[1].equals("SmartColorLamp")) {
                if (strips.length == 3) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        deviceList.add(new SmartColorLamp(strips[2]));
                        nameList.add(strips[2]);
                    }
                }
                else if (strips.length == 4) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        deviceList.add(new SmartColorLamp(strips[2], strips[3]));
                        nameList.add(strips[2]);
                    }
                }
                else if (strips.length == 6) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        if (strips[3].equals("On") || strips[3].equals("Off")) {
                            if (strips[4].contains("x")) {
                                try {
                                    if (Integer.parseInt(strips[4].substring(2), 16) > 0 && Integer.parseInt(strips[4].substring(2), 16) < 16777215) {
                                        SmartColorLamp tempLamp = new SmartColorLamp(strips[2], strips[3], strips[4], Integer.parseInt(strips[5]));
                                        tempLamp.setIsColored(true);
                                        deviceList.add(tempLamp);
                                        nameList.add(strips[2]);
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
                                try {
                                    if (2000 <= Integer.parseInt(strips[4]) && Integer.parseInt(strips[4]) <= 6500) {
                                        if (0 <= Integer.parseInt(strips[5]) && Integer.parseInt(strips[5]) <= 100) {
                                            SmartColorLamp tempLamp = new SmartColorLamp(strips[2], strips[3], Integer.parseInt(strips[4]), Integer.parseInt(strips[5]));
                                            tempLamp.setIsColored(false);
                                            deviceList.add(tempLamp);
                                            nameList.add(strips[2]);
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
                        }
                        else {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                        }
                    }
                }
                else {
                    FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                }
            }
            else if (strips[1].equals("SmartCamera")) {
                if (strips.length == 4) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        try {
                            if (Double.parseDouble(strips[3]) > 0) {
                                deviceList.add(new SmartCamera(strips[2], Double.parseDouble(strips[3])));
                                nameList.add(strips[2]);
                            }
                            else {
                                FileWrite.fileWrite("ERROR: Megabyte value must be a positive number!\n", fileName);
                            }
                        }
                        catch (Exception e) {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                        }
                    }
                }
                else if (strips.length == 5) {
                    if (nameList.contains(strips[2])) {
                        FileWrite.fileWrite("ERROR: There is already a smart device with same name!\n", fileName);
                    }
                    else {
                        try {
                            if (Double.parseDouble(strips[3]) > 0) {
                                if (strips[4].equals("On") || strips[4].equals("Off")) {
                                    deviceList.add(new SmartCamera(strips[2], Double.parseDouble(strips[3]), strips[4]));
                                    nameList.add(strips[2]);
                                }
                                else {
                                    FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                                }
                            }
                            else {
                                FileWrite.fileWrite("ERROR: Megabyte value must be a positive number!\n", fileName);
                            }
                        }
                        catch (Exception e) {
                            FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                        }
                    }
                }
                else {
                    FileWrite.fileWrite("ERROR: Erroneous command!\n", fileName);
                }
            }
        }
    }
}
