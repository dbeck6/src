package controller.commands;

import model.ShapeType;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IDrawShapesStrategy;
import model.interfaces.IUndoable;
import model.shapes.Ellipse;
import model.shapes.Rectangle;
import model.shapes.ShapeConfiguration;
import model.shapes.Triangle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DrawShapeCommand implements ICommand, IUndoable {

    private Graphics2D graphics;
    private IApplicationState appState;
    private Point start, end;
    private ShapeConfiguration shapeConfiguration = new ShapeConfiguration();
    private ArrayList<IDrawShapesStrategy> shapes;


    public DrawShapeCommand(Graphics2D graphics, IApplicationState appState, ArrayList<IDrawShapesStrategy> shapes, Point start, Point end) {
        this.graphics = graphics;
        this.appState = appState;
        this.shapes = shapes;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() throws IOException {
        appState.getCurrentShapeConfiguration(shapeConfiguration);

        // maybe make this a switch statement
        if(shapeConfiguration.shapeType == ShapeType.RECTANGLE){
            IDrawShapesStrategy rectangle = new Rectangle(graphics, shapeConfiguration, start, end);
            shapes.add(rectangle);
            rectangle.drawShapes();
        } else if(shapeConfiguration.shapeType == ShapeType.ELLIPSE){
            IDrawShapesStrategy ellipse = new Ellipse(graphics, shapeConfiguration, start, end);
            shapes.add(ellipse);
            ellipse.drawShapes();
        } else if(shapeConfiguration.shapeType == ShapeType.TRIANGLE){
            IDrawShapesStrategy triangle = new Triangle(graphics, shapeConfiguration, start, end);
            shapes.add(triangle);
            triangle.drawShapes();
        } else {throw new IOException();}

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        // needs to destroy shape somehow...
    }

    @Override
    public void redo() {

    }
}
