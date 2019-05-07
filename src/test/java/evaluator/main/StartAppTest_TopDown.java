package evaluator.main;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.repository.IntrebariRepository;
import org.junit.Test;

import java.util.List;

public class StartAppTest_TopDown {
    private AppController appController;

    @Test
    public void integrareA() {
        // P -> A

        this.appController = new AppController();

        String intrebare = "Intrebare?";
        String varianta1 = "1) a";
        String varianta2 = "2) b";
        String varianta3 = "3) c";
        String variantaCorecta = "2";
        String domeniu = "Domeniu";

        try {
            this.appController.addNewIntrebare(intrebare, varianta1, varianta2, varianta3, variantaCorecta, domeniu);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }

        try {
            assert this.appController.exists(new Intrebare(intrebare, varianta1, varianta2, varianta3, variantaCorecta,
                    domeniu));
        } catch (InputValidationFailedException e) {
            assert false;
        }
    }

    @Test
    public void integrareB() {
        // P -> A -> B

        this.appController = new AppController();

        String intrebare = "Intrebare?";
        String varianta1 = "1) a";
        String varianta2 = "2) b";
        String varianta3 = "3) c";
        String variantaCorecta = "2";
        String domeniu = "Domeniu";

        try {
            this.appController.addNewIntrebare(intrebare, varianta1, varianta2, varianta3, variantaCorecta, domeniu);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }

        try {
            assert this.appController.exists(new Intrebare(intrebare, varianta1, varianta2, varianta3, variantaCorecta,
                    domeniu));
        } catch (InputValidationFailedException e) {
            assert false;
        }

        try {
            this.appController.addNewIntrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1");
            this.appController.addNewIntrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2");
            this.appController.addNewIntrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3");
            this.appController.addNewIntrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4");

            this.appController.createNewTest();
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }

        assert this.appController.getAllTests().size() == 1;
    }

    @Test
    public void integrareC() {
        // P -> A -> B -> C

        this.appController = new AppController();

        String intrebare = "Intrebare?";
        String varianta1 = "1) a";
        String varianta2 = "2) b";
        String varianta3 = "3) c";
        String variantaCorecta = "2";
        String domeniu = "Domeniu";

        try {
            this.appController.addNewIntrebare(intrebare, varianta1, varianta2, varianta3, variantaCorecta, domeniu);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }

        try {
            assert this.appController.exists(new Intrebare(intrebare, varianta1, varianta2, varianta3, variantaCorecta,
                    domeniu));
        } catch (InputValidationFailedException e) {
            assert false;
        }

        try {
            this.appController.addNewIntrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1");
            this.appController.addNewIntrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2");
            this.appController.addNewIntrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3");
            this.appController.addNewIntrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4");

            this.appController.createNewTest();

            assert this.appController.getAllTests().size() == 1;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }

        try {
            Statistica statistica = this.appController.getStatistica();

            assert statistica.getIntrebariDomenii().get("Domeniu") == 1;
        } catch (NotAbleToCreateStatisticsException e) {
            assert false;
        }
    }

    @Test
    public void F01() {
        this.appController = new AppController();
        try {
            Intrebare newIntrebare = appController.addNewIntrebare("Enunt?", "1) R1", "2) R2","3) R3",
                    "1", "Domeniu");
            assert appController.exists(newIntrebare);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }
    }

    @Test
    public void F02() {
        this.appController = new AppController();
        try {
            this.appController.addNewIntrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1");
            this.appController.addNewIntrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2");
            this.appController.addNewIntrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3");
            this.appController.addNewIntrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4");
            this.appController.addNewIntrebare("Intrebare5?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu5");

            this.appController.createNewTest();
            assert this.appController.getAllTests().size() == 1;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }
    }

    @Test
    public void F03() {
        IntrebariRepository intrebariRepository = new IntrebariRepository();

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("Intrebare?", "1) a", "2) b", "3) c",
                    "1", "Domeniu");
        } catch (InputValidationFailedException e) {
            assert false;
        }
        try {
            intrebariRepository.addIntrebare(intrebare);
        } catch (DuplicateIntrebareException e) {
            assert false;
        }

        List<Intrebare> l = intrebariRepository.getIntrebariByDomain("Domeniu");
        assert l.size() == 1;
    }
}