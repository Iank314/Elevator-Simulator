/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 3
 * CSE214.R30
 */
import java.util.Random;

/**
 * The BooleanSource class simulates a random boolean value generator based on a given probability.
 */
public class BooleanSource 
{
    private double probability;
    private Random random;

    /**
     * Constructs a BooleanSource object with the specified probability.
     *
     * @param probability the probability of returning true in the requestArrived method
     * @throws Exceptions.IllegalArgumentException if the probability is not between 0.0 and 1.0 inclusive
     */
    public BooleanSource(double probability) throws Exceptions.IllegalArgumentException
    {
        if (probability < 0.0 || probability > 1.0) 
        {
            throw new Exceptions.IllegalArgumentException("Probability must be between 0.0 and 1.0.");
        }
        this.probability = probability;
        this.random = new Random();
    }

    /**
     * Returns true based on the specified probability.
     *
     * @return true if a random value is less than the probability, false otherwise
     */
    public boolean requestArrived() 
    {
        return random.nextDouble() < probability;
    }

    /**
     * Returns the probability of returning true.
     *
     * @return the probability
     */
    public double getProbability() 
    {
        return probability;
    }

    /**
     * Sets the probability of returning true.
     *
     * @param probability the new probability
     * @throws Excepions.IllegalArgumentException if the probability is not between 0.0 and 1.0 inclusive
     */
    public void setProbability(double probability) throws Exceptions.IllegalArgumentException
    {
        if (probability < 0.0 || probability > 1.0) 
        {
            throw new Exceptions.IllegalArgumentException("Probability must be between 0.0 and 1.0.");
        }
        this.probability = probability;
    }
}