package student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class student {
	private static JTextArea textArea;

	public static void main(String[] args) {
//		Controller control=new Controller();
        createDbTable db = new createDbTable();
        
		db.connectDb();
		db.createTable();

		JFrame frame = new JFrame("Student List");
		frame.setLayout(null);
		frame.setSize(800, 300);

		JLabel lName = new JLabel("Name");
		lName.setBounds(10, 20, 70, 20);
		frame.add(lName);

		JTextField tf2 = new JTextField();
		tf2.setBounds(10, 40, 70, 20);
		frame.add(tf2);

		JLabel lGender = new JLabel("Gender");
		lGender.setBounds(10, 70, 70, 20);
		frame.add(lGender);

		JRadioButton maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setBounds(10, 90, 70, 20);
		frame.add(maleRadioButton);

		JRadioButton femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setBounds(10, 110, 70, 20);
		frame.add(femaleRadioButton);

		// Create a ButtonGroup and add the radio buttons to it
		ButtonGroup genderButtonGroup = new ButtonGroup();
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);

		JLabel lFavFood = new JLabel("Favorite Food");
		lFavFood.setBounds(10, 140, 100, 20);
		frame.add(lFavFood);

		JCheckBox pizzaCheckBox = new JCheckBox("Pizza");
		pizzaCheckBox.setBounds(10, 160, 70, 20);
		frame.add(pizzaCheckBox);

		JCheckBox burgerCheckBox = new JCheckBox("Burger");
		burgerCheckBox.setBounds(10, 180, 70, 20);
		frame.add(burgerCheckBox);

		JCheckBox momoCheckBox = new JCheckBox("Momo");
		momoCheckBox.setBounds(10, 200, 70, 20);
		frame.add(momoCheckBox);

		JButton addButton = new JButton("Submit");
		addButton.setBounds(10, 230, 120, 20);
		frame.add(addButton);

		textArea = new JTextArea();
		
		JScrollPane pane= new JScrollPane(textArea);
		pane.setBounds(300, 20, 500, 230);
		frame.add(pane);
		
		

		
		
		
		
		
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tf2.getText();
				String gender = maleRadioButton.isSelected() ? "Male" : "Female";
				
				String favoriteFoods = getFavoriteFoods(pizzaCheckBox.isSelected(), burgerCheckBox.isSelected(),
						momoCheckBox.isSelected());
				db.insertTable(name, gender, favoriteFoods);
				db.displayStudentList(textArea);
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	

	private static String getFavoriteFoods(boolean pizza, boolean burger, boolean momo) {
		StringBuilder sb = new StringBuilder();
		if (pizza) {
			sb.append("Pizza");
		}
		if (burger) {
			sb.append("Burger");
		}
		if (momo) {
			sb.append("Momo");
		}
		return sb.toString();

	}

}

