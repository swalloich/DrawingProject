package drawing.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import drawing.controller.Controller;

public class DrawingPanel extends JPanel
{
	private ShapePanel shapePanel;
	private JButton rectangleButton;
	private JButton ellipseButton;
	private JButton triangleButton;
	private JButton circleButton;
	private JButton polygonButton;
	private JButton save;
	private SpringLayout baseLayout;
	private Controller baseController;
	
	public DrawingPanel(Controller baseController)
	{
		super();
		this.baseController = baseController;
		
		shapePanel = new ShapePanel(baseController);
		rectangleButton = new JButton("rectangle");
		save = new JButton("Save Result");
		baseLayout = new SpringLayout();
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setMinimumSize(new Dimension(600, 600));
		this.add(rectangleButton);
		this.add(shapePanel);
		this.add(circleButton);
		this.add(ellipseButton);
		this.add(triangleButton);
		this.add(polygonButton);
		this.add(save);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.NORTH, shapePanel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, shapePanel, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, shapePanel, 0, SpringLayout.EAST, save);
		baseLayout.putConstraint(SpringLayout.EAST, rectangleButton, 0, SpringLayout.EAST, save);
		baseLayout.putConstraint(SpringLayout.EAST, shapePanel, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, rectangleButton, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, save, 6, SpringLayout.SOUTH, rectangleButton);
		baseLayout.putConstraint(SpringLayout.WEST, save, 0, SpringLayout.WEST, rectangleButton);
		baseLayout.putConstraint(SpringLayout.NORTH, rectangleButton, 0, SpringLayout.NORTH, shapePanel);
	}
	
	private void setupListeners()
	{
		rectangleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				shapePanel.addRectangles();
			}
		});
		
		save.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String path = JOptionPane.showInputDialog("Please enter the path you want to save the image to.");
				System.out.println(path.indexOf(path.length(), path.length() + 1));
				if(path.substring(path.length(), path.length() + 1).equals("/"))
				{
					
				}
				String name = JOptionPane.showInputDialog("Please enter a name for the image.");
				shapePanel.saveImage(path, name);
			}
		});
		
		ellipseButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				shapePanel.addEllipses();
			}
		});
		
		circleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				shapePanel.addCircles();
			}
		});
		
		triangleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				shapePanel.addTriangles();
			}
		});
		
		polygonButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				shapePanel.addPolygons();
			}
		});
	}
}
