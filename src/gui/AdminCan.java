package gui;

import database.Candidate;
import database.Linker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdminCan extends JFrame {
    private DefaultTableModel tableModel;
    private JTable candidateTable;
    JButton jb3;

    public AdminCan() {
        super("Candidate Information Table");

        Color var = new Color(255, 128, 0);
        Color var2 = new Color(0, 128, 255);
        Font font2 = new Font("TIMES NEW ROMAN", Font.BOLD, 14);

        Font font3 = new Font("TIMES NEW ROMAN", Font.BOLD, 16);

        jb3 = new JButton("Back");
        jb3.setFont(font3);
        jb3.setBackground(var2);
        jb3.setForeground(Color.WHITE);
        jb3.setBounds(300, 500, 100, 25);
        jb3.addActionListener(e -> {
            new AdminDash();
            dispose();
        });
        add(jb3);

        // Set up the GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create table model with column names
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Full Name");
        tableModel.addColumn("Age");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Email");
        tableModel.addColumn("Account Status");
        tableModel.addColumn("ID");
        tableModel.addColumn("Remove"); // New column for Remove button

        // Create JTable with the table model
        candidateTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(candidateTable);

        int height = 25;
        candidateTable.setRowHeight(height);

        // Set button renderers and editors for the new columns
        candidateTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        candidateTable.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new ButtonClickListener()));

        scrollPane.getViewport().setBackground(var);
        candidateTable.setBackground(var2);

        // Add components to the frame
        add(scrollPane);

        // Load candidate information from the database
        loadCandidateInfo();

        // Set the custom renderer for the entire row
        candidateTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set the font for the entire row
                renderer.setFont(font2);
                renderer.setForeground(Color.WHITE);

                return renderer;
            }
        });

        // Set the font for the column names (headers)
        JTableHeader header = candidateTable.getTableHeader();
        header.setFont(font2);

        // Display the frame
        setVisible(true);
    }

    private void loadCandidateInfo() {

        for (Candidate candidate : Linker.getZCandidates()) {
            tableModel.addRow(new Object[]{
                    candidate.getFullName(),
                    candidate.getAge(),
                    candidate.getPhoneNumber(),
                    candidate.getEmail(),
                    candidate.getAccountStatus(),
                    candidate.getRandomId(),
                    "Remove"
            });
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button clicks here
            if ("Remove".equals(e.getActionCommand())) {
                int selectedRow = candidateTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Perform removal action
                    String candidateID = (String) tableModel.getValueAt(selectedRow, 5);
                    try {
                        Linker.removeCandidateByID(candidateID);
                        tableModel.removeRow(selectedRow);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}
