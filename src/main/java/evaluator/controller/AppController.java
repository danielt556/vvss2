package evaluator.controller;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.model.Test;
import evaluator.repository.IntrebariRepository;
import evaluator.repository.TestRepository;

import java.util.LinkedList;
import java.util.List;

public class AppController {
	
	private IntrebariRepository intrebariRepository;
	private TestRepository testRepository;
	
	public AppController() {
		intrebariRepository = new IntrebariRepository();
		testRepository = new TestRepository();
	}
	
	public Intrebare addNewIntrebare(String enunt, String varianta1, String varianta2, String varianta3,
									 String variantaCorecta, String domeniu)
			throws DuplicateIntrebareException, InputValidationFailedException {

		Intrebare intrebare = new Intrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu);

		intrebariRepository.addIntrebare(intrebare);
		
		return intrebare;
	}
	
	public boolean exists(Intrebare intrebare){
		return intrebariRepository.exists(intrebare);
	}
	
	public Test createNewTest() throws NotAbleToCreateTestException {
		List<Intrebare> testIntrebari = new LinkedList<>();
		List<String> domenii = new LinkedList<>();
		Intrebare intrebare;
		Test test = new Test();

		if(intrebariRepository.getIntrebari().size() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");
		
		if(intrebariRepository.getNumberOfDistinctDomains() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");
		
		while(testIntrebari.size() != 5){
			intrebare = intrebariRepository.pickRandomIntrebare();
			
			if(!testIntrebari.contains(intrebare) && !domenii.contains(intrebare.getDomeniu())) {
				testIntrebari.add(intrebare);
				domenii.add(intrebare.getDomeniu());
			}
			
		}
		
		test.setIntrebari(testIntrebari);
		this.testRepository.addTest(test);

		return test;
		
	}
	
	public void loadIntrebariFromFile(String f){
		intrebariRepository.setIntrebari(intrebariRepository.loadIntrebariFromFile(f));
	}
	
	public Statistica getStatistica() throws NotAbleToCreateStatisticsException {
		
		if(intrebariRepository.getIntrebari().isEmpty())
			throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");
		
		Statistica statistica = new Statistica();
		for(String domeniu : intrebariRepository.getDistinctDomains()){
			statistica.add(domeniu, intrebariRepository.getIntrebariByDomain(domeniu).size());
		}
		
		return statistica;
	}

	public List<Test> getAllTests() {
		return this.testRepository.getTeste();
	}
}
