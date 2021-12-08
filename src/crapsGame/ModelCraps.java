package crapsGame;

/**
 * This class apply craps rules.
 * status = 1 Natural winner
 * status = 2 Craps loser
 * status = 3 set point
 * status = 4 winner point
 * status = 5 loser point
 * @author Deisy C. Melo 202041790 deisy.melo@correounivalle.edu.co
 * @version V.1.0.0 date 7/12/2021
 */
public class ModelCraps
{
    private Die die1, die2;
    private int throw0, record, status, flag;
    private String[] statusToString;
    private int[] sides;

    /**
     * Class Constructor
     */
    public ModelCraps()
    {
        die1  = new Die();
        die2  = new Die();
        sides = new int [2];
        statusToString = new String[2];
        flag  = 0;

    }

    /**
     * Sets the throw value according to each dice.
     */
    public void calculateThrow ()
    {
        sides[0] = die1.getSide();
        sides[1] = die2.getSide();
        throw0 = sides[0] + sides[1];
    }

    /**
     * Sets the state game according to the value of the status attribute.
     * status = 1 Natural winner
     * status = 2 Craps loser
     * status = 3 set point
     */
    public void score()
    {
        if(flag==0){
            if(throw0==7 || throw0 ==11){
                status = 1;
            }
            else if (throw0==3 || throw0 == 2 || throw0 == 12) {
                status = 2;
            }
            else {
                status=3;
                record=throw0;
                flag=1;
            }
        }
        else{
            pointRound();
        }


    }

    /**
     * Sets the status game according to the value of the status attribute.
     * status = 4 winner point
     * status = 5 loser point
     */
    private void pointRound()
    {
        if (throw0 == record){
            status=4;
            flag=0;
        }
        else if(throw0==7){
            status=5;
            flag=0;
        }
        else{
            status=6;
        }

    }

    /**
     * Sets the message according to the value of the status attribute.
     * @return message for the view class
     */
    public String[] getStatusToString() {
        switch (status){
            case 1: statusToString[0] = " Output throw = "+throw0;
                    statusToString[1] = " It's NATURAL! You won!!";
                    break;
            case 2: statusToString[0] = " Output throw = "+throw0;
                    statusToString[1] = " I'm sorry, it's craps... You lost";
                    break;
            case 3: statusToString[0] = " Output throw = "+throw0+"\n Point = "+record;
                    statusToString[1]=
                    " You set a point in " +record+ ", keep throwing"+ "\n But if you get 7 before "+record+
                            " you will lose";
                    break;
            case 4: statusToString[0] = " Output throw = "+record+"\n Point = "+record+
                    "\n The value of the new throw is "+throw0;
                    statusToString[1] = " You got "+record+" again, you won!!";
                    break;
            case 5: statusToString[0] = " Output throw = "+record+"\n Point = "+ record+
                    "\n Value of the new throw = "+throw0;
                    statusToString[1] = " You got 7 before "+record+", you lost";
                    break;
            case 6: statusToString[0] = " Output throw = "+record+" \n Point = "+record+
                                        "\n Value of the new throw = "+throw0;
                    statusToString[1] =
                            "\n You're on point and you need to keep throwing"+ "\n But if you get 7 before "+record+
                            " you will lose";
        }
        return statusToString;
    }

    public int getThrow0() {
        return throw0;
    }

    public int getRecord() {
        return record;
    }

    public int[] getSides() {
        return sides;
    }
}

