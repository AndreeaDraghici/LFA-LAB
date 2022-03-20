package com.Tema1;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    public JFrame frame;
    private JTextField regularExpression;
    private JTextField testString;
    private JTextArea matchInformation;
    private String regex;
    private String string;
    private JRadioButton caseSensitive;
    private boolean caseChoice;


    public Controller() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame("Regular Expression");
        frame.setBounds(600, 250, 750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel labelRegularExpression = new JLabel("Enter your regex");
        labelRegularExpression.setBounds(37, 50, 300, 36);
        frame.add(labelRegularExpression);

        regularExpression = new JTextField();
        regularExpression.setBounds(37, 85, 300, 30);
        frame.getContentPane().add(regularExpression);
        regularExpression.setColumns(10);

        JLabel labelTestString = new JLabel("Test string");
        labelTestString.setBounds(37, 120, 300, 36);
        frame.add(labelTestString);

        testString = new JTextField();
        testString.setBounds(37, 155, 300, 65);
        frame.getContentPane().add(testString);
        testString.setColumns(10);

        JLabel labelMatchInfo = new JLabel("Match information");
        labelMatchInfo.setBounds(350, -7, 216, 148);
        frame.add(labelMatchInfo);

        matchInformation = new JTextArea();
        matchInformation.setEditable(false);
        JScrollPane scroll = new JScrollPane(matchInformation);
        matchInformation.setBounds(350, 85, 353, 200);
        scroll.setBounds(350, 85, 356, 206);
        frame.getContentPane().add(scroll);

        caseSensitive = new JRadioButton("Case sensitive");
        caseSensitive.addActionListener(arg0 -> caseChoice = !caseSensitive.isSelected());

        caseSensitive.setBounds(130, 220, 150, 30);
        caseSensitive.setSelected(true);
        frame.add(caseSensitive);

        JButton btnSubmit = new JButton("Extract the pattern");
        btnSubmit.addActionListener(arg0 -> {

            regex = regularExpression.getText();
            string = testString.getText();

            if (!regex.isEmpty() && !string.isEmpty()) {
                final Pattern pattern;
                if (caseChoice) {
                    pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
                } else {
                    pattern = Pattern.compile(regex, Pattern.MULTILINE);
                }
                final Matcher matcher = pattern.matcher(string);
                matchInformation.setText(null);

                if (!matcher.find()) {
                    matchInformation.setForeground(Color.RED);
                    matchInformation.append("\n\nYour regular expression does not match the subject string.\n\n");
                } else {
                    matchInformation.setForeground(Color.BLUE);
                    matchInformation.append("\nMatch 1 " + matcher.start() + "-" + matcher.end() + " " + matcher.group(0) + "\n");
                    int i = 2;
                    while (matcher.find()) {
                        matchInformation.append("\nMatch " + i + matcher.start() + "-" + matcher.end() + " " + matcher.group(0) + "\n");
                        i = i + 1;
                    }
                    if (i-- == 1) {
                        matchInformation.append("\n   " + i + " match ");
                    } else {
                        matchInformation.append("\n   " + i + " matches ");
                    }
                }

            }
        });

        btnSubmit.setBounds(50, 260, 150, 29);
        frame.getContentPane().add(btnSubmit);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(arg0 -> {
            matchInformation.setText(null);
            regularExpression.setText(null);
            testString.setText(null);
        });

        btnClear.setBounds(200, 260, 106, 29);
        frame.getContentPane().add(btnClear);

    }
}
