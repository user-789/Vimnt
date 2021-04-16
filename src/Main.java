import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Main {
	
	static JFrame frame;
	static Color blue = new Color(100, 180, 250);
	
	static Point initMouse = new Point(0, 0);
	static Point initWindow = new Point(0, 0);

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setSize(300, 500);
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.GREEN);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		
		JPanel closebar = new JPanel();
		closebar.setLayout(new BorderLayout());
		closebar.setBackground(blue);
		closebar.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent e) {
				initMouse = e.getPoint();
				initWindow = frame.getLocation();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		closebar.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(e.getX()-initMouse.x+initWindow.x, e.getY()-initMouse.y+initWindow.y);
				initWindow = frame.getLocation();
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {}
			
		});
		
		JPanel closebuttons = new JPanel();
		closebuttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		closebuttons.setBackground(blue);
		
		JLabel title = new JLabel("Good Text Editor", JLabel.CENTER);
		title.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
	
		JPanel editpanel = new JPanel();
		editpanel.setBorder(BorderFactory.createLineBorder(blue, 6));
		editpanel.setBackground(Color.ORANGE);
		editpanel.setLayout(new BorderLayout());
		
		JTextArea editor = new JTextArea();
		editor.setFont(new Font("Monospaced", Font.PLAIN, 16));
		editor.setBackground(Color.WHITE);
		editor.setForeground(Color.BLACK);
		editor.setLineWrap(true);
		
		
		closebuttons.add(createExitButton());
		closebuttons.add(createExitButton());
		closebuttons.add(createExitButton());
		closebar.add(title, BorderLayout.CENTER);
		closebar.add(closebuttons, BorderLayout.EAST);
		editpanel.add(editor, BorderLayout.CENTER);
		frame.add(closebar, BorderLayout.NORTH);
		frame.add(editpanel, BorderLayout.CENTER);
		
		frame.setMinimumSize(new Dimension(600, 400));
		frame.pack();
		
		
		JDialog popup = new JDialog(frame, "Exit?");
		JLabel popuptext = new JLabel("Would you like to quit?", JLabel.CENTER);
		popuptext.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		popuptext.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
		JPanel buttons = new JPanel();
		buttons.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		
		JButton yes = new JButton("Yes");
		yes.setPreferredSize(new Dimension(150, 20));
		yes.setBackground(Color.BLUE);
		yes.setForeground(Color.WHITE);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		JButton no = new JButton("No");
		no.setPreferredSize(new Dimension(50, 20));
		no.setBackground(Color.LIGHT_GRAY);
		no.setFocusable(false);
		no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popup.dispatchEvent(new WindowEvent(popup, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		buttons.setLayout(new BorderLayout());
		buttons.add(yes, BorderLayout.WEST);
		buttons.add(no, BorderLayout.EAST);
		
		popup.setLayout(new BoxLayout(popup.getContentPane(), BoxLayout.Y_AXIS));
		popup.add(popuptext);
		popup.add(buttons);
		popup.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		popup.pack();
		popup.setResizable(false);
		
		Timer timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Point frameLocation = frame.getLocationOnScreen();
				popup.setLocation(frameLocation.x + (int)(Math.random()*350), frameLocation.y + (int)(Math.random()*250));
				popup.setVisible(true);
			}
		});
		
		timer.start();
		frame.setVisible(true);
		
	}
	
	static JButton createExitButton() {
		JButton closebtn = new JButton();
		closebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		closebtn.setBackground(new Color(200, 80, 80));
		closebtn.setForeground(Color.WHITE);
		closebtn.setFont(new Font("Arial", Font.BOLD, 8));
		closebtn.setText("X");
		closebtn.setFocusable(false);
		return closebtn;
	}

}
