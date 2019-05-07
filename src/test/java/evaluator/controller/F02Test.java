package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

public class F02Test {
    private AppController appController;

    @Before
    public void setupController() {
        appController = new AppController();
    }

    @Test
    public void tc1() {
        try {
            this.appController.createNewTest();
            assert false;
        } catch (NotAbleToCreateTestException e) {
            assert true;
        }
    }

    @Test
    public void tc2() {
        try {
            this.appController.addNewIntrebare("Q1?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain1");
            this.appController.addNewIntrebare("Q2?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain1");
            this.appController.addNewIntrebare("Q3?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain2");
            this.appController.addNewIntrebare("Q4?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain2");
            this.appController.addNewIntrebare("Q5?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain3");

            this.appController.createNewTest();
            assert false;
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        } catch (NotAbleToCreateTestException e) {
            assert e.getMessage().equals("Nu exista suficiente intrebari pentru crearea unui test!(5)");
        }
    }

    @Test
    public void tc3() {
        try {
            this.appController.addNewIntrebare("Q1?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain1");
            this.appController.addNewIntrebare("Q2?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain2");
            this.appController.addNewIntrebare("Q3?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain3");
            this.appController.addNewIntrebare("Q4?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain4");
            this.appController.addNewIntrebare("Q5?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain5");

            this.appController.createNewTest();
            assert this.appController.getAllTests().size() == 1;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }
    }

    @Test
    public void tc4() {
        try {
            this.appController.addNewIntrebare("Q1?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain1");
            this.appController.addNewIntrebare("Q2?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain2");
            this.appController.addNewIntrebare("Q3?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain3");
            this.appController.addNewIntrebare("Q4?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain4");
            this.appController.addNewIntrebare("Q5?", "1) A1.", "2) A2.", "3) A3.",
                    "1", "Domain5");

            this.appController.createNewTest();
            assert this.appController.getAllTests().size() == 1;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }
    }
}
