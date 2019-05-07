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

import static org.junit.Assert.*;

public class StartAppTest_BigBang {
    private AppController appController;

    @Test
    public void integrareP() {
        // P -> A -> B -> C
        this.appController = new AppController();

        try {
            this.appController.addNewIntrebare("Intrebare?", "1) a", "2) b", "3) c",
                    "1", "Domeniu");

            assert this.appController.exists(new Intrebare("Intrebare?", "1) a", "2) b",
                    "3) c","1", "Domeniu"));
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }

        try {
            this.appController.addNewIntrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2");
            this.appController.addNewIntrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3");
            this.appController.addNewIntrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4");
            this.appController.addNewIntrebare("Intrebare5?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu5");

            assert this.appController.createNewTest().getIntrebari().size() == 5;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }

        try {
            Statistica statistica = this.appController.getStatistica();

            assert statistica.getIntrebariDomenii().get("Domeniu") == 1;
            assert statistica.getIntrebariDomenii().get("Domeniu2") == 1;
            assert statistica.getIntrebariDomenii().get("Domeniu3") == 1;
            assert statistica.getIntrebariDomenii().get("Domeniu4") == 1;
            assert statistica.getIntrebariDomenii().get("Domeniu5") == 1;
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