package evaluator.repository;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class F03Test {
    private IntrebariRepository intrebariRepository;

    @Before
    public void setUp() {
        intrebariRepository = new IntrebariRepository();

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
    }

    @Test
    public void getIntrebariByDomainValid() {
        List<Intrebare> l = this.intrebariRepository.getIntrebariByDomain("Domeniu");
        assert l.size() == 1;
    }

    @Test
    public void getIntrebariByDomainNonvalid() {
        List<Intrebare> l = this.intrebariRepository.getIntrebariByDomain("-1");
        assert l.size() == 0;
    }
}