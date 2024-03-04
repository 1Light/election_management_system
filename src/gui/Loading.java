package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Loading {
    JFrame fr;
    JLabel lb, jt;
    JProgressBar pb;

    ImageIcon ii;
    javax.swing.Timer timer;

    Loading () {
        // Creating a frame
        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0,128,255);
        Color var3 = new Color(0, 204, 102);

        fr = new JFrame("Loading Page");

        pb = new JProgressBar();
        pb.setBackground(Color.BLUE);
        pb.setBounds(234, 415, 120,  25);
        pb.setBackground(var3);

        int[] value = { 0 };
        timer = new Timer(80, e -> {
            pb.setValue(value[0]++);
            if (value[0] > 100) {
                timer.stop();
                fr.dispose();
                new Main();
            }
        });
        timer.start();

        // Creating text field
        jt = new JLabel("Loading ByteBallot");
        jt.setBounds(0,0,600, 50);
        jt.setBackground(var);
        jt.setForeground(Color.BLACK);

        fr.add(pb);
        fr.add(jt);

        // Setting the font size on the text field
        Font font = new Font("TIMES NEW ROMAN", Font.BOLD, 30);
        jt.setFont(font);
        jt.setHorizontalAlignment(JTextField.CENTER);

        ii = Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\loading.jpeg", 588, 517);
        lb = new JLabel(ii);
        lb.setBounds(0, 0, 588, 516);

        lb.setBorder(new LineBorder(var2, 4));
        fr.add(lb);

        fr.setSize(600, 550);
        fr.getContentPane().setBackground(var);
        fr.setLocationRelativeTo(null);
        fr.setLayout(null);
        fr.setVisible(true);

    }
    public ImageIcon Resize(String imagePath, int width, int height) {
        System.out.println("Image Path: " + imagePath);
        try {
            // Load the original image
            BufferedImage originalImage = ImageIO.read(new File(imagePath));

            // Resize the image while maintaining its aspect ratio
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Create a button with the resized image
            return new ImageIcon(resizedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void main (String [] args) {
        new Loading();
    }
}
