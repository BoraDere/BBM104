import java.time.LocalDateTime;
import java.util.Locale;

/**
 * This class has the methods and variables for SmartColorLamp device.
 */
public class SmartColorLamp extends SmartDevice {
    public int kelvin = 4000, brightness = 100;
    public LocalDateTime switchTime = null;
    public String hexCode;
    public boolean isColored = false;

    /**
     * Constructor with 1 parameter.
     * @param name is the device's name.
     */
    SmartColorLamp(String name) {
        super(name);
    }

    /**
     * Constructor with 2 parameters.
     * @param name is the device's name.
     * @param status is the status of the device.
     */
    SmartColorLamp(String name, String status) {
        super(name, status);
    }

    /**
     * Constructor with 4 parameters.
     * @param name is the device's name.
     * @param status is the status of the device.
     * @param kelvin is the kelvin value of the device.
     * @param brightness is the brightness value of the device.
     */
    SmartColorLamp(String name, String status, int kelvin, int brightness) {
        super(name, status);
        this.kelvin = kelvin;
        this.brightness = brightness;
    }

    /**
     * Constructor with 4 parameters.
     * @param name is the device's name.
     * @param status is the status of the device.
     * @param hexCode  is the color value of the device.
     * @param brightness is the brightness value of the device.
     */
    SmartColorLamp(String name, String status, String hexCode, int brightness) {
        super(name, status);
        this.hexCode = hexCode;
        this.brightness = brightness;
    }

    /**
     * Brightness getter.
     * @return brightness.
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * Kelvin getter.
     * @return kelvin.
     */
    public int getKelvin() {
        return kelvin;
    }

    /**
     * Color value getter.
     * @return hexCode.
     */
    public String getHexCode() {
        return hexCode;
    }

    /**
     * isColored getter.
     * @return if it isColored.
     */
    @Override
    public boolean getIsColored() {
        return isColored;
    }

    /**
     * Kelvin setter.
     * @param kelvin is the kelvin value of the device.
     */
    @Override
    public void setKelvin(int kelvin) {
        this.kelvin = kelvin;
    }

    /**
     * Brightness setter.
     * @param brightness is the brightness value of the device.
     */
    @Override
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    /**
     * Color value setter.
     * @param hexCode is the color value of the device.
     */
    @Override
    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    /**
     * isColored setter.
     * @param isColored is the bool colored value.
     */
    @Override
    public void setIsColored(boolean isColored) {
        this.isColored = isColored;
    }

    /**
     * SwitchTime setter.
     * @param switchTime is the switch time of the device.
     */
    @Override
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }

    /**
     * SwitchTime getter.
     * @return switch time.
     */
    @Override
    public LocalDateTime getSwitchTime() {
        return switchTime;
    }

    /**
     * toString overrider.
     * @return a String value to print.
     */
    @Override
    public String toString() {
        if (!getIsColored()) {
            return String.format("Smart Color Lamp %s is %s and its color value is %dK with %d", getName(),
                    getStatus().toLowerCase(Locale.ROOT), getKelvin(), getBrightness()) + "% brightness, and its time to " +
                    "switch its status is " + dateFormatter.getFormattedDate(getSwitchTime()) + ".";
        }
        else {
            return String.format("Smart Color Lamp %s is %s and its color value is %s with %d", getName(),
                    getStatus().toLowerCase(Locale.ROOT), getHexCode(), getBrightness()) + "% brightness, and its time to " +
                    "switch its status is " + dateFormatter.getFormattedDate(getSwitchTime()) + ".";
        }
    }
}
