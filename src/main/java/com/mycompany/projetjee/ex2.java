/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjee;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Hafsa Darja
 */


public class ex2 extends JFrame implements ActionListener {
    private JLabel amountLabel, resultLabel;
    private JTextField amountTextField;
    private JComboBox<String> currencyComboBox;
    private JButton convertButton;

    private String[] currencies = {"USD", "EUR", "GBP", "JPY", "CAD"}; // Exemple de devises disponibles
    private double[] exchangeRates = {1.0, 0.85, 0.73, 109.54, 1.26}; // Taux de change par rapport à l'USD

    public ex2 () {
        setTitle("Convertisseur de devises");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        amountLabel = new JLabel("Montant:");
        amountTextField = new JTextField(10);
        currencyComboBox = new JComboBox<>(currencies);
        convertButton = new JButton("Convertir");
        resultLabel = new JLabel("");

        JPanel panel = new JPanel();
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(currencyComboBox);
        panel.add(convertButton);
        panel.add(resultLabel);

        convertButton.addActionListener(this);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            String input = amountTextField.getText();
            try {
                double amount = Double.parseDouble(input);
                String selectedCurrency = (String) currencyComboBox.getSelectedItem();
                int index = currencyComboBox.getSelectedIndex();
                double convertedAmount = amount / exchangeRates[index];
                resultLabel.setText(String.format("%.2f %s", convertedAmount, selectedCurrency));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un montant valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new ex2 ();
    }
}