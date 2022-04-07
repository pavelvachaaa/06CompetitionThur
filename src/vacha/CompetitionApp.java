/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vacha;

import java.util.Scanner;
import static vacha.Competitor.getInstance;

/**
 *
 * @author pavel.vacha
 */
public class CompetitionApp {

    private static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Competition competition = new Competition("Jizerská 50");

        Registration r1 = Registration.getInstance(getInstance("Pavel ", "Vácha", 2001, "M"));
        Registration r2 = Registration.getInstance(getInstance("Jakub ", "Štěpánek", 2002, "M"));
        Registration r3 = Registration.getInstance(getInstance("Matěj ", "Štěpán", 2002, "Z"));
        Registration r4 = Registration.getInstance(getInstance("Adam ", "Sucharda", 2001, "M"));
        Registration r5 = Registration.getInstance(getInstance("Jakub ", "Qtěpánek", 2002, "M"));
        Registration r6 = Registration.getInstance(getInstance("Jakub ", "Adam", 2002, "M"));

        competition.addRegistration(r1);
        competition.addRegistration(r2);
        competition.addRegistration(r3);
        competition.addRegistration(r4);
        competition.addRegistration(r5);
        competition.addRegistration(r6);
        
        competition.startRaceAll(9, 0, 0, 1);

        r1.setFinishTime("09:05:06");
        r2.setFinishTime("09:10:06");
        r3.setFinishTime("09:06:06");
        r4.setFinishTime("09:06:06");

        System.out.println("Vítej na závodě " + competition.getName());
        competition.sortBySurname();
        System.out.println(competition);

        System.out.println(competition.findFastestCompetitor().getCompetitor().getFullname());

        System.out.println(competition.getRegistration(2).getCompetitor().getFullname() + " " + competition.getRegistration(2).getCompetitor().getGender());
        competition.sortByRunningTime();
        System.out.println(competition);

    }

    public static Registration registration() {

        System.out.println("Vítejte v registraci");
        System.out.println("====================");
        System.out.print("Zadejte vaše jméno: ");
        String name = sc.nextLine();
        System.out.print("Zadejte vaše příjmení: ");
        String surname = sc.nextLine();
        System.out.println("Vaše pohlaví (M,F,O): ");
        String gender = sc.nextLine();
        System.out.println("Váš ročník: ");
        int year = sc.nextInt();
        sc.nextLine();
        return Registration.getInstance(getInstance(name, surname, year, gender));
    }

}
