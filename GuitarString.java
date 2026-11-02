/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    RingBuffer ring;
    private int ticTime = 0;
    public GuitarString(double frequency) 
    {
        ring = new RingBuffer((int)(44100/frequency));
    }

    public GuitarString(double[] array) 
    {
        ring = new RingBuffer((int)(array.length));
        for(int i = 0; i < array.length; i++)
        {
            ring.add(array[i]);
        }
    }

    public void pluck() 
    {
        for(int i = 0; i < ring.size(); i++)
        {
            ring.add(Math.random() - .5);
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        double first = ring.remove();
        ring.add((first + ring.peek())/2 * .994 );
        ticTime++;
    }

    // return the current sample
    public double sample() 
    {
        return ring.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return ticTime;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
