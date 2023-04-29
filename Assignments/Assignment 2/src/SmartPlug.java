import java.time.LocalDateTime;
import java.util.Locale;

/**
 * This class has the methods and variables for SmartPlug device.
 */
public class SmartPlug extends SmartDevice {
    public double consumption = 0;
    public double tempConsumption = 0;
    public int voltage = 220; // I added this because you specified it as "default" which made me think that it can be changed
                              // but there is no command or input to change it. But I keep it anyway, because of this confusion.
    public double ampere;
    public LocalDateTime switchTime = null;
    public boolean plugged;

    /**
     * Constructor with 1 parameter.
     * @param name is the device's name.
     */
    SmartPlug(String name) {
        super(name);
        plugged = false;
    }

    /**
     * Constructor with 2 parameters.
     * @param name is the device's name.
     * @param status is the status of the device.
     */
    SmartPlug(String name, String status) {
        super(name, status);
        plugged = false;
    }

    /**
     * Constructor with 3 parameters.
     * @param name is the device's name.
     * @param status is the status of the device.
     * @param ampere is the ampere value.
     */
    SmartPlug(String name, String status, double ampere) {
        super(name, status);
        this.ampere = ampere;
        plugged = true;
    }

    /**
     * Consumption getter.
     * @return consumption.
     */
    @Override
    public double getConsumption() {
        return consumption;
    }

    /**
     * tempConsumption getter.
     * @return tempConsumption.
     */
    @Override
    public double getTempConsumption() {
        return tempConsumption;
    }

    /**
     * tempConsumption setter.
     * @param tempConsumption is the temporary consumption value.
     */
    @Override
    public void setTempConsumption(double tempConsumption) {
        this.tempConsumption = tempConsumption;
    }

    /**
     * Ampere getter.
     * @return ampere.
     */
    @Override
    public double getAmpere() {
        return ampere;
    }

    /**
     * Consumption setter.
     * @param consumption is the consumption value of the device.
     */
    @Override
    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    /**
     * Ampere setter.
     * @param ampere is the ampere value.
     */
    @Override
    public void setAmpere(double ampere) {
        this.ampere = ampere;
    }

    /**
     * Plugged setter.
     * @param plugged states if the plug is plugged.
     */
    @Override
    public void setPlugged(boolean plugged) {
        this.plugged = plugged;
    }

    /**
     * Plugged getter.
     * @return plugged.
     */
    @Override
    public boolean isPlugged() {
        return plugged;
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
        return String.format("Smart Plug %s is %s and consumed ", getName(), getStatus().toLowerCase(Locale.ROOT)) +
                String.format("%.2fW so far (excluding current device), and its time to switch its status is ",
                        getConsumption()).replace(".", ",") + dateFormatter.getFormattedDate(getSwitchTime()) + ".";
    }
}
