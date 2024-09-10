/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 2
 * CSE214.R30
 */
/**
 * The ItemInfo class represents the information of an item in the store.
 */
class ItemInfo 
{
    private String name;
    private String rfidTag;
    private double price;
    private String originalLocation;
    private String currentLocation;

    /**
     * Constructor for ItemInfo.
     *
     * @param name         The name of the item.
     * @param rfidTag      The RFID tag number of the item.
     * @param price        The price of the item.
     * @param originalLocation The original location of the item.
     */
    public ItemInfo(String name, String rfidTag, double price, String originalLocation) 
    {
        this.name = name;
        this.rfidTag = rfidTag;
        this.price = price;
        this.originalLocation = originalLocation;
        this.currentLocation = originalLocation;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Gets the RFID tag number of the item.
     *
     * @return The RFID tag number of the item.
     */
    public String getRfidTag() 
    {
        return rfidTag;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    public double getPrice() 
    {
        return price;
    }

    /**
     * Gets the original location of the item.
     *
     * @return The original location of the item.
     */
    public String getOriginalLocation() 
    {
        return originalLocation;
    }

    /**
     * Gets the current location of the item.
     *
     * @return The current location of the item.
     */
    public String getCurrentLocation() 
    {
        return currentLocation;
    }

    /**
     * Sets the current location of the item.
     *
     * @param currentLocation The new current location of the item.
     */
    public void setCurrentLocation(String currentLocation) 
    {
        this.currentLocation = currentLocation;
    }
}