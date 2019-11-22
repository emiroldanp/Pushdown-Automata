
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class GUI extends JFrame {
//     private static final long serialVersionUID = 1L;
//     private JPanel wrapper;
//     private JLabel title, insert1, insert2, insert3, txt;
//     private JTextField nameTxt, stringAutomata, result, stringDisplay;
//     private JButton verifyString;

//     public GUI(){
//         /*
//         *Constructor method that will initialize all the thing to make work the graphic user interface.
//         */
//         super("Top-down Parsing");
//         initComponents();
//         setLayout(new GridLayout(1, 1));
//         setDefaultCloseOperation(EXIT_ON_CLOSE);
//         setSize(900, 300);
//         setVisible(true);
//     }

//     public void initComponents(){
//         /*
//         *This method will initialize all the variables and set all the setting needed to display a window to the user.
//         */
//         wrapper = new JPanel();
//         title = new JLabel("Lambda - N D F A");
//         insert1 = new JLabel("Insert the name of the .txt file");
//         insert2 = new JLabel("Insert the string");
//         insert3 = new JLabel("The string was:");
//         txt = new JLabel(".txt");
//         nameTxt = new JTextField();
//         stringAutomata = new JTextField();
//         result = new JTextField();
//         stringDisplay = new JTextField();
//         verifyString = new JButton("Check string");

//         title.setFont(new Font("C", Font.BOLD, 18));
//         title.setHorizontalAlignment(JLabel.CENTER);
//         insert1.setHorizontalAlignment(JLabel.CENTER);
//         insert2.setHorizontalAlignment(JLabel.CENTER);
//         insert3.setHorizontalAlignment(JLabel.RIGHT);
//         result.setEditable(false);
//         stringDisplay.setEditable(false);
//         stringDisplay.setForeground(Color.black);

//         verifyString.addActionListener(new checkButtonListener());
//         wrapper.setLayout(new GridLayout(6, 5));
//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));
//         wrapper.add(title);
//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));

//         wrapper.add(new JLabel(""));
//         wrapper.add(insert1);
//         wrapper.add(nameTxt);
//         wrapper.add(txt);
//         wrapper.add(new JLabel(""));

//         wrapper.add(new JLabel(""));
//         wrapper.add(insert2);
//         wrapper.add(stringAutomata);
//         wrapper.add(verifyString);
//         wrapper.add(new JLabel(""));

//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));

//         wrapper.add(new JLabel(""));
//         wrapper.add(insert3);
//         wrapper.add(stringDisplay);
//         wrapper.add(result);
//         wrapper.add(new JLabel(""));

//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));
//         wrapper.add(new JLabel(""));

//         add(wrapper);
//     }

//     public class checkButtonListener implements ActionListener{
//         public void actionPerformed(ActionEvent e){
//             /*
//             *This method will add funcionality to the button when is click.
//             */

//             Automata auto = new Automata();//This will initialize the object automata where all the magic is being made.
//             String name = nameTxt.getText();//This will get the name of the .txt file from the text field
//             String sTest = stringAutomata.getText();//This will get the string to be tested by the automata
//             boolean res = false;//This is the variable to indicate if the string was accepted by the automata or not

//             if(!name.equals("") || !sTest.equals("")){
//                 try {
//                     res = auto.processString(name, sTest);
//                 } catch (Exception ex) {
//                     System.err.println("File is not found");
//                 }

//                 if(res){
//                     result.setText("Accepted");
//                 }else{
//                     result.setText("Not Accepted");
//                 }

//                 stringAutomata.setText("");
//                 stringDisplay.setText(sTest);
//             }else{
//                 JOptionPane.showMessageDialog(wrapper, "both fields must have something");
//             }
//         }
//     }
// }