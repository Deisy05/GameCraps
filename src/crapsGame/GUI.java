package crapsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as view Craps Class
 * @autor Deisy C. Melo 202041790 deisy.melo@correounivalle.edu.co
 * @version V.1.0.0 date:07/12/2021
 */
public class GUI extends JFrame {

    private static final String MESSAGE = " WELCOME TO CRAPS \n"
            + " Press the throw button to start the game"
            + "\n If you starting throw is 7 or 11 you will win with Natural"
            + "\n If you starting throw is 2, 3 or 12 you will lose with Craps"
            + "\n If you get any other value you will set the point"
            + "\n State on the point you can continue to throw the dice"
            + "\n but now you will win if you get the value of the point again"
            + "\n without you having previously got 7";


    private Header headerProject;
    private JLabel die1, die2;
    private JButton throw1;
    private JPanel dicePanel, resultsPanel;
    private ImageIcon imageDie;
    private JTextArea outputMessage, results;
    private JSeparator separator;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private int flag;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();
        flag=0;

        //Default JFrame configuration
        this.setTitle("CRAPS GAME");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("TABLE CRAPS GAME", new Color(146,81,142));
        this.add(headerProject, BorderLayout.NORTH);

        imageDie = new ImageIcon(getClass().getResource("/resources/dado.png"));
        die1 = new JLabel(imageDie);
        die2 = new JLabel(imageDie);

        throw1 = new JButton("Throw");
        throw1.addActionListener(escucha);

        dicePanel = new JPanel();
        dicePanel.setPreferredSize(new Dimension(300, 180));
        dicePanel.setBorder(BorderFactory.createTitledBorder("Your dice"));
        dicePanel.add(die1);
        dicePanel.add(die2);
        dicePanel.add(throw1);

        this.add(dicePanel, BorderLayout.CENTER);

        outputMessage = new JTextArea(7, 31);
        outputMessage.setText(MESSAGE);
        JScrollPane scroll = new JScrollPane(outputMessage);

        resultsPanel = new JPanel();
        resultsPanel.setBorder(BorderFactory.createTitledBorder("What you should do"));
        resultsPanel.add(scroll);
        resultsPanel.setPreferredSize(new Dimension(370,180));


        this.add(resultsPanel, BorderLayout.EAST);

        results = new JTextArea(4,31);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(320,7));
        separator.setBackground(Color.MAGENTA);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calculateThrow();
            int[] sides = modelCraps.getSides();
            imageDie = new ImageIcon(getClass().getResource("/resources/"+sides[0]+".png"));
            die1.setIcon(imageDie);
            imageDie = new ImageIcon(getClass().getResource("/resources/"+sides[1]+".png"));
            die2.setIcon(imageDie);

            modelCraps.score();

            if(flag==0){
                resultsPanel.removeAll();
                resultsPanel.setBorder(BorderFactory.createTitledBorder("Results "));
                resultsPanel.add(results);
                resultsPanel.add(separator);
                resultsPanel.add(outputMessage);
                flag=1;
            }
            results.setText(modelCraps.getStatusToString()[0]);
            outputMessage.setRows(4);
            outputMessage.setText(modelCraps.getStatusToString()[1]);

            revalidate();
            repaint();
        }
    }
}
