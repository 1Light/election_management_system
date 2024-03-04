package gui;

import database.Candidate;
import database.Linker;
import database.Voter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class VoterCand {
    JFrame jf;
    JButton jb;
    private List<String> candidateNames;

    public VoterCand (String position) {
        jf = new JFrame("Voter Dashboard");

        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0,128,255);

        jb = new JButton("Back");
        jb.setBackground(var2);
        jb.setForeground(Color.WHITE);
        jb.setFont(font2);
        jb.setBounds(250, 400, 100, 25);
        jb.addActionListener(e-> {
            new VoterCandPos();
            jf.dispose();
        });

        List<Candidate> candidates = Linker.getAllCandidateForPosition(position);
        int x = 50;

        // Inside the for loop
        for (Candidate candidate : candidates) {
            JLabel label = new JLabel(candidate.getFullName());
            label.setBounds(x, 150, 100, 25);
            jf.add(label);
            label.setFont(font2);
            label.setForeground(Color.WHITE);

            // Check if imagePath is not null before calling Resize
            if (candidate.getImagePath() != null) {
                JButton button = new JButton(Resize(candidate.getImagePath(), 100, 100));
                button.setBounds(x, 50, 100, 100);

                button.addActionListener(e -> {
                    //Candidate loggedInCandidate = (Candidate) Linker.getLoggedInCandidate();
                    //if(loggedInCandidate != null) {
                        new VoterCanPro(candidate);
                        jf.dispose();
                });

                jf.add(button);
            } else {
                System.err.println("Image path is null for candidate: " + candidate.getFullName());
            }

            x += 150;
        }

        jf.add(jb);

        // Finalizing input
        jf.setSize(600, 550);
        jf.getContentPane().setBackground(var);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.setVisible(true);

    }
    // Method for resizing images
    public ImageIcon Resize(String imagePath, int width, int height) {
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
    public static void main(String args []){
    }
}
