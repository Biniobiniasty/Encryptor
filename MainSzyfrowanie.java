import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public class MainSzyfrowanie extends JFrame {

	private JPanel contentPane;
	private JTextField txtAsdf;
	private Szyfr szyfr;
	private Pliki file;
	private JTextPane txtpnText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainSzyfrowanie frame = new MainSzyfrowanie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainSzyfrowanie() {
		setTitle("Szyfrator");
		szyfr = new Szyfr();
		file = new Pliki();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 632);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton_1 = new JButton("Decrypt");
		btnNewButton_1.setBounds(197, 542, 179, 27);

		JButton btnNewButton_2 = new JButton("Encrypt");
		btnNewButton_2.setBounds(15, 542, 172, 27); 

		JButton btnNewButton_3 = new JButton("Save to file...");
		btnNewButton_3.setBounds(386, 542, 153, 27);

		JButton btnNewButton = new JButton("Load file...");
		btnNewButton.setBounds(550, 542, 152, 27);

		txtAsdf = new JTextField();
	
		txtAsdf.setBounds(68, 16, 912, 31);
		txtAsdf.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		txtAsdf.setForeground(Color.ORANGE);
		txtAsdf.setText("min5znakow");
		txtAsdf.setBackground(new Color(0, 0, 51));
		txtAsdf.setColumns(10);

		txtpnText = new JTextPane();
		txtpnText.setInheritsPopupMenu(true);
		txtpnText.setIgnoreRepaint(true);
		txtpnText.setVerifyInputWhenFocusTarget(false);
		txtpnText.setCaretColor(Color.YELLOW);
		txtpnText.setBorder(new CompoundBorder());
		txtpnText.setBounds(10, 58, 988, 473);

		txtpnText.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}

			public void ancestorMoved(AncestorEvent event) {
			}

			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		txtpnText.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}

			public void inputMethodTextChanged(InputMethodEvent event) {

			}
		});
		txtpnText.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 14));
		txtpnText.setBackground(new Color(0, 0, 51));
		txtpnText.setForeground(Color.ORANGE);
		txtpnText.setText("min 1 max 2000 znakowt do szyfrowania");

		JLabel lblNewLabel_1_1 = new JLabel("Key:");
		lblNewLabel_1_1.setBounds(15, 22, 53, 25);
		lblNewLabel_1_1.setForeground(Color.YELLOW);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));

		JLabel lblNewLabel_1_1_1 = new JLabel("Ilosc znakow: 4/2000");
		lblNewLabel_1_1_1.setAutoscrolls(true);
		
		lblNewLabel_1_1_1.setBounds(771, 545, 236, 25);
		lblNewLabel_1_1_1.setForeground(Color.YELLOW);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = txtAsdf.getText();
				String text = txtpnText.getText();
				String textSzyfr = szyfr.Szyfruj(text, key);
				txtpnText.setText(textSzyfr);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = txtAsdf.getText();
				String text = txtpnText.getText();
				String textSzyfr = szyfr.Deszyfruj(text, key);
				txtpnText.setText(textSzyfr);
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					file.Zapisz(txtpnText.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtpnText.setText(file.Wczytaj(txtpnText.getText()));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		txtpnText.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				lblNewLabel_1_1_1.setText("Ilosc znakow: " + txtpnText.getText().length() + "/2000");
			}
		});
		txtAsdf.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				lblNewLabel_1_1_1.setText("Ilosc znakow: " + txtAsdf.getText().length() + "/100");
			}
		});
		contentPane.setLayout(null);
		contentPane.add(txtpnText);
		contentPane.add(btnNewButton_2);
		contentPane.add(btnNewButton_1); 
		contentPane.add(btnNewButton_3);
		contentPane.add(btnNewButton);
		contentPane.add(lblNewLabel_1_1_1);
		contentPane.add(lblNewLabel_1_1);
		contentPane.add(txtAsdf);
	}
}
