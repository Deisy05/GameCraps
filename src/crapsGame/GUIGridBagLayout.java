package crapsGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {
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
    private JButton throw1, help, exit;
    private JPanel dicePanel;
    private ImageIcon imageDie, imageHelp, imageExit, imageThrow;
    private JTextArea outputMessage, results;
    private Escucha escucha;
    private ModelCraps modelCraps;


    public GUIGridBagLayout(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("CRAPS GAME");
        this.setUndecorated(true);
        this.setBackground(new Color(186,219,234,150));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("TABLE CRAPS GAME", new Color(146,81,142));
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;

        this.add(headerProject, constraints);

        help = new JButton();
        help.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        imageHelp = new ImageIcon(getClass().getResource("/resources/helpp.png"));
        help.setIcon(new ImageIcon(imageHelp.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH)));

        help.setBorderPainted(false);
        help.setFocusPainted(false);
        help.setContentAreaFilled(false);

        this.add(help,constraints);

        exit = new JButton();
        exit.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        imageExit = new ImageIcon(getClass().getResource("/resources/close.png"));
        exit.setIcon(new ImageIcon(imageExit.getImage().getScaledInstance(30,25, Image.SCALE_SMOOTH)));

        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);

        this.add(exit,constraints);


        imageDie = new ImageIcon(getClass().getResource("/resources/dado.png"));
        die1 = new JLabel(imageDie);
        die2 = new JLabel(imageDie);

        dicePanel = new JPanel();
        dicePanel.setPreferredSize(new Dimension(300, 180));
        dicePanel.setBorder(BorderFactory.createTitledBorder("Your dice"));
        dicePanel.setOpaque(false);
        dicePanel.add(die1);
        dicePanel.add(die2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;

        add(dicePanel, constraints);

        results = new JTextArea(4,31);
        results.setBorder(BorderFactory.createTitledBorder("Results"));
        results.setText(" You need to throw the dice");
        results.setOpaque(false);
        results.setEditable(false);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;

        add(results, constraints);

        throw1 = new JButton();
        throw1.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        imageThrow = new ImageIcon(getClass().getResource("/resources/throw-icon.png"));
        throw1.setIcon(new ImageIcon(imageThrow.getImage().getScaledInstance(60,25, Image.SCALE_SMOOTH)));

        throw1.setBorderPainted(false);
        throw1.setFocusPainted(false);
        throw1.setContentAreaFilled(false);

        this.add(throw1,constraints);

        outputMessage = new JTextArea(4, 31);
        outputMessage.setText(" Use the help button to view the game instructions ");
        outputMessage.setBorder(BorderFactory.createTitledBorder("Messages"));
        outputMessage.setOpaque(false);
        outputMessage.setEditable(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;

        this.add(outputMessage,constraints);

    }


    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==throw1){
                modelCraps.calculateThrow();
                int[] sides = modelCraps.getSides();
                imageDie = new ImageIcon(getClass().getResource("/resources/" + sides[0] + ".png"));
                die1.setIcon(imageDie);
                imageDie = new ImageIcon(getClass().getResource("/resources/" + sides[1] + ".png"));
                die2.setIcon(imageDie);
                modelCraps.score();
                results.setText(modelCraps.getStatusToString()[0]);
                outputMessage.setText(modelCraps.getStatusToString()[1]);
            }
            else if(e.getSource()==help){
                JOptionPane.showMessageDialog(null,MESSAGE);
            }
            else{
                System.exit(0);
            }
        }
    }
}
