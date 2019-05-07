package evaluator.repository;

import evaluator.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRepository {
    private List<Test> teste;


    public TestRepository() {
        this.teste = new ArrayList<>();
    }

    public void addTest(Test test) {
        this.teste.add(test);
    }

    public List<Test> getTeste() {
        return this.teste;
    }
}
