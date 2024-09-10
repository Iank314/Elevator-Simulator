/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 2
 * CSE214.R30
 */
public class Exceptions 
{
	/**
     * Exception thrown when the playlist is full.
     * @precondition None.
     * @postcondition An exception with a specified message is created.
     */
    public static class IllegalArgumentException extends Exception 
    {
        /**
         * Constructs a FullPlaylistException with a specified message.
         * @param message The detail message.
         */
        public IllegalArgumentException(String message) 
        {
            super(message);
        }
    }
}
