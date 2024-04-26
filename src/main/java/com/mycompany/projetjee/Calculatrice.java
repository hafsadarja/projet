package com.mycompany.projetjee;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculatrice extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField display;
	private JButton[] boutons;
	private String[] nomsBoutons = {"7", "8", "9", "/",
			 "4", "5", "6", "*",
			"1", "2", "3", "-",
			"0", ".", "=", "+"};
	private JPanel panelBoutons;

	private double num1, num2;
	private char operation;

	public Calculatrice() {
		setTitle("Calculatrice");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout()); 

		display = new JTextField(10);
		display.setEditable(false);

		add(display, BorderLayout.NORTH);

		panelBoutons = new JPanel();
		panelBoutons.setLayout(new GridLayout(4, 4));

		boutons = new JButton[nomsBoutons.length];
		
		for (int i = 0; i < 16; i++) {
			boutons[i] = new JButton(nomsBoutons[i]);
			boutons[i].addActionListener(this); // Important
			panelBoutons.add(boutons[i]);
		}

		add(panelBoutons, BorderLayout.CENTER);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();

		String boutonTexte = b.getText();

		if (boutonTexte.matches("[0-9]|\\.")) 
		{
			display.setText(display.getText() + boutonTexte);

		} else if (boutonTexte.matches("[/*\\-+]")) 
		{
			num1 = Double.parseDouble(display.getText());
			operation = boutonTexte.charAt(0);
			display.setText("");
		} 
		else if (boutonTexte.equals("=")) 
		{
			num2 = Double.parseDouble(display.getText());
			double resultat = 0;
			switch (operation) 
			{
			case '+':
				resultat = num1 + num2;
				break;
			case '-':
				resultat = num1 - num2;
				break;
			case '*':
				resultat = num1 * num2;
				break;
			case '/':
				if (num2 != 0)
					resultat = num1 / num2;
				else {
					// System.out.println("Erreur: Division par zéro");
					JOptionPane.showMessageDialog(this, "Erreur: Division par zéro");
					return;
				}
				break;
			}
			
			display.setText(Double.toString(resultat));
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Calculatrice();
			}
		});

	}
}
