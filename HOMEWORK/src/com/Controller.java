package com;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    public JFrame frame;
    private JTextField regularExpression;
    private JTextField testString;
    private JTextArea matchInfo;
    private String regex;
    private String string;
    private JLabel labelRegularExpression;
    private JLabel labelTestString;
    private JLabel labelMatchInfo;
    private JScrollPane scroll;
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


        labelRegularExpression = new JLabel("Enter your regex: ");
        labelRegularExpression.setBounds(37, 50, 300, 36);
        frame.add(labelRegularExpression);

        regularExpression = new JTextField();
        regularExpression.setBounds(37, 85, 300, 36);
        frame.getContentPane().add(regularExpression);
        regularExpression.setColumns(10);


        labelTestString = new JLabel("Test string");
        labelTestString.setBounds(37, 120, 300, 36);
        frame.add(labelTestString);

        testString = new JTextField();
        testString.setBounds(37, 155, 300, 36);
        frame.getContentPane().add(testString);
        testString.setColumns(10);


        labelMatchInfo = new JLabel("Match information");
        labelMatchInfo.setBounds(350, -7, 216, 148);
        frame.add(labelMatchInfo);

        matchInfo = new JTextArea();
        matchInfo.setEditable(false);
        scroll = new JScrollPane(matchInfo);
        matchInfo.setBounds(350, 85, 353, 200);
        scroll.setBounds(350, 85, 356, 206);
        frame.getContentPane().add(scroll);


        caseSensitive = new JRadioButton("Case sensitive");
        caseSensitive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if(caseSensitive.isSelected()) {
                    caseChoice = false;
                }else {
                    caseChoice = true;
                }
            }
        });

        caseSensitive.setBounds(130,210,150,30);
        caseSensitive.setSelected(true);
        frame.add(caseSensitive);

        JButton btnSubmit = new JButton("Extract the pattern");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                regex = regularExpression.getText();
                string = testString.getText();

                if(!regex.isEmpty() && !string.isEmpty()) {
                    final Pattern pattern;
                    if(caseChoice) {
                        pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
                    }else {
                        pattern = Pattern.compile(regex, Pattern.MULTILINE);
                    }
                    final Matcher matcher = pattern.matcher(string);
                    matchInfo.setText(null);

                    if(!matcher.find()) {
                        matchInfo.setForeground(Color.RED);
                        matchInfo.append("\n\nYour regular expression does not match the subject string.\n\n");

                    }else {

                        matchInfo.setForeground(Color.BLUE);
                        matchInfo.append("\nMatch 1 "+matcher.start() + "-" + matcher.end() + " " + matcher.group(0)+"\n");

                        int i = 2;

                        while (matcher.find()) {

                            matchInfo.append("\nMatch "+ i + matcher.start() + "-" + matcher.end() + " " + matcher.group(0)+"\n");
                            i++;

                        }
                        if(--i==1) {
                            matchInfo.append("\n   "+ i +" match " );
                        }else {
                            matchInfo.append("\n   "+ i +" matches ");
                        }
                    }

                }
            }
        });

        btnSubmit.setBounds(50, 260, 150, 29);
        frame.getContentPane().add(btnSubmit);


        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                matchInfo.setText(null);
                regularExpression.setText(null);
                testString.setText(null);
            }
        });

        btnClear.setBounds(200, 260, 106, 29);
        frame.getContentPane().add(btnClear);

    }
}