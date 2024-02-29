package view;

import controller.IContrl;

public class RunExampleCommand extends Command{
    private IContrl controller;

    public RunExampleCommand(String key, String description, IContrl controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute(){
        try{
            controller.oneStepExecution();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public IContrl getController(){
        return this.controller;
    }

    public String toString(){
        return this.getKey() + " " + this.getDescription();
    }
}
