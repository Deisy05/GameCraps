package crapsGame;

import javax.swing.*;
import java.awt.*;

/**
 * Create a personalized Header for the project
 * @autor Deisy C. Melo 202041790 deisy.melo@correounivalle.edu.co
 * @version V.1.0.0 date:07/12/2021
 */
public class Header extends JLabel {
    /**
     * Constructor of the Header class
     * @param title String that contains Header text
     * @param colorBackground Color object to be assigned for the Header background
     */
    public Header(String title, Color colorBackground){
        this.setText(title);
        this.setBackground(colorBackground);
        this.setForeground(new Color(201,186,234));
        this.setFont(new Font( null, Font.BOLD,20 ));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);
    }
}
