import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
public class OnlineQuizApp extends JFrame implements ActionListener {
private JTree questionTree;
private JLabel questionLabel;
private JTextField answerField;
private JButton submitButton;
private JLabel feedbackLabel;

private HashMap<String, String> questions;
private String currentQuestion;

public OnlineQuizApp() {
setTitle("Online Quiz");
setSize(500, 400);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new BorderLayout());

initializeQuestions();

DefaultMutableTreeNode root = new DefaultMutableTreeNode("Questions");
for (int i = 1; i <= questions.size(); i++) {
root.add(new DefaultMutableTreeNode("Question " + i));
}

questionTree = new JTree(root);
JScrollPane treeScrollPane = new JScrollPane(questionTree);
add(treeScrollPane, BorderLayout.WEST);

questionLabel = new JLabel("Select a question from the tree.");

add(questionLabel, BorderLayout.NORTH);

JPanel answerPanel = new JPanel();
answerPanel.setLayout(new FlowLayout());
answerField = new JTextField(15);
submitButton = new JButton("Submit Answer");
submitButton.addActionListener(this);
answerPanel.add(new JLabel("Your Answer: "));
answerPanel.add(answerField);
answerPanel.add(submitButton);
add(answerPanel, BorderLayout.CENTER);

feedbackLabel = new JLabel();
add(feedbackLabel, BorderLayout.SOUTH);

questionTree.addTreeSelectionListener(e -> {
DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
questionTree.getLastSelectedPathComponent();
if (selectedNode != null) {
currentQuestion = selectedNode.getUserObject().toString();
if (questions.containsKey(currentQuestion)) {
questionLabel.setText(currentQuestion + ": " + questions.get(currentQuestion));
feedbackLabel.setText("");
answerField.setText("");
}
}
});
setVisible(true);
}

private void initializeQuestions() {
questions = new HashMap<>();
questions.put("Question 1", "What is the capital of France?");
questions.put("Question 2", "What is 2 + 2?");
questions.put("Question 3", "What is the color of the sky?");
}
@Override

public void actionPerformed(ActionEvent e) {
if (currentQuestion != null && !answerField.getText().isEmpty()) {
String correctAnswer = "";
switch (currentQuestion) {
case "Question 1":
correctAnswer = "Paris";
break;
case "Question 2":
correctAnswer = "4";
break;
case "Question 3":
correctAnswer = "Blue";
break;
}
String userAnswer = answerField.getText().trim();
if (userAnswer.equalsIgnoreCase(correctAnswer)) {
feedbackLabel.setText("Correct!");
} else {
feedbackLabel.setText("Incorrect. The correct answer is: " + correctAnswer);
}
} else {
feedbackLabel.setText("Please select a question and provide an answer.");
}
}
public static void main(String[] args) {
new OnlineQuizApp();
}
}
