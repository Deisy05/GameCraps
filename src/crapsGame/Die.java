package crapsGame;

import java.util.Random;

/**
 * This class generates a random value between 1 and 6.
 * @autor Deisy C. Melo 202041790 deisy.melo@correo.edu.co
 * @version v.1.0.0 date 07/12/2021
 */

public class Die {
    private int side;

    /**
     * Method that generate a random value to side
     * @return number between (1,6)
     */

    public int getSide()
    {
        Random random = new Random();
        side = random.nextInt(6)+1;

        return side;
    }


}
