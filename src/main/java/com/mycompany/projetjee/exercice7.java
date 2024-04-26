/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjee;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;




/**
 *
 * @author Hafsa Darja
 */

public class exercice7 extends JFrame {
    private JTextPane textPane;
    private JFileChooser fileChooser;

    public exercice7() {
        setTitle("Simple Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.CENTER);

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        textPane.read(reader, null);
                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        fileMenu.add(openMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                        textPane.write(writer);
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        fileMenu.add(saveMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem findReplaceMenuItem = new JMenuItem("Find/Replace");
        findReplaceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String findText = JOptionPane.showInputDialog("Enter text to find:");
                if (findText != null && !findText.isEmpty()) {
                    String replaceText = JOptionPane.showInputDialog("Enter replacement text:");
                    if (replaceText != null) {
                        String content = textPane.getText();
                        content = content.replace(findText, replaceText);
                        textPane.setText(content);
                    }
                }
            }
        });
        editMenu.add(findReplaceMenuItem);

        JMenu styleMenu = new JMenu("Style");
        menuBar.add(styleMenu);

        JMenuItem boldMenuItem = new JMenuItem("Bold");
        boldMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyStyleToSelectedText(Font.BOLD);
            }
        });
        styleMenu.add(boldMenuItem);

        JMenuItem italicMenuItem = new JMenuItem("Italic");
        italicMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyStyleToSelectedText(Font.ITALIC);
            }
        });
        styleMenu.add(italicMenuItem);

        JMenuItem underlineMenuItem = new JMenuItem("Underline");
        underlineMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyUnderlineToSelectedText();
            }
        });
        styleMenu.add(underlineMenuItem);
    }

    private void applyStyleToSelectedText(int style) {
        StyledDocument doc = textPane.getStyledDocument();
        int selectionStart = textPane.getSelectionStart();
        int selectionEnd = textPane.getSelectionEnd();
        if (selectionStart != selectionEnd) {
            StyleConstants.setBold(doc.addStyle("bold", null), (style & Font.BOLD) != 0);
            StyleConstants.setItalic(doc.addStyle("italic", null), (style & Font.ITALIC) != 0);
            doc.setCharacterAttributes(selectionStart, selectionEnd - selectionStart, doc.getStyle((style & Font.BOLD) != 0 ? "bold" : "italic"), true);
        }
    }

    private void applyUnderlineToSelectedText() {
        StyledDocument doc = textPane.getStyledDocument();
        int selectionStart = textPane.getSelectionStart();
        int selectionEnd = textPane.getSelectionEnd();
        if (selectionStart != selectionEnd) {
            Map<TextAttribute, Object> attributes = new HashMap<>();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            doc.setCharacterAttributes(selectionStart, selectionEnd - selectionStart, doc.getStyle(StyleContext.DEFAULT_STYLE), true);
            doc.setCharacterAttributes(selectionStart, selectionEnd - selectionStart, (AttributeSet) attributes, false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new exercice7();
            }
        });
    }
}
//setJMenuBar(add)