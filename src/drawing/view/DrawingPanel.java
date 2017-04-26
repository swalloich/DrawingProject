package drawing.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	private DrawingFrame baseFrame;

	public DrawingPanel(Controller baseController, DrawingFrame baseFrame)
	{
		super();
		this.baseController = baseController;
		this.baseFrame = baseFrame;

		shapePanel = new ShapePanel(baseController);
		rectangleButton = new JButton("rectangle");
		circleButton = new JButton("Circles");
		polygonButton = new JButton("Polygons");
		triangleButton = new JButton("Triangles");
		ellipseButton = new JButton("Ellipses");
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
		baseLayout.putConstraint(SpringLayout.NORTH, rectangleButton, 0, SpringLayout.NORTH, shapePanel);
		baseLayout.putConstraint(SpringLayout.EAST, polygonButton, 0, SpringLayout.EAST, triangleButton);
		baseLayout.putConstraint(SpringLayout.EAST, triangleButton, 0, SpringLayout.EAST, ellipseButton);
		baseLayout.putConstraint(SpringLayout.EAST, ellipseButton, 0, SpringLayout.EAST, circleButton);
		baseLayout.putConstraint(SpringLayout.EAST, circleButton, 0, SpringLayout.EAST, save);
		baseLayout.putConstraint(SpringLayout.NORTH, polygonButton, 6, SpringLayout.SOUTH, triangleButton);
		baseLayout.putConstraint(SpringLayout.WEST, polygonButton, 0, SpringLayout.WEST, rectangleButton);
		baseLayout.putConstraint(SpringLayout.NORTH, triangleButton, 6, SpringLayout.SOUTH, ellipseButton);
		baseLayout.putConstraint(SpringLayout.WEST, triangleButton, 0, SpringLayout.WEST, rectangleButton);
		baseLayout.putConstraint(SpringLayout.NORTH, ellipseButton, 6, SpringLayout.SOUTH, circleButton);
		baseLayout.putConstraint(SpringLayout.WEST, ellipseButton, 0, SpringLayout.WEST, rectangleButton);
		baseLayout.putConstraint(SpringLayout.WEST, save, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, circleButton, 6, SpringLayout.SOUTH, save);
		baseLayout.putConstraint(SpringLayout.WEST, circleButton, 0, SpringLayout.WEST, rectangleButton);
	}

	private String endWithPng(String input)
	{
		String output = "";

		System.out.println(input);
		if (input.contains(".png") && input.substring((input.length() - 4)).equalsIgnoreCase(".png"))
		{
			output = input;
			System.out.println(output);
		} 
		else if (!input.contains(".png") && !(input.contains(".jpg") || input.contains(".jpeg")))
		{
			output = input + ".png";
			System.out.println(output);
		}
		else if (input.substring((input.length() - 4)).equalsIgnoreCase(".jpg"))
		{
			output = input.substring(0, input.length() - 4) + ".png";
			System.out.println(output);
		} 
		else if (input.contains("jpeg") && input.substring((input.length() - 5)).equalsIgnoreCase(".JPEG"))
		{
			output = input.substring(0, input.length() - 5) + ".png";
			System.out.println(output);
		}

		return output;
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
				String path = shapePanel.selectPath(shapePanel);
				System.out.println(path);
				if(!path.substring(path.length()).equalsIgnoreCase("/"))
				{
					path += "/";
					System.out.println(path);
				}

				// if(!path.substring(path.length()).equals("/"))
				// {
				// path += "/";
				// }

				String name = JOptionPane.showInputDialog("Please enter a name for the image.");
				name = endWithPng(name);
				try
				{
					shapePanel.saveImage(path, name);
				} 
				catch (Exception error)
				{
					path = JOptionPane.showInputDialog("Invalid file path");
				}
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
