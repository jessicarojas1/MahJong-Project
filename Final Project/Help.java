////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class Help extends JPanel implements HyperlinkListener
{
	private static final long serialVersionUID = 1L;
	private JEditorPane text = new JEditorPane();
	private JScrollPane scroller = new JScrollPane();
	private JPanel controls = new JPanel();
	private Stack<URL> backStack = new Stack<URL>();
	private Stack<URL> forwardStack = new Stack<URL>();
	private JFrame frame = null;
	private URL currentURL = null;
	private JButton back = null;
	private JButton next = null;

	public Help(String file, String title)
	{
		this(file);

		frame = new JFrame();
		Image img = getToolkit().getImage(getClass().getResource("images/dragon_bg.png"));
		frame.setIconImage(img);

		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				frame.setVisible(false);
			}
		});

		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				getRootPane().getParent().setVisible(false);
			}
		});

		controls.add(close);

		frame.add(this);

		frame.setSize(800, 900);
		frame.setTitle(title);
		frame.setVisible(true);
	}

	public Help(String file)
	{
		setLayout(new BorderLayout());
		scroller.setViewportView(text);
		add(scroller);
		text.addHyperlinkListener(this);
		readHTML(file);

		add(controls, BorderLayout.SOUTH);
	}

	public void hyperlinkUpdate(HyperlinkEvent e)
	{
		try
		{
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
			{
				backStack.push(currentURL);
				currentURL = e.getURL();
				text.setPage(currentURL);
				back.setEnabled(true);
				next.setEnabled(false);
				forwardStack.clear();
			}
		} catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(this, "Unable to load page:\n" + e.getDescription(), "Help Error",
			        JOptionPane.ERROR_MESSAGE);
		}
	}

	public void back()
	{
		try
		{
			forwardStack.push(currentURL);

			currentURL = backStack.pop();
			text.setPage(currentURL);

			if (backStack.empty())
				back.setEnabled(false);
			next.setEnabled(true);
		} catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(this, "Unable to reload previous page", "Help Error",
			        JOptionPane.ERROR_MESSAGE);
		}
	}

	public void forward()
	{
		try
		{
			backStack.push(currentURL);

			currentURL = forwardStack.pop();
			text.setPage(currentURL);

			if (forwardStack.empty())
				next.setEnabled(false);
			back.setEnabled(true);
		} catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(this, "Unable to reload next page", "Help Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void close()
	{
		frame.setVisible(false);
		backStack.clear();
		forwardStack.clear();
	}

	public void display() { frame.setVisible(true); }

	public void conceal() { frame.setVisible(false); }

	private void readHTML(String file)
	{
		try
		{
			text.setEditable(false);

			currentURL = Help.class.getResource(file);
			text.setPage(currentURL);
		} catch (FileNotFoundException FNF)
		{
			JOptionPane.showMessageDialog(this, "Unable to locate help file " + file, "Help Error",
			        JOptionPane.ERROR_MESSAGE);
		} catch (IOException ioe)
		{
			JOptionPane.showMessageDialog(this, "Unable to locate help file " + file, "Help Error",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
}
