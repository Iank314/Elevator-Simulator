/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 2
 * CSE214.R30
 */
/**
 * The ItemInfoNode class represents a node in the double-linked list of items.
 */
class ItemInfoNode 
{
    private ItemInfo data;
    private ItemInfoNode next;
    private ItemInfoNode prev;

    /**
     * Constructor for ItemInfoNode.
     *
     * @param setData The ItemInfo object to be stored in this node.
     * @throws Exceptions.IllegalArgumentException if setInfo is null.
     */
    public ItemInfoNode(ItemInfo setData) throws Exceptions.IllegalArgumentException 
    {
        if (setData == null) 
        {
            throw new Exceptions.IllegalArgumentException("ItemInfo cannot be null");
        }
        this.data = setData;
        this.next = null;
        this.prev = null;
    }

    /**
     * Sets the ItemInfo object for this node.
     *
     * @param data The ItemInfo object to be stored in this node.
     */
    public void setData(ItemInfo data) 
    {
        this.data = data;
    }

    /**
     * Gets the ItemInfo object stored in this node.
     *
     * @return The ItemInfo object stored in this node.
     */
    public ItemInfo getData() 
    {
        return data;
    }

    /**
     * Sets the next node in the list.
     *
     * @param next The next node in the list.
     */
    public void setNext(ItemInfoNode next) 
    {
        this.next = next;
    }

    /**
     * Gets the next node in the list.
     *
     * @return The next node in the list.
     */
    public ItemInfoNode getNext() 
    {
        return next;
    }

    /**
     * Sets the previous node in the list.
     *
     * @param prev The previous node in the list.
     */
    public void setPrev(ItemInfoNode prev) 
    {
        this.prev = prev;
    }

    /**
     * Gets the previous node in the list.
     *
     * @return The previous node in the list.
     */
    public ItemInfoNode getPrev() 
    {
        return prev;
    }
}