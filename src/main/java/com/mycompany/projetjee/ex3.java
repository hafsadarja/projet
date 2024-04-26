/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Hafsa Darja
 */
public class ex3 extends JFrame {

    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public ex3() {
        setTitle("Gestionnaire de Tâches");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        JButton addButton = new JButton("Ajouter une Tâche");
        JButton removeButton = new JButton("Supprimer la Tâche");
        JButton completeButton = new JButton("Marquer comme Terminée");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String taskName = JOptionPane.showInputDialog("Entrez le nom de la tâche :");
                if (taskName != null && !taskName.isEmpty()) {
                    taskListModel.addElement(taskName);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!taskList.isSelectionEmpty()) {
                    int selectedIndex = taskList.getSelectedIndex();
                    taskListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Sélectionnez une tâche à supprimer.");
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!taskList.isSelectionEmpty()) {
                    int selectedIndex = taskList.getSelectedIndex();
                    String taskName = taskListModel.getElementAt(selectedIndex);
                    taskListModel.set(selectedIndex, "[Terminée] " + taskName);
                } else {
                    JOptionPane.showMessageDialog(null, "Sélectionnez une tâche à marquer comme terminée.");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(completeButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ex3().setVisible(true);
            }
        });
    }
}
