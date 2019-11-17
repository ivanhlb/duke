package main.duke.command;

import main.duke.exception.DukeUnknownException;
import main.duke.storage.Storage;
import main.duke.task.*;
import main.duke.ui.Ui;

public class UpdateCommand extends Command {


    public enum Operation {
        Done, Delete, Add;
    }

    private Operation operation;
    private int position;
    private Task task;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (operation) {
            case Add:
                tasks.add(task);
                ui.printAddMsg(tasks);
                break;
            case Done:
                ui.printDoneMsg(tasks, position);
                tasks.done(position);
                break;
            case Delete:
                ui.printDeleteMsg(tasks.remove(position), tasks);
                break;
            default:
                ui.printErrorMsg(new DukeUnknownException());
                break;
        }
    }

    public UpdateCommand(Operation operation, int pos) {
        this.operation = operation;
        position = pos;
    }

    public UpdateCommand(Operation operation, Task task) {
        this.operation = operation;
        this.task = task;
    }
}