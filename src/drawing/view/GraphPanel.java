package drawing.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GraphPanel extends JPanel
{
	private int [] data = {27, 5, 54, 10};
	private int width;
	private ArrayList<Rectangle> graph;
	
	public GraphPanel(int [] data)
	{
		if(data != null)
		{
			this.data = data;
		}
		this.width = 10;
		graph = new ArrayList<Rectangle>();
		
		this.setMinimumSize(new Dimension(250, 250));
	}
	
	@Override
	protected void paintComponent(Graphics basic)
	{
		graph.clear();
		this.setBackground(new Color(138, 43, 226));
		if(this != null)
		{
			width = this.getWidth()/data.length;
		}
		for(int index = 0; index < data.length; index++)
		{
			int xPoint = index * width;
			int yPoint = this.getHeight() - data[index];
			Rectangle current = new Rectangle(xPoint, yPoint, width, data[index]);
			graph.add(current);
		}
		Graphics2D drawing = (Graphics2D) basic;
		for(Rectangle current : graph)
		{
			int red = (int) (Math.random() * 256);
			int green = (int) (Math.random() * 256);
			int blue = (int) (Math.random() * 256);
			int alpha = (int) (Math.random() * 256);
			Color random = new Color(red, green, blue, alpha);
			drawing.setColor(random);
			drawing.fill(current);
		}
	}
}
