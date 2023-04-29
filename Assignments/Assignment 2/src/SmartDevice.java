import java.time.LocalDateTime;

/**
 * Abstract class so all smart devices has a shared superclass which we can do and store methods, variables...
 */
abstract class SmartDevice {
    protected String name;
    protected String status;
    protected LocalDateTime switchTime;
    private boolean plugged;
    private boolean isColored;
    private double ampere;
    private double consumption;
    private double megabytes;
    public DateFormatter dateFormatter = new DateFormatter("yyyy[-M][-d]_HH:mm:ss");
    private double tempConsumption;

    /**
     * Constructor with 1 parameter.
     * @param name is the device's name.
     */
    public SmartDevice(String name) {
        this(name, "Off");
    }

    /**
     * Constructor with 2 parameters.
     * @param name is the device's name.
     * @param status is the status of the device.
     */
    public SmartDevice(String name, String status) {
        this.name = name;
        this.status = status;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Name setter.
     * @param name is the name of the device.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Plugged setter.
     * @param plugged states if the plug is plugged.
     */
    public void setPlugged(boolean plugged) {
        this.plugged = plugged;
    }

    /**
     * Ampere setter.
     * @param ampere is the ampere value.
     */
    public void setAmpere(double ampere) {

    }

    /**
     * isColored getter.
     * @return if it isColored.
     */
    public boolean getIsColored() {
        return isColored;
    }

    /**
     * Plugged getter.
     * @return plugged.
     */
    public boolean isPlugged() {
        return plugged;
    }

    /**
     * Status getter.
     * @return status.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Kelvin setter.
     * @param kelvin is the kelvin value of the device.
     */
    public void setKelvin(int kelvin) {

    }

    /**
     * Brightness setter.
     * @param brightness is the brightness value.
     */
    public void setBrightness(int brightness) {

    }

    /**
     * Color value setter.
     * @param hexCode is the color value of the device.
     */
    public void setHexCode(String hexCode) {

    }

    /**
     * Megabytes getter.
     * @return megabytes.
     */
    public double getMegabytes() {
        return megabytes;
    }

    /**
     * Status setter.
     * @param status is the status of the device.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * SwitchTime setter.
     * @param switchTime is the switch time of the device.
     */
    public void setSwitchTime(LocalDateTime switchTime) {
        this.switchTime = switchTime;
    }

    /**
     * isColored setter.
     * @param isColored is the bool colored value.
     */
    public void setIsColored(boolean isColored) {
        this.isColored = isColored;
    }

    /**
     * SwitchTime getter.
     * @return switch time.
     */
    public LocalDateTime getSwitchTime() {
        return switchTime;
    }


    /**
     * Consumption setter.
     * @param consumption is the consumption value of the device.
     */
    public void setConsumption(double consumption) {

    }

    /**
     * Ampere getter.
     * @return ampere.
     */
    public double getAmpere() {
        return ampere;
    }

    /**
     * tempConsumption getter.
     * @return tempConsumption.
     */
    public double getTempConsumption() {
        return tempConsumption;
    }

    /**
     * tempConsumption setter.
     * @param tempConsumption is the temporary consumption value.
     */
    public void setTempConsumption(double tempConsumption) {

    }

    /**
     * Consumption getter.
     * @return consumption.
     */
    public double getConsumption() {
        return consumption;
    }
}
