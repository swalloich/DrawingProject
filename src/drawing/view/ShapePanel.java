package drawing.view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import drawing.controller.Controller;

public class ShapePanel extends JPanel
{
	private Controller baseController;
	private ArrayList<Shape> rectangleList;
	private ArrayList<Shape> triangleList;
	private ArrayList<Shape> circleList;
	private ArrayList<Shape> ellipseList;
	private ArrayList<Shape> polygonList;
	private ArrayList<ArrayList<Shape>> shapes;
	
	public ShapePanel(Controller baseController)
	{
		super();
		this.baseController = baseController;
		rectangleList = new ArrayList<Shape>();
		circleList = new ArrayList<Shape>();
		ellipseList = new ArrayList<Shape>();
		triangleList = new ArrayList<Shape>();
		polygonList = new ArrayList<Shape>();
		shapes = new ArrayList<ArrayList<Shape>>();
		
		setupPanel();
		buildShapes();
	}
	
	private void setupPanel()
	{
		this.setBackground(Color.CYAN);
		this.setMinimumSize(new Dimension(250, 500));
	}
	
	private void buildShapes()
	{
		shapes.add(rectangleList);
		shapes.add(triangleList);
		shapes.add(circleList);
		shapes.add(ellipseList);
		shapes.add(polygonList);
	}
	
	private Color getRandomColor()
	{
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		int alpha = (int) (Math.random() * 255);
		
		return new Color(red, green, blue, alpha);
	}
	
	public void addRectangles()
	{
		for(int index = 0; index < 500; index++)
		{
			int width = (int) (Math.random() * 120) + 1;
			int height = (int) (Math.random() * 150) + 15;
			int xCorner = (int) (Math.random() * this.getWidth() - 15);
			int yCorner = (int) (Math.random() * this.getHeight() - 15);
			
			Rectangle currentRectangle = new Rectangle(xCorner, yCorner, width, height);
			rectangleList.add(currentRectangle);
		}
		
		this.repaint();
	}
	
	private void drawShapes(ArrayList<Shape> shapeList, Graphics2D graphics)
	{
		for(Shape currentShape : shapeList)
		{
			graphics.setColor(getRandomColor());
			int strokeWidth = (int) (Math.random() * 10) + 1;
			graphics.setStroke(new BasicStroke(strokeWidth));
			
			int randomness = (int) (Math.random() * 35);
			
			if(randomness % 5 == 0 || randomness % 7 == 0)
			{
				graphics.fill(currentShape);
				graphics.setColor(getRandomColor());
				graphics.draw(currentShape);
			}
			else
			{
				graphics.draw(currentShape);
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics graphics)
	{
//		this.setBackground(getRandomColor()); no
		
		Graphics2D drawingGraphics = (Graphics2D) graphics;
		
		for(ArrayList<Shape> list: shapes)
		{
			drawShapes(list, drawingGraphics);
		}
	}
	
	public void addCircles()
	{
		if(circleList.size() > 500)
		{
			circleList.clear();
		}
		
		for(int index = 0; index < 30; index++)
		{
			int radius = (int) (Math.random() * 25) + 2;
			int xCorner = (int) (Math.random() * this.getWidth() - 15);
			int yCorner = (int) (Math.random() * this.getHeight() - 15);
			Ellipse2D.Double current = new Ellipse2D.Double(xCorner, yCorner, radius, radius);
			circleList.add(current);
		}
		this.repaint();
	}
	
	public void addTriangles()
	{
		if(triangleList.size() > 500)
		{
			triangleList.clear();
		}
		
		for(int index = 0; index < 30; index++)
		{
			int vertexCount = 3;
			int [] xVertices = new int [vertexCount];
			int [] yVertices = new int [vertexCount];
			for(int vertex = 0; vertex < vertexCount; vertex++)
			{
				int yCorner = (int) (Math.random() * this.getWidth());
				int xCorner = (int) (Math.random() * this.getHeight());
				yVertices[vertex] = yCorner;
				xVertices[vertex] = xCorner;
			}
			
			Polygon current = new Polygon(xVertices, yVertices, vertexCount);
			triangleList.add(current);
		}
		this.repaint();
	}
	
	public void addPolygons()
	{
		if(polygonList.size() > 500)
		{
			polygonList.clear();
		}
		
		for(int index = 0; index < 30; index++)
		{
			int vertexCount = (int) (Math.random() * 7) + 4;
			int [] xVertices = new int [vertexCount];
			int [] yVertices = new int [vertexCount];
			for(int vertex = 0; vertex < vertexCount; vertex++)
			{
				int yCorner = (int) (Math.random() * this.getWidth());
				int xCorner = (int) (Math.random() * this.getHeight());
				yVertices[vertex] = yCorner;
				xVertices[vertex] = xCorner;
			}
			
			Polygon current = new Polygon(xVertices, yVertices, vertexCount);
			triangleList.add(current);
		}
		this.repaint();
	}
	
	public void addEllipses()
	{
		if(ellipseList.size() > 500)
		{
			ellipseList.clear();
		}
		
		for(int index = 0; index < 30; index++)
		{
			int xRadius = (int) (Math.random() * 50) * 2;
			int yRadius = (int) (Math.random() * 50) + 3;
			int xCorner = (int) (Math.random() * this.getWidth() - 15);
			int yCorner = (int) (Math.random() * this.getHeight() - 15);
			Ellipse2D.Double current = new Ellipse2D.Double(xCorner, yCorner, xRadius, yRadius);
			ellipseList.add(current);
		}
		this.repaint();
	}
	
	public void reset()
	{
		for(int index = 0; index < shapes.size(); index++)
		{
			shapes.get(index).clear();
		}
		this.setBackground(getRandomColor());
		this.repaint();
	}
	
	public String selectPath(Component parent)
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		if(chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
		{
			return chooser.getSelectedFile().getAbsolutePath();
		}
		
		return null;
		
	}
	
	public void saveImage(String filePath, String fileName)
	{
		BufferedImage image = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB);
		Graphics graphic = image.createGraphics();
		
		Color background = new Color(getBackground().getRed(), getBackground().getGreen(), getBackground().getBlue(),getBackground().getAlpha());
		graphic.setColor(background);
		graphic.fillRect(0, 0, this.getSize().width, this.getSize().height);
		this.printAll(graphic);
		graphic.dispose();
		
		try
		{
			ImageIO.write(image, "png", new File(filePath + fileName));
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(this, "Unable to write to the destination.");
		}
	}
}