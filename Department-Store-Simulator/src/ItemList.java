/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 2
 * CSE214.R30
 */
/**
 * The ItemList class represents a doubly-linked list of ItemInfoNode nodes.
 * It contains methods to insert items, remove purchased items, move items, and print lists.
 */
public class ItemList 
{
    private ItemInfoNode head;
    private ItemInfoNode tail;

    /**
     * Constructor for ItemList.
     * Initializes an empty list.
     */
    public ItemList() 
    {
        this.head = null;
        this.tail = null;
    }
    /**
     * Checks if the location is valid.
    *
    * @param location The location to be validated.
    * @return True if the location is valid, false otherwise.
    */
   private boolean isValidLocation(String location) 
   {
       return location.equals("out") || location.substring(0, 1).equals("s") || location.substring(0, 1).equals("c");
   }   

   /**
    * Validates the cart number.
    *
    * @param cartNumber The cart number to be validated.
    * @return True if the cart number is valid, false otherwise.
    */
   private boolean isValidCartNumber(String cartNumber) 
   {
       return cartNumber.substring(0, 1).equals("c");
   }

    /**
     * Inserts an item into the list in its correct position based on rfidTagNumber.
     *
     * @param name            The name of the item.
     * @param rfidTag         The RFID tag number of the item.
     * @param price           The price of the item.
     * @param originalLocation The original location of the item.
     * @throws Exceptions.IllegalArgumentException if the provided arguments are invalid.
     *
     * Time Complexity: O(n) - In the worst case, the method iterates through the entire list to find the correct position.
     */
    public void insertInfo(String name, String rfidTag, double price, String originalLocation) throws Exceptions.IllegalArgumentException 
    {
        if (name == null || name.length() > 20) 
        {
            throw new Exceptions.IllegalArgumentException("Name must be non-null and at most 20 characters.");
        }
        if (rfidTag == null || rfidTag.length() != 9 || !rfidTag.matches("[0-9A-Fa-f]+")) 
        {
            throw new Exceptions.IllegalArgumentException("RFID tag number must be 9 characters long and contain only hexadecimal characters.");
        }
        if (originalLocation == null || originalLocation.length() != 6 || !(originalLocation.startsWith("s") || originalLocation.startsWith("c"))) 
        {
            throw new Exceptions.IllegalArgumentException("Original location must be 6 characters long and start with 's' or 'c'.");
        }
        if (price < 0) 
        {
            throw new Exceptions.IllegalArgumentException("Price cannot be negative.");
        }

        ItemInfo newItem = new ItemInfo(name, rfidTag, price, originalLocation);
        ItemInfoNode newNode = new ItemInfoNode(newItem);
        if (head == null || head.getData().getRfidTag().compareTo(rfidTag) > 0) 
        {
            newNode.setNext(head);
            if (head != null) 
            {
                head.setPrev(newNode);
            }
            head = newNode;
            if (tail == null) 
            {
                tail = newNode;
            }
            return;
        }
        ItemInfoNode current = head;
        while (current.getNext() != null && current.getNext().getData().getRfidTag().compareTo(rfidTag) < 0) 
        {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        if (current.getNext() != null) 
        {
            current.getNext().setPrev(newNode);
        } 
        else 
        {
            tail = newNode;
        }
        current.setNext(newNode);
        newNode.setPrev(current);
    }

    /**
     * Removes all nodes in the list that have the current location listed as "out" and displays the removed items.
     *
     * Time Complexity: O(n) - The method iterates through the entire list to find and remove nodes.
     */
    public void removeAllPurchased() 
    {
        ItemInfoNode current = head;
        System.out.println("\nThe following item(s) have been removed from the system:\n");
        System.out.printf("%-15s%-15s%-15s%-15s%-10s%n", "Item Name", "RFID", "Original Location", "Current Location", "Price");
        System.out.println("---------       ---------     ---------        ---------   ------");

        while (current != null) 
        {
            ItemInfoNode nextNode = current.getNext();
            if (current.getData().getCurrentLocation().equals("out")) 
            {
                removeNode(current);
                System.out.printf("%-15s%-15s%-15s%-15s%-10.2f%n", current.getData().getName(), current.getData().getRfidTag(), current.getData().getOriginalLocation(), current.getData().getCurrentLocation(), current.getData().getPrice());
            }
            current = nextNode;
        }
    }

    /**
     * Moves an item with a rfidTagNumber from the original location to a destination location.
     *
     * @param rfidTag     The RFID tag number of the item.
     * @param original    The source location of the item.
     * @param destination The destination location of the item.
     * @return True if the item was found and moved, false otherwise.
     * @throws Exceptions.IllegalArgumentException if the destination is invalid, the source is "out", or inputs are invalid.
     *
     * Time Complexity: O(n) - The method iterates through the entire list to find the item.
     */
    public boolean moveItem(String rfidTag, String original, String destination) throws Exceptions.IllegalArgumentException 
    {
        if (rfidTag == null || rfidTag.length() != 9 || !rfidTag.matches("[0-9A-Fa-f]+")) 
        {
            throw new Exceptions.IllegalArgumentException("Invalid RFID tag number.");
        }
        if (original == null || original.length() < 2 || original.length() > 6 ||
            destination == null || destination.length() < 2 || destination.length() > 6) 
        {
            throw new Exceptions.IllegalArgumentException("Original and destination locations must be between 2 and 6 characters long.");
        }
        if (original.equals("out")) 
        {
            throw new Exceptions.IllegalArgumentException("Invalid original location: 'out'.");
        }
        if (!isValidLocation(destination)) 
        {
            throw new Exceptions.IllegalArgumentException("Invalid destination location.");
        }

        ItemInfoNode current = head;
        boolean itemMoved = false;

        while (current != null) 
        {
            if (current.getData().getRfidTag().equals(rfidTag) && current.getData().getCurrentLocation().equals(original)) 
            {
                current.getData().setCurrentLocation(destination);
                itemMoved = true;
                break;
            }
            current = current.getNext();
        }

        if (!itemMoved) 
        {
            System.out.println("Item not found or invalid move.");
            return false;
        }
        
        return true;
    }



    /**
     * Prints a formatted list of all items in the list.
     *
     * Time Complexity: O(n) - The method iterates through the entire list to print each item.
     */
    public void printAll() 
    {
        ItemInfoNode current = head;
        System.out.printf("%-15s%-15s%-15s%-15s%-10s%n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("---------       ---------     ---------        ---------   ------");
        while (current != null)
        {
            ItemInfo data = current.getData();
            System.out.printf("%-15s%-15s%-15s%-15s%-10.2f%n", data.getName(), data.getRfidTag(), data.getOriginalLocation(), data.getCurrentLocation(), data.getPrice());
            current = current.getNext();
        }
    }

    /**
     * Prints a formatted list of all items in the specified location.
     *
     * @param location The specified location to filter items by.
     *
     * Time Complexity: O(n) - The method iterates through the entire list to print each item in the specified location.
     */
    public void printByLocation(String location) 
    {
        ItemInfoNode current = head;
        System.out.printf("%-20s%-20s%-20s%-20s%-10s%n", "Name", "RFID Tag", "Original Location", "Current Location", "Price");
        while (current != null) 
        {
            ItemInfo data = current.getData();
            if (data.getCurrentLocation().equals(location)) 
            {
                System.out.printf("%-20s%-20s%-20s%-20s%-10.2f%n", data.getName(), data.getRfidTag(), data.getOriginalLocation(), data.getCurrentLocation(), data.getPrice());
            }
            current = current.getNext();
        }
    }

    /**
     * Takes every item that is in the store and on the wrong shelf and places it back where it belongs.
     *
     * Time Complexity: O(n) - The method iterates through the entire list to find and move misplaced items.
     */
    public void cleanStore() 
    {
        ItemInfoNode current = head;
        boolean itemsMoved = false;

        System.out.println("The following item(s) have been moved back to their original locations:");
        System.out.printf("%-15s%-15s%-15s%-15s%-10s%n", "Item Name", "RFID", "Original", "Current", "Price");
        System.out.println("---------       ---------     ---------        ---------   ------");

        while (current != null) 
        {
            ItemInfo data = current.getData();
            if (!data.getCurrentLocation().equals(data.getOriginalLocation()) && !data.getCurrentLocation().equals("out") && !data.getCurrentLocation().startsWith("c")) 
            {
                itemsMoved = true;
                System.out.printf("%-15s%-15s%-15s%-15s%-10.2f%n", data.getName(), data.getRfidTag(), data.getOriginalLocation(), data.getCurrentLocation(), data.getPrice());
                data.setCurrentLocation(data.getOriginalLocation());
            }
            current = current.getNext();
        }

        if (!itemsMoved) 
        {
            System.out.println("No items were moved.");
        }
    }

    /**
     * Goes through a cart and checks out each item (changes its location to "out").
     *
     * @param cartNumber The cart number to check out items from.
     * @return The total cost of the items that were in the cart.
     * @throws Exceptions.IllegalArgumentException if the cart number is invalid.
     *
     * Time Complexity: O(n) - The method iterates through the entire list to find and check out items in the specified cart.
     */
    public double checkOut(String cartNumber) throws Exceptions.IllegalArgumentException
    {
        if (cartNumber == null || !isValidCartNumber(cartNumber)) 
        {
            throw new Exceptions.IllegalArgumentException("Invalid cart number");
        }

        ItemInfoNode current = head;
        double totalCost = 0;
        System.out.printf("%-20s%-20s%-20s%-20s%-10s%n", "Name", "RFID Tag", "Original Location", "Current Location", "Price");
        while (current != null) 
        {
            ItemInfo data = current.getData();
            if (data.getCurrentLocation().equals(cartNumber)) 
            {
                System.out.printf("%-20s%-20s%-20s%-20s%-10.2f%n", data.getName(), data.getRfidTag(), data.getOriginalLocation(), data.getCurrentLocation(), data.getPrice());
                data.setCurrentLocation("out");
                totalCost += data.getPrice();
            }
            current = current.getNext();
        }
        return totalCost;
    }

    /**
     * Removes a specific node from the list.
     *
     * @param node The node to be removed.
     *
     * Time Complexity: O(1) - The method performs a constant-time operation to remove the node.
     */
    private void removeNode(ItemInfoNode node) 
    {
        if (node.getPrev() != null) 
        {
            node.getPrev().setNext(node.getNext());
        } 
        else 
        {
            head = node.getNext();
        }

        if (node.getNext() != null) 
        {
            node.getNext().setPrev(node.getPrev());
        }
        else 
        {
            tail = node.getPrev();
        }
    }
   

    /**
     * Prints a formatted list organized by RFID number.
     *
     * @param rfidTag The RFID tag number to filter items by.
     *
     * Time Complexity: O(n) - The method iterates through the entire list to print each item with the specified RFID tag.
     */
    public void printByRFID(String rfidTag) 
    {
        ItemInfoNode current = head;
        System.out.printf("%-20s%-20s%-20s%-20s%-10s%n", "Name", "RFID Tag", "Original Location", "Current Location", "Price");
        while (current != null) 
        {
            ItemInfo data = current.getData();
            if (data.getRfidTag().equals(rfidTag)) 
            {
                System.out.printf("%-20s%-20s%-20s%-20s%-10.2f%n", data.getName(), data.getRfidTag(), data.getOriginalLocation(), data.getCurrentLocation(), data.getPrice());
            }
            current = current.getNext();
        }
    }
}