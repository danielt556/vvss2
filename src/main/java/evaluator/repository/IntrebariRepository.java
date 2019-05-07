package evaluator.repository;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IntrebariRepository {

    private List<Intrebare> intrebari;

    public IntrebariRepository() {
        setIntrebari(new LinkedList<Intrebare>());
    }

    public void addIntrebare(Intrebare i) throws DuplicateIntrebareException {
        if(exists(i))
            throw new DuplicateIntrebareException("Intrebarea deja exista!");
        intrebari.add(i);
    }

    public boolean exists(Intrebare i){
        for(Intrebare intrebare : intrebari)
            if(intrebare.equals(i))
                return true;
        return false;
    }

    public Intrebare pickRandomIntrebare(){
        Random random = new Random();
        return intrebari.get(random.nextInt(intrebari.size()));
    }

    public int getNumberOfDistinctDomains(){
        return getDistinctDomains().size();

    }

    public Set<String> getDistinctDomains(){
        Set<String> domains = new TreeSet<String>();
        for(Intrebare intrebre : intrebari)
            domains.add(intrebre.getDomeniu());
        return domains;
    }

    public List<Intrebare> getIntrebariByDomain(String domain){
        List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
        for(Intrebare intrebare : intrebari){
            if(intrebare.getDomeniu().equals(domain)){
                intrebariByDomain.add(intrebare);
            }
        }

        return intrebariByDomain;
    }

    public int getNumberOfIntrebariByDomain(String domain){
        return getIntrebariByDomain(domain).size();
    }

    public List<Intrebare> loadIntrebariFromFile(String f){

        List<Intrebare> intrebari = new LinkedList<Intrebare>();
        BufferedReader br = null;
        String line = null;
        List<String> intrebareAux;
        Intrebare intrebare;

        try {
            br = new BufferedReader(new FileReader(f));
            line = br.readLine();
            while(line != null){
                intrebare = new Intrebare();
                List<String> components = Arrays.asList(line.split(";"));
                try {
                    intrebare.setEnunt(components.get(0));
                    intrebare.setVarianta1(components.get(1));
                    intrebare.setVarianta2(components.get(2));
                    intrebare.setVarianta3(components.get(3));
                    intrebare.setVariantaCorecta(components.get(4));
                    intrebare.setDomeniu(components.get(5));
                    intrebari.add(intrebare);
                } catch (InputValidationFailedException e) {
                    // daca intrebarea nu e corecta doar trec la urmatoarea din fisier
                }
                line = br.readLine();
            }

        }
        catch (IOException e) {
            // daca nu pot citii din fisier trec mai departe
        } finally{
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // daca nu pot inchide BufferReader-ul trec mai departe
                }
            }
        }

        return intrebari;
    }

    public List<Intrebare> getIntrebari() {
        return intrebari;
    }

    public void setIntrebari(List<Intrebare> intrebari) {
        this.intrebari = intrebari;
    }

}
