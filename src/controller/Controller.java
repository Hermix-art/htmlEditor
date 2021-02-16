package controller;

import view.View;

public class Controller {
    private View view;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);

        view.init();
        controller.init();

    }

    public void init(){

    }
    public void exit(){
        System.exit(0);
    }
}
