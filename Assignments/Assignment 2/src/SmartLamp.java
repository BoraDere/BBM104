import java.time.LocalDateTime;
import java.util.Locale;

/**
 * This class has the methods and variables for SmartLamp device.
 */
public class SmartLamp extends SmartDevice {
    public int kelvin = 4000, brightness = 100;
    public LocalDateTime switchTime = null;

    /**
     * Constructor with 1 parameter.
     * @param name is the device's name.
     */
    SmartLamp(String name) {
        super(name);
    }

    /**
     * Constructor with 2 parameters.
     * @param name is the device's name.
     * @param status is the status of the device.
     */
    SmartLamp(String name, String status) {
        super(name, status);
    }

    /**
     * Constructor with 4 parameters.
     * @param name is the device's name.
     * @param status is the status of the device.
     * @param kelvin is the kelvin value of the device.
     * @param brightness is the brightness value of the device.
     */
    SmartLamp(String name, String status, int kelvin, int brightness) {
        super(name, status);
        this.kelvin = kelvin;
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
        return String.format("Smart Lamp %s is %s and its kelvin value is %dK with %d", getName(),
                getStatus().toLowerCase(Locale.ROOT), getKelvin(), getBrightness()) + "% brightness, and its time to " +
                "switch its status is " + dateFormatter.getFormattedDate(getSwitchTime()) + ".";
    }
}
