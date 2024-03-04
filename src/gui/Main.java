package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    JFrame jf;
    JLabel jt;
    JComboBox jx;
    JSeparator line;
    JButton jb, jb2;
    JLabel lb;
    ImageIcon ii;

    public Main() {
        // Accessories
        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0, 128, 255);
        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 14);

        // JSeparator for the horizontal line
        line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setBounds(65, 50, 455, 5);
        line.setBorder(new LineBorder(var2, 2));
        line.setBackground(var2);

        jf = new JFrame("Online Voting System");
        jf.getContentPane().setBackground(Color.WHITE);

        // Creating text field
        jt = new JLabel("Elections Management System");
        jt.setBounds(0, 0, 588, 50);
        jt.setForeground(Color.BLACK);
        jt.setBackground(var);

        // Setting the font size on the text field
        Font font = new Font("TIMES NEW ROMAN", Font.BOLD, 30);
        jt.setFont(font);
        jt.setHorizontalAlignment(JLabel.CENTER);

        // Creating the combo box
        String[] members = {"Admin", "Voter", "Candidate"};
        jx = new JComboBox<>(members);
        jx.setBounds(405, 73, 150, 25);
        jx.setBackground(Color.WHITE);
        jx.setForeground(Color.WHITE);

        // Resizing and setting image
        ii = Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\img.jpg", 435, 255);
        lb = new JLabel(ii);
        lb.setBounds(73, 120, 438, 255);
        lb.setBorder(new LineBorder(var2, 4));

        // Creating the "Sign In" button
        jb = new JButton("Sign In");
        jb.setBounds(220, 407, 150, 25);
        jb.setForeground(Color.WHITE);

        // Adding action to "Sign In" button
        jb.addActionListener(e -> {
            // Getting category from the JComboBox
            String choice = (String) jx.getSelectedItem();
            if ("Voter".equals(choice) || "Admin".equals(choice) || "Candidate".equals(choice)) {
                new SignIn(choice);
                jf.dispose();
            }
        });

        // Creating the "Sign Up" button
        jb2 = new JButton("Sign Up");
        jb2.setBounds(220, 460, 150, 25);
        jb2.setForeground(Color.WHITE);

        // Adding action to "Sign Up" button
        jb2.addActionListener(e -> {
            String choice = (String) jx.getSelectedItem();
            if ("Voter".equals(choice)) {
                new SignUpV();
                jf.dispose();
            } else if ("Candidate".equals(choice)) {
                new SignUpC();
                jf.dispose();
            }
        });

        // Styling buttons and combo box
        jb.setFont(font2);
        jb.setBackground(var2);
        jb2.setFont(font2);
        jb2.setBackground(var2);
        jx.setFont(font2);
        jx.setBackground(var2);

        // Adding components to the frame
        jf.add(jt);
        jf.add(jx);
        jf.add(jb);
        jf.add(jb2);
        jf.add(lb);
        jf.add(jb);
        jf.add(line);

        // Finalizing frame settings
        jf.setSize(600, 550);
        jf.getContentPane().setBackground(var);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.setVisible(true);
    }

    // Method to resize an image
    public ImageIcon Resize(String imagePath, int width, int height) {
        try {
            // Load the original image
            BufferedImage originalImage = ImageIO.read(new File(imagePath));

            // Resize the image while maintaining its aspect ratio
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Create an ImageIcon with the resized image
            return new ImageIcon(resizedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Main method to run the application
    public static void main(String args[]) {
        new Main();
    }
}
