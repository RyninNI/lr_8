package package1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

public class Calculator {
	public static void main(String[] args) {
		CalFrame frame = new CalFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class CalFrame extends JFrame {
	public CalFrame() {
		setTitle("");
		CalcPanel panel = new CalcPanel();
		add(panel);
		pack();
	}
}

class CalcPanel extends JPanel {
	private JButton out;
	private JPanel panel;
	private double result;
	private String oldCommand;
	
	public CalcPanel() {
		setLayout(new BorderLayout());

		result = 0;
		oldCommand = "=";

		Font fontout = new Font("TimesRoman", Font.BOLD, 50);

		out = new JButton("0");
		out.setEnabled(false);
		add(out, BorderLayout.NORTH);
		out.setFont(fontout);

		ActionListener Op1 = new OperationOne();
		ActionListener Op2 = new OperationTwo();

		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 5));

		newButton("7", Op1);
		newButton("8", Op1);
		newButton("9", Op1);
		newButton("+", Op2);

		newButton("4", Op1);
		newButton("5", Op1);
		newButton("6", Op1);
		newButton("-", Op2);

		newButton("1", Op1);
		newButton("2", Op1);
		newButton("3", Op1);
		newButton("*", Op2);
		
		newButton("0", Op1);
		newButton("C", Op1);
		newButton("=", Op2);
		newButton("/", Op2);

		add(panel, BorderLayout.CENTER);

	}

	private void newButton(String label, ActionListener listener) {
		Font fontButton = new Font("TimesRoman", Font.BOLD, 20);
		JButton button = new JButton(label);
		button.addActionListener(listener);
		button.setFont(fontButton);
		panel.add(button);
	}

	private class OperationOne implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String input = event.getActionCommand();
			if (start_operation) {
				out.setText("");
			}
			if (input.equals("C")) {
				out.setText("0");
			} else {
				out.setText(out.getText() + input);
			}
		}
	}

	private class OperationTwo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String complexOp = event.getActionCommand();

			if (complexOp.equals("1/x") || complexOp.equals("sqrt(x)")) {
				oldCommand = complexOp;
				operation(Double.parseDouble(out.getText()));
				oldCommand = "=";
			} else {
				if (start_operation) {
					if (complexOp.equals("-")) {
						out.setText(complexOp);
					} else
						oldCommand = complexOp;
				} else {
					operation(Double.parseDouble(out.getText()));
					oldCommand = complexOp;
				}
			}
		}
	}

	public void operation(double x) {
	
		switch (oldCommand) {
		case "+":
			result += x;
			break;
		case "-":
			result -= x;
			break;
		case "*":
			result *= x;
			break;
		case "/":
			result /= x;
			break;
		case "=":
			result = x;
			break;
		case default:
			break;
		}
		out.setText("" + result);
	}

	
}


