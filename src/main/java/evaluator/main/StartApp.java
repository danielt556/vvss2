package evaluator.main;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Statistica;
import evaluator.model.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

    private static final String file = "intrebari.txt";

    public static void main(String[] args) throws IOException {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        AppController appController = new AppController();
        appController.loadIntrebariFromFile(file);

        boolean activ = true;
        String optiune = null;

        while(activ){

            System.out.println("");
            System.out.println("1.Adauga intrebare");
            System.out.println("2.Creeaza test");
            System.out.println("3.Statistica");
            System.out.println("4.Exit");
            System.out.println("");

            optiune = console.readLine();

            switch(optiune){
                case "1" :
                    System.out.print("[*] Introduceti enuntul intrebarii: ");
                    String enunt = console.readLine();

                    System.out.print("[*] Introduceti varianta 1: ");
                    String varianta1 = console.readLine();
                    System.out.print("[*] Introduceti varianta 2: ");
                    String varianta2 = console.readLine();
                    System.out.print("[*] Introduceti varianta 3: ");
                    String varianta3 = console.readLine();

                    System.out.print("[*] Introduceti varianta corecta: ");
                    String variantaCorecta = console.readLine();

                    System.out.print("[*] Introduceti domeniul: ");
                    String domeniu = console.readLine();

                    try {
                        appController.addNewIntrebare(enunt, varianta1, varianta2, varianta3, variantaCorecta, domeniu);
                        System.out.println("[#] Intrebare adaugata cu success!");
                    } catch (DuplicateIntrebareException | InputValidationFailedException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "2" :
                    try {
                        appController.createNewTest();
                    } catch (NotAbleToCreateTestException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "3" :
                    Statistica statistica;
                    try {
                        statistica = appController.getStatistica();
                        System.out.println(statistica);
                    } catch (NotAbleToCreateStatisticsException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "4" :
                    activ = false;
                    break;
                default:
                    break;
            }
        }

    }

}
