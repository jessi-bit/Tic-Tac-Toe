package tester;

import controller.Controller;
import model.*;
import view.*;

public class Tester {

	public static void main(String[] args) {
		Status model = new Status();
		ViewFrame frame = new ViewFrame();
		Controller controller = Controller.getInstance(model, frame);
		controller.addObserver(model);
		model.addObserver(controller);
	}

}
