package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

public class F01Test {
    private AppController appController;

    @Before
    public void setupController() {
        appController = new AppController();
    }

    /**
     * Test ECP1
     * @throws DuplicateIntrebareException
     * @throws InputValidationFailedException
     */
    @Test
    public void ecp1_valid() {
        try {
            Intrebare newIntrebare = appController.addNewIntrebare("Enunt?", "1) R1", "2) R2","3) R3",
                    "1", "Domeniu");
            assert appController.exists(newIntrebare);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }
    }

    /**
     * Test ECP2
     * @throws DuplicateIntrebareException
     * @throws InputValidationFailedException
     */
    @Test
    public void ecp2_valid() {
        try {
            Intrebare intrebare = appController.addNewIntrebare("Ce se poate afirma?", "1) raspuns1", "2) raspuns2","3) raspuns3",
                    "2", "DomeniuMare");
            assert appController.exists(intrebare);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }
    }

    /**
     * Test ECP3
     * @throws DuplicateIntrebareException
     * @throws InputValidationFailedException
     */
    @Test
    public void ecp1_invalid() {
        try {
            appController.addNewIntrebare("Enunt?", "1) R1", "2) R2","3 R3",
                    "1", "Domeniu");
            assert false;
        } catch (DuplicateIntrebareException e) {
            assert false;
        } catch (InputValidationFailedException e) {
            assert true;
        }
    }

    /**
     * Test ECP4
     * @throws DuplicateIntrebareException
     * @throws InputValidationFailedException
     */
    @Test
    public void ecp2_invalid() {
        try {
            appController.addNewIntrebare("intrebare?", "1) R1", "2) R2","3) R3",
                    "1", "Domeniu");
            assert false;
        } catch (DuplicateIntrebareException e) {
            assert false;
        } catch (InputValidationFailedException e) {
            assert true;
        }
    }

    /**
     * Test BVA1
     * @throws DuplicateIntrebareException
     * @throws InputValidationFailedException
     */
    @Test
    public void bva1_invalid() {
        try {
            appController.addNewIntrebare("", "1) R1", "2) R2","3) R3",
                    "1", "Domeniu");
            assert false;
        } catch (DuplicateIntrebareException e) {
            assert false;
        } catch (InputValidationFailedException e) {
            assert true;
        }
    }

    /**
     * Test BVA2
     * @throws DuplicateIntrebareException
     * @throws InputValidationFailedException
     */
    @Test
    public void bva2_invalid() {
        try {
            appController.addNewIntrebare("D", "1) R1", "2) R2","3) R3",
                    "1", "Domeniu");
            assert false;
        } catch (DuplicateIntrebareException e) {
            assert false;
        } catch (InputValidationFailedException e) {
            assert true;
        }
    }

    /**
     * Test BVA3
     * @throws DuplicateIntrebareException
     * @throws InputValidationFailedException
     */
    @Test
    public void bva1_valid() {
        try {
            Intrebare newIntrebare = appController.addNewIntrebare("C?", "1) R1", "2) R2","3) R3",
                    "1", "D");
            assert appController.exists(newIntrebare);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }
    }

    /**
     * Test BVA4
     * @throws DuplicateIntrebareException
     * @throws InputValidationFailedException
     */
    @Test
    public void bva2_valid() {
        try {
            Intrebare intrebare = appController.addNewIntrebare("Enunt?", "1) R1", "2) R2","3) R3",
                    "1", "DDDDDDDDDDDDDDDDDDDDDDDDDDDD12");
            assert appController.exists(intrebare);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }
    }
}