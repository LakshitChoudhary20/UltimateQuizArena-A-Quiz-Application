// FULL UPDATED QuizApp.java WITH:
// - 20 QUESTIONS PER TOPIC
// - RANDOMIZED QUESTION ORDER PER SELECTED TOPIC
// ==============================================================

import java.awt.*;
import javax.swing.*;

public class UltimateQuizArena extends JFrame {

    private String userName = "";
    private String selectedTopic = "";
    private int currentIndex = 0;
    private int[] selectedAnswers;
    private String[][] currentQuestions;
    private javax.swing.Timer countdownTimer;
    private int timeLeft = 30;

    // 20-QUESTION POOLS PER TOPIC
    private final String[][][] allTopics = {
        // ----------------- GENERAL KNOWLEDGE (20) -----------------
        {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Rome", "1"},
            {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Venus", "2"},
            {"Who invented the telephone?", "Newton", "Einstein", "Bell", "Edison", "3"},
            {"Element with symbol O?", "Oxygen", "Gold", "Iron", "Silver", "1"},
            {"Language for Android apps?", "Python", "Java", "Swift", "Kotlin", "2"},
            {"Largest ocean?", "Atlantic", "Pacific", "Indian", "Arctic", "2"},
            {"How many continents?", "5", "6", "7", "8", "3"},
            {"Plants absorb?", "Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen", "3"},
            {"Country gifting Statue of Liberty?", "UK", "France", "Germany", "Italy", "2"},
            {"Painter of Mona Lisa?", "Van Gogh", "Picasso", "Da Vinci", "Rembrandt", "3"},
            {"Fastest animal?", "Cheetah", "Lion", "Tiger", "Horse", "1"},
            {"Smallest country?", "Vatican City", "Nepal", "Monaco", "Bhutan", "1"},
            {"Author of Harry Potter?", "Tolkien", "Rowling", "Lewis", "Twain", "2"},
            {"Longest river?", "Nile", "Amazon", "Ganga", "Yangtze", "2"},
            {"Largest desert?", "Sahara", "Gobi", "Thar", "Arctic", "1"},
            {"Ship of desert?", "Camel", "Horse", "Goat", "Donkey", "1"},
            {"Speed of light?", "300k km/s", "150k", "3m", "100k", "1"},
            {"Hardest substance?", "Diamond", "Iron", "Gold", "Quartz", "1"},
            {"Festival of lights?", "Holi", "Diwali", "Eid", "Christmas", "2"},
            {"Players in cricket?", "10", "11", "12", "9", "2"}
        },

        // ----------------- JAVA PROGRAMMING (20) -----------------
        {
            {"What is JVM?", "Java Virtual Method", "Java Virtual Machine", "Java Mode", "Java Variable Machine", "2"},
            {"Creator of Java?", "Sun Microsystems", "Google", "IBM", "Oracle", "1"},
            {"Keyword to inherit?", "extends", "implements", "inherits", "super", "1"},
            {"Boolean type?", "bool", "boolean", "char", "int", "2"},
            {"Java is platform ______.", "Dependent", "Independent", "Native", "Simple", "2"},
            {"Not keyword?", "void", "public", "main", "static", "3"},
            {"Checks after loop?", "while", "for", "do-while", "none", "3"},
            {"Not OOP?", "Encapsulation", "Abstraction", "Compilation", "Inheritance", "3"},
            {"Accessible anywhere?", "private", "protected", "public", "default", "3"},
            {"Ordered collection?", "List", "HashSet", "Map", "TreeSet", "1"},
            {"Return type of constructor?", "void", "none", "int", "Object", "2"},
            {"Checked exception?", "IOException", "NullPointer", "Arithmetic", "IndexOutOfBounds", "1"},
            {"Package keyword?", "package", "import", "namespace", "using", "1"},
            {"Multiple inheritance by?", "Interfaces", "Classes", "Enums", "Objects", "1"},
            {"Wrapper for int?", "Integer", "Float", "Double", "String", "1"},
            {"Start thread?", "run()", "begin()", "start()", "execute()", "3"},
            {"Swing window class?", "JFrame", "Frame", "Window", "Panel", "1"},
            {"JVM memory areas?", "Stack", "Heap", "Both", "None", "3"},
            {"String is?", "mutable", "immutable", "static", "volatile", "2"},
            {"Strongest access?", "public", "private", "protected", "default", "2"}
        },

        // ----------------- COMPUTER BASICS (20) -----------------
        {
            {"CPU stands for?", "Central Processing Unit", "Power Unit", "Program Unit", "Panel Unit", "1"},
            {"Output device?", "Monitor", "Keyboard", "Mouse", "Scanner", "1"},
            {"Not input?", "Speaker", "Keyboard", "Scanner", "Mouse", "1"},
            {"Copy shortcut?", "Ctrl+C", "Ctrl+V", "Ctrl+X", "Ctrl+Z", "1"},
            {"Filesystem Windows?", "NTFS", "FAT32", "ext4", "XFS", "1"},
            {"BIOS?", "Basic Input Output System", "Binary OS", "Base Output", "Internal Sys", "1"},
            {"Start menu key?", "Windows", "Shift", "Alt", "Ctrl", "1"},
            {"RAM is?", "Volatile", "Permanent", "Static", "Slow", "1"},
            {"Not software?", "Hardware", "System", "Utility", "Application", "1"},
            {"This quiz built in?", "Java", "Python", "C++", "HTML", "1"},
            {"1 byte = ?", "8 bits", "4 bits", "16 bits", "32 bits", "1"},
            {"SSD?", "Solid State Drive", "Super Drive", "Secure System", "Speed Disk", "1"},
            {"GUI?", "Graphical User Interface", "General UI", "Graphics UI", "Global Interface", "1"},
            {"OS example?", "Windows", "Chrome", "YouTube", "Instagram", "1"},
            {"Largest unit?", "TB", "GB", "MB", "KB", "1"},
            {"Paste shortcut?", "Ctrl+V", "Ctrl+B", "Ctrl+P", "Ctrl+X", "1"},
            {"Internet uses?", "TCP/IP", "FTP", "SSH", "SMTP", "1"},
            {"Search engine?", "Google", "Chrome", "Facebook", "WhatsApp", "1"},
            {"Key on keyboard?", "Enter", "Shutdown", "Brightness", "Scroll", "1"},
            {"Brain of computer?", "CPU", "RAM", "ROM", "GPU", "1"}
        },

        // ----------------- SCIENCE (20) -----------------
        {
            {"Water formula?", "H2O", "H2", "O2", "CO2", "1"},
            {"Sun is?", "Star", "Planet", "Moon", "Asteroid", "1"},
            {"Photosynthesis part?", "Leaf", "Root", "Stem", "Seed", "1"},
            {"Bones?", "206", "208", "210", "200", "1"},
            {"Vitamin from Sun?", "D", "A", "B", "C", "1"},
            {"Pumps blood?", "Heart", "Liver", "Lungs", "Kidney", "1"},
            {"Boiling point?", "100°C", "90°C", "80°C", "110°C", "1"},
            {"Detox organ?", "Liver", "Heart", "Lung", "Stomach", "1"},
            {"Virus disease?", "COVID-19", "Malaria", "Diabetes", "Thyroid", "1"},
            {"Unit of life?", "Cell", "Organ", "Tissue", "Nucleus", "1"},
            {"Largest planet?", "Jupiter", "Earth", "Venus", "Mars", "1"},
            {"Nearest star?", "Sun", "Sirius", "Polaris", "Alpha Centauri", "1"},
            {"Most abundant gas?", "Nitrogen", "Oxygen", "CO2", "Hydrogen", "1"},
            {"Blood pH?", "7.4", "6.2", "5.5", "8.5", "1"},
            {"Largest organ?", "Skin", "Liver", "Heart", "Brain", "1"},
            {"Plant breathes?", "Oxygen", "CO2", "Hydrogen", "Nitrogen", "1"},
            {"Powerhouse of cell?", "Mitochondria", "Nucleus", "Ribosome", "Centrosome", "1"},
            {"Force unit?", "Newton", "Joule", "Pascal", "Watt", "1"},
            {"Light year measures?", "Distance", "Time", "Speed", "Area", "1"},
            {"Liquid metal?", "Mercury", "Iron", "Gold", "Copper", "1"}
        }
    };

    private final String[] topicNames = {
        "General Knowledge", "Java Programming", "Computer Basics", "Science & Nature"
    };

    // GUI
    private JLabel questionLabel;
    private JRadioButton[] options = new JRadioButton[4];
    private ButtonGroup optionGroup;
    private JLabel timerLabel, progressLabel;
    private JButton nextBtn, prevBtn, submitBtn;

    public UltimateQuizArena() {
        setTitle("Ultimate Quiz Arena");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        showStartScreen();
    }

    // ----------------- START SCREEN -----------------
    private void showStartScreen() {
        getContentPane().removeAll(); repaint(); revalidate();
        setLayout(null);

        JLabel title = new JLabel("Ultimate Quiz Arena", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title.setBounds(200, 50, 400, 50);
        add(title);

        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        nameLabel.setBounds(280, 130, 200, 30);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameField.setBounds(280, 170, 240, 35);
        add(nameField);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        submitBtn.setBounds(330, 220, 140, 40);
        submitBtn.addActionListener(e -> {
            userName = nameField.getText().trim();
            if (userName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name.");
            } else showTopicSelection();
        });
        add(submitBtn);
    }

    // ----------------- TOPIC SELECTION -----------------
    private void showTopicSelection() {
    getContentPane().removeAll(); repaint(); revalidate();
    setLayout(null);

    JLabel welcome = new JLabel("Welcome, " + userName + "! Choose a topic:");
    welcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
    welcome.setBounds(200, 60, 500, 30);
    add(welcome);

    int y = 130;
    for (int i = 0; i < topicNames.length; i++) {
        int idx = i;
        JButton topicBtn = new JButton(topicNames[i]);
        topicBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        topicBtn.setBounds(260, y, 280, 40);

        topicBtn.addActionListener(e -> {
            selectedTopic = topicNames[idx];

            // Always use 10 questions from a 20-question bank
            int QUESTION_COUNT = 10;

            // Shuffle full question bank for selected topic
            java.util.List<String[]> list = new java.util.ArrayList<>(
                    java.util.Arrays.asList(allTopics[idx])
            );
            java.util.Collections.shuffle(list);

            // Pick first 10 randomized questions
            currentQuestions = list.subList(0, QUESTION_COUNT)
                                   .toArray(new String[QUESTION_COUNT][]);

            selectedAnswers = new int[QUESTION_COUNT];
            currentIndex = 0;

            showQuizScreen();
        });

        add(topicBtn);
        y += 60;
    }
}


    // ----------------- QUIZ SCREEN -----------------
    private void showQuizScreen() {
        getContentPane().removeAll(); repaint(); revalidate();
        setLayout(null);

        JLabel heading = new JLabel(selectedTopic + " Quiz");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setBounds(50, 10, 500, 40);
        add(heading);

        progressLabel = new JLabel("Question 1 of " + currentQuestions.length);
        progressLabel.setBounds(50, 50, 300, 30);
        add(progressLabel);

        timerLabel = new JLabel("Time Left: 30s");
        timerLabel.setForeground(Color.RED);
        timerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        timerLabel.setBounds(600, 10, 150, 40);
        add(timerLabel);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        questionLabel.setBounds(50, 100, 700, 30);
        add(questionLabel);

        optionGroup = new ButtonGroup();
        int y = 160;
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(70, y, 640, 35);
            optionGroup.add(options[i]);
            add(options[i]);
            y += 50;
        }

        prevBtn = new JButton("Previous");
        prevBtn.setBounds(120, 400, 120, 40);
        prevBtn.addActionListener(e -> handlePrevious());
        add(prevBtn);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(270, 400, 120, 40);
        nextBtn.addActionListener(e -> handleNext());
        add(nextBtn);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(420, 400, 120, 40);
        submitBtn.addActionListener(e -> handleSubmit());
        add(submitBtn);

        loadQuestion(currentIndex);
        startTimer();
        setVisible(true);
    }

    // ----------------- LOAD QUESTION -----------------
    private void loadQuestion(int index) {
        questionLabel.setText((index+1) + ". " + currentQuestions[index][0]);
        for (int i = 0; i < 4; i++) options[i].setText(currentQuestions[index][i+1]);

        optionGroup.clearSelection();
        if (selectedAnswers[index] != 0) options[selectedAnswers[index]-1].setSelected(true);

        prevBtn.setEnabled(index > 0);
        nextBtn.setEnabled(index < currentQuestions.length -1);
        submitBtn.setEnabled(index == currentQuestions.length -1);

        progressLabel.setText("Question " + (index+1) + " of " + currentQuestions.length);
        resetTimer();
    }

    private void saveAnswer() {
        for (int i = 0; i < 4; i++) if (options[i].isSelected()) selectedAnswers[currentIndex] = i+1;
    }

    private void handleNext() {
        saveAnswer();
        if (currentIndex < currentQuestions.length - 1) {
            currentIndex++;
            loadQuestion(currentIndex);
        }
    }

    private void handlePrevious() {
        saveAnswer();
        if (currentIndex > 0) {
            currentIndex--;
            loadQuestion(currentIndex);
        }
    }

    // ----------------- TIMER -----------------
    private void startTimer() {
        timeLeft = 30;
        if (countdownTimer != null) countdownTimer.stop();
        countdownTimer = new javax.swing.Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                countdownTimer.stop();
                JOptionPane.showMessageDialog(this, "Time up! Moving to next question.");
                handleNext();
            }
        });
        countdownTimer.start();
    }

    private void resetTimer() {
        if (countdownTimer != null) countdownTimer.stop();
        startTimer();
    }

    // ----------------- SUBMIT -----------------
    private void handleSubmit() {
        if (countdownTimer != null) countdownTimer.stop();
        saveAnswer();

        getContentPane().removeAll(); repaint(); revalidate();
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        int score = 0;

        for (int i = 0; i < currentQuestions.length; i++) {
            JLabel q = new JLabel("<html><b>" + (i+1) + ". " + currentQuestions[i][0] + "</b></html>");
            panel.add(q);

            int correct = Integer.parseInt(currentQuestions[i][5]);
            int ans = selectedAnswers[i];

            for (int j = 1; j <= 4; j++) {
                JLabel opt = new JLabel("  " + (char)('A'+j-1) + ". " + currentQuestions[i][j]);
                if (j == correct && j == ans) { opt.setForeground(Color.GREEN); score++; }
                else if (j == ans && ans != correct) opt.setForeground(Color.RED);
                else if (j == correct) opt.setForeground(new Color(0,120,0));
                panel.add(opt);
            }
            panel.add(Box.createVerticalStrut(15));
        }

        JLabel scoreLabel = new JLabel("You scored " + score + " out of " + currentQuestions.length);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        scoreLabel.setForeground(Color.BLUE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(scoreLabel);

        JButton homeBtn = new JButton("Back to Home");
        homeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeBtn.addActionListener(e -> showStartScreen());
        panel.add(homeBtn);

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBounds(30,20,730,520);
        add(scroll);

        repaint(); revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UltimateQuizArena().setVisible(true));
    }
}
