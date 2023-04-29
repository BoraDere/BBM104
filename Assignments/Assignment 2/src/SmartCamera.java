import java.time.LocalDateTime;
import java.util.Locale;

/**
 * This class has the methods and variables for SmartCamera device.
 */
public class SmartCamera extends SmartDevice {
    public LocalDateTime switchTime = null;
    public double tempConsumption = 0;
    public double megabytes;
    public double consumption = 0;

    /**
     * Constructor with 2 parameters.
     * @param name is the device's name.
     * @param megabytes is the camera's megabytes per minute consumption value.
     */
    SmartCamera(String name, double megabytes) {
        super(name);
        this.megabytes = megabytes;
    }

    /**
     * Constructor with 3 parameters.
     * @param name is the device's name.
     * @param megabytes is the camera's megabytes per minute consumption value.
     * @param status is the status of the device.
     */
    SmartCamera(String name, double megabytes, String status) {
        super(name, status);
        this.megabytes = megabytes;
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
     * Megabytes getter.
     * @return megabytes.
     */
    @Override
    public double getMegabytes() {
        return megabytes;
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
        return String.format("Smart Camera %s is %s and used ", getName(), getStatus().toLowerCase(Locale.ROOT)) +
                String.format("%.2f MB of storage so far (excluding current status), and its time to switch its status is ",
                        getConsumption()).replace(".", ",") + dateFormatter.getFormattedDate(getSwitchTime()) + ".";
    }
}
