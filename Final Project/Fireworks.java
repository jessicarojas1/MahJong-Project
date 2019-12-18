////Jessica Rojas
////Lab 7
////Due Dec 10
////CS3230 Obj Orient UI java

import	static	java.lang.Math.*;
import	java.util.*;
import	java.awt.*;
import	javax.swing.*;
//this is required for lab 7 5/5
public class Fireworks implements Runnable
{
	private	Color[]		colors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE , Color.ORANGE};
	private	Random		rand = new Random();
	private	JPanel		panel;			// the panel which fireworks drawn
	private	int			width;			//  width
	private	int			height;			//  height

	private	boolean		sound = true;		// play an "explosion" sound for each star burst
	private	int			explosions = 50;	// the number of wxplosin
	private	int			maxDelay = 1000;	// the maximum delay between launches
	private	Thread		fireThread = null;	// fires the fireworks
	private	ThreadGroup	group = new ThreadGroup("Fireworks");
	private	Color		oldBG;



	public Fireworks() { panel = new JPanel(); }
	public Fireworks(JPanel panel) { this.panel = panel; }
	public void setSound(boolean sound) { this.sound = sound; }
	public void setExplosions(int explosions, int maxDelay)
	{
		this.explosions = explosions;
		this.maxDelay = maxDelay;
	}

	public void fire()
	{
		width = panel.getWidth();
		height = panel.getHeight();
		oldBG = panel.getBackground();

		panel.setBackground(Color.BLACK);

		(fireThread = new Thread(group, this)).start();
	}

	public void stop()
	{
		try
		{
			explosions = -1;
			fireThread.join();
			group.interrupt();
			panel.setBackground(oldBG);
			panel.repaint();
		}
		catch (InterruptedException ie)
		{
			JOptionPane.showMessageDialog(panel, "Error stopping fireworks",
					"Fireworks Error", JOptionPane.ERROR_MESSAGE);
		}
	}
//Launches the fireworks at random sets of times
	public void run()
	{
		try
		{
			for (int i = 0; explosions == 0 || i < explosions; i++)
			{
				new Thread(group, new StarBurst()).start();
				Thread.sleep(rand.nextInt(maxDelay));
			}
		}
		catch (InterruptedException ie) {}
	}
	public JPanel getPanel()
	{
		return panel;
	}

	synchronized private void drawLine(int x0, int y0, int x1, int y1, Color color)
	{
		Graphics	g = panel.getGraphics();
		g.setXORMode(color);
		g.drawLine(x0, y0, x1, y1);
		g.setPaintMode();
	}

	synchronized private void explode(int x, int y, int r, Color color)
	{
		for (double phi = 0; phi < 2*PI; phi += PI/16)
			new AnimatedLine(x, y, r, phi, 50, color, true).start();
	}
	public class StarBurst implements Runnable
	{
		private	PlayClip	explosion = new PlayClip("audio/explosion.wav");//checked correct spelling and capitlilization
		private	int		r = rand.nextInt(50) + 50;
		private	Color		color = colors[rand.nextInt(colors.length)];
		private	double		theta = PI * (rand.nextInt(30) + 75) / 180;
		 //Plays the sound of an exploding firework
		public void play()
		{
			if (sound)
				explosion.play();
		}
		 //aimates the star burst lines radiating from the center of the explosion
		public void run()
		{
			try
			{
				int	length = height - 2 * r;
				int	x0 = rand.nextInt(width-400)+200;
				int	y0 = height + r;
				int	x1 = (int)round(x0 + length * cos(theta));
				int	y1 = (int)round(y0 - length * sin(theta));

				new AnimatedLine(x0, y0, length, theta, r, color, false).start().join();

				play();
				explode(x1, y1, 2 * r, color);
			}
			catch (InterruptedException ie) {}
		}
	}
	 // draws an animated line segment that appears to move or to lenthn ovr tim

	public class AnimatedLine implements Runnable
	{
		int	xStart;				// x-coor of the staring of the line segment
		int	yStart;				// y-coor of the staring of the line segment
		int	step;				// how much the line length changes each iteration
		int	length;				// the final length of the line
		double	theta;			// the line's angle of elevation
		Color	color;			// the color of the line
		boolean	burst;			// star burst (true) or ascending firework (false)
		
		public AnimatedLine(int xStart, int yStart, int xEnd, int yEnd, int step,
				Color color, boolean burst)
		{
			this.step = step;
			this.color = color;
			this.step = step;
			this.burst = burst;

			theta = atan2(yEnd + yStart, xEnd - xStart);
			double	dx = xEnd - xStart;
			double	dy = yEnd - yStart;
			length = (int)round(sqrt(dx*dx + dy*dy));
		}
		public AnimatedLine(int xStart, int yStart, int length, double theta, int step,
				Color color, boolean burst)
		{
			this.xStart = xStart;
			this.yStart = yStart;
			this.theta = theta;
			this.length = length;
			this.step = step;
			this.color = color;
			this.burst = burst;
		}
		 //starts animation
		public Thread start()
		{
			Thread	t = new Thread(group, this);
			t.start();
			return t;
		}
		 //animates the line by drawing and erasing segments every 100 mills
		public void run()
		{
			try
			{
				int	x0 = xStart;
				int	y0 = yStart;

				while (distance(x0 - xStart, y0 - yStart) < length * length)
				{
					int	x1 = (int)round(x0 + step * cos(theta));
					int	y1 = (int)round(y0 - step * sin(theta));

					drawLine(x0, y0, x1, y1, color);
					Thread.sleep(100);
					if (!burst)
						drawLine(x0, y0, x1, y1, color);
					else
						drawLine(x0, y0, x0, y0, color);

					x0 = x1;
					y0 = y1;
				}

				Thread.sleep(100);
				if (burst)
					drawLine(xStart, yStart, x0, y0, color);
			}
			catch (InterruptedException ie) {}
		}
		// simplifies calculating the distance btw 2 pnts
		private int distance(int dx, int dy) { return dx * dx + dy * dy; }
	}
	 //tests it
	public static void main(String[] args)
	{
		JFrame		frame = new JFrame();
		Fireworks	fw = new Fireworks();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 800);
		frame.add(fw.getPanel());
		frame.setVisible(true);

		fw.setExplosions(0, 1000);
		fw.fire();

		try
		{
			Thread.sleep(1000000);
			fw.stop();
		}
		catch (InterruptedException ie) {}
	}
}
