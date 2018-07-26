package controller.commands;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import view.interfaces.IGuiWindow;

import java.io.IOException;

public class SelectShapesCommand implements ICommand, IUndoable {

    private IGuiWindow guiWindow;

    public SelectShapesCommand(IGuiWindow guiWindow){
        this.guiWindow = guiWindow;
    }
    @Override
    public void run() throws IOException {
        System.out.println("Made it to SelectShape Command");

        // add command to CommandHistory
        CommandHistory.add(this);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}