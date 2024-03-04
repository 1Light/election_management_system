package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;
import database.Linker;

public class ResultDash {

    JFrame jf;
    JPanel jp;
    JLabel jl, jl2, jl3, jl4;
    ImageIcon ii;
    JButton jb, jb2, jb3, jb4;
    private List<String> candidateNames;
    private List<Integer> candidateVotes;

    public ResultDash() {
        jf = new JFrame("Voter Dashboard");


        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0,128,255);

        ii = Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\votercandpos.jpeg", 435, 245);
        jl4 = new JLabel(ii);
        jl4.setBounds(75, 20, 435, 245);

        jl4.setBorder(new LineBorder(var2, 2));

        // Creating labels
        jl = new JLabel("President");
        jl.setBounds(50, 400, 150, 25);
        jl.setFont(font2);
        jl.setBackground(var2);
        jl.setForeground(Color.WHITE);
        jl2 = new JLabel("Prime Minister");
        jl2.setBounds(240, 400, 150, 25);
        jl2.setFont(font2);
        jl2.setBackground(var2);
        jl2.setForeground(Color.WHITE);
        jl3 = new JLabel("Senator");
        jl3.setBounds(440, 400, 150, 25);
        jl3.setFont(font2);
        jl3.setBackground(var2);
        jl3.setForeground(Color.WHITE);

        // Adding the button icons
        jb = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\president.jpeg", 100, 100));
        jb.setBounds(50, 300, 100, 100);
        jb2 = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\primeminister.jpeg", 100, 100));
        jb2.setBounds(240, 300, 100, 100);
        jb3 = new JButton(Resize("C:\\Users\\Nas\\IdeaProjects\\SpicyS\\src\\Images\\senator.jpeg", 100, 100));
        jb3.setBounds(440, 300, 100, 100);

        // Setting events
        jb.addActionListener(e -> {
            showBarChart("President");
        });
        jb2.addActionListener(eo -> {
            showBarChart("Prime Minister");
        });
        jb3.addActionListener(e1 -> {
            showBarChart("Senator");
        });

        jb4 = new JButton("Back");
        jb4.setBounds(225, 450, 150, 25);
        jb4.setForeground(Color.WHITE);
        jb4.setFont(font2);
        jb4.setBackground(var2);

        jb4.addActionListener(e-> {
            new VoterDash();
            jf.dispose();
        });

        jf.add(jl);
        jf.add(jl2);
        jf.add(jl3);
        jf.add(jb);
        jf.add(jb2);
        jf.add(jb3);
        jf.add(jb4);
        jf.add(jl4);

        // Finalizing input
        jf.setSize(600, 550);
        jf.getContentPane().setBackground(var);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private DefaultCategoryDataset createDataset(List<String> candidateNames, List<Integer> votes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add data to the dataset
        for (int i = 0; i < candidateNames.size(); i++) {
            String uniqueName = candidateNames.get(i) + " " + i; // (Remember NASIR) Here you indexed the names to make names unique
            dataset.addValue(votes.get(i), "Votes", uniqueName);
        }

        return dataset;
    }
    public void showBarChart(String position) {
        Color var = new Color(255, 128, 0);
        candidateNames = Linker.getCandidateNamesForPosition(position);
        candidateVotes = Linker.getVotesForCandidates(position);

        DefaultCategoryDataset dataset = createDataset(candidateNames, candidateVotes);

        CategoryDataset categoryDataset = dataset;

        JFreeChart chart = ChartFactory.createBarChart(position + " Vote Results", "Candidates", "Votes",
                categoryDataset, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);

        JFrame chartFrame = new JFrame(position + " Results"); // New frame for the chart
        chartFrame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        chartFrame.setSize(600, 400);
        chartFrame.getContentPane().setBackground(var);
        chartFrame.setLocationRelativeTo(null);
        chartFrame.setVisible(true);

        /*// Find the candidate with the highest votes
        int maxVotes = Integer.MIN_VALUE;
        String winner = "";
        for (int i = 0; i < candidateNames.size(); i++) {
            if (candidateVotes.get(i) > maxVotes) {
                maxVotes = candidateVotes.get(i);
                winner = candidateNames.get(i);*/
    }

    public static void main(String[] args) {
    }
}