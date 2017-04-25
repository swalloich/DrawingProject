package drawing.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import drawing.controller.Controller;

@SuppressWarnings("serial")
public class DrawingFrame extends JFrame
{
	private Controller controller;
	private DrawingPanel basePanel;
	
	public DrawingFrame(Controller controller)
	{
		super();
		this.controller = controller;
		basePanel = new DrawingPanel(controller);
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("memes");
		this.setSize(new Dimension(600, 600));
		this.setVisible(true);
	}
}
