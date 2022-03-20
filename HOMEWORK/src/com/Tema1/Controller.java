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
/**
 * author: andreea draghici
 * 2022
 * Regex , LFA LAB
 */
public class Controller {

    public JFrame frame;
    private JTextField regularExpression;
    private JTextField testString;
    private JTextArea matchInformation;
    private String regex;
    private String string;
    private JRadioButton caseSensitive;
    private boolean caseChoice;

    /** Create the application.*/
    public Controller() {
        initialize();
    }

    /** Initialize the contents of the frame.*/
    private void initialize() {

        /** create a new frame */
        frame = new JFrame("Regular Expression");
        frame.getContentPane().setBackground(new Color(241, 202, 144));
        frame.setBounds(600, 250, 750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        /** create a label to display text */
        JLabel labelRegularExpression = new JLabel("Enter your regex");
        labelRegularExpression.setBounds(37, 50, 300, 36);
        frame.add(labelRegularExpression);

        regularExpression = new JTextField();
        regularExpression.setBounds(37, 85, 300, 30);
        frame.getContentPane().add(regularExpression);
        regularExpression.setColumns(10);

        /** create a label to display text */
        JLabel labelTestString = new JLabel("Test string");
        labelTestString.setBounds(37, 120, 300, 36);
        frame.add(labelTestString);

        testString = new JTextField();
        testString.setBounds(37, 155, 300, 65);
        frame.getContentPane().add(testString);
        testString.setColumns(10);

        /** create a label to display text */
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
        caseSensitive.addActionListener(arg0 -> {
            if (!caseSensitive.isSelected()){
                caseChoice = true;
            }
            else {
                caseChoice = false;
            }
        });
        caseSensitive.setBounds(130, 220, 150, 30);
        caseSensitive.setSelected(true);
        caseSensitive.setBackground(new Color(241, 202, 144));
        frame.add(caseSensitive);

        JButton btnSubmit = new JButton("Extract the pattern");
        btnSubmit.addActionListener(arg0 -> {

            regex = regularExpression.getText();
            string = testString.getText();

            if (!regex.isEmpty() && !string.isEmpty()) {

                /** Defines a pattern */
                final Pattern pattern;

                /**
                 * if you need to match a text against a regular expression pattern more than one time,
                 * you need to create a Pattern instance using the Pattern.compile() method
                 *
                 * Case Insensitive enables case-insensitive matching, the case of letters will be ignored when performing a search
                 *
                 * In multiline mode the expressions ^ and $ match just after or just before,
                 * respectively, a line terminator or the end of the input sequence
                 *
                 **/
                if (caseChoice) {
                    pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
                } else {
                    pattern = Pattern.compile(regex, Pattern.MULTILINE);
                }

                /**
                 * Used to search for the pattern
                 *
                 * matcher() method is used to search for the pattern in a string
                 *
                 * find() method returns true if the pattern was found in the string and false if it was not found
                 *
                 * */
                final Matcher matcher = pattern.matcher(string);
                matchInformation.setText(null);

                if (!matcher.find()) {
                    matchInformation.setForeground(Color.RED);
                    matchInformation.append("\n\nYour regular expression does not match the subject string.\n\n");
                } else {
                    matchInformation.setForeground(Color.BLUE);
                    /**
                     * If multiple matches can be found in the text, the find() method will find the first,
                     * and then for each subsequent call to find() it will move to the next match
                     *
                     * The methods start() and end() will give the indexes into the text where the found match starts and ends
                     *
                     * */
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
