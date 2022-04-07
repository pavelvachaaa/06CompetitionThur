/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vacha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 *
 * @author pavel.vacha
 */
public class Competition {

    private String name;
    private ArrayList<Registration> registrations = new ArrayList<>();

    public Competition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Registration> getRegistrations() {
        ArrayList<Registration> copy = new ArrayList<>();
        for (Registration reg : this.registrations) {
            copy.add(new Registration(reg));
        }
        return copy;

    }

    /**
     * Asociace s tímto závodem a tímto závodníkem
     *
     */
    public void addRegistration(Registration reg) {
        this.registrations.add(reg);
    }

    public Registration getRegistration(int id) {
        return new Registration(this.registrations.stream().filter(c -> c.getRegistrationNumber() == id).findAny().orElseThrow(() -> {
            throw new NoSuchElementException("Žádny takový uživatel neexistuje");
        }));
    }

    /**
     * Nejrychlejší závodník z CELÉHO závodu
     *
     * @return
     */
    public Registration findFastestCompetitor() {
        long fastestTime = Integer.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < this.registrations.size(); i++) {
            if (this.registrations.get(i).getRunningTime() < fastestTime) {
                fastestTime = this.registrations.get(i).getRunningTime();
                index = i;
            }
        }

        return new Registration(this.registrations.get(index));
    }

    public void startRaceAll(int hours, int minutes, int seconds, int offsetInMinutes) {
        for (int i = 0; i < this.registrations.size(); i++) {
            this.registrations.get(i).setStartTime(hours, minutes + i * offsetInMinutes, seconds);
        }
    }

    public void startRaceAll(int hours, int minutes, int seconds) {
        for (Registration reg : registrations) {
            reg.setStartTime(hours, minutes, seconds);
        }
    }

    /**
     * Diskvalifikace závodníka
     *
     * @param registrationNumber
     */
    public void removeCompetititor(int registrationNumber) {
        this.registrations.removeIf(c -> c.getRegistrationNumber() == registrationNumber);
    }

    public void sortByRunningTime() {
        Collections.sort(this.registrations);
    }

    public void sortBySurname() {
        Collections.sort(registrations, new ComparatorCompetitorBySurname());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %20s %20s %20s %20s %20s", "ID", "JMÉNO", "KATEGORIE", "START", "KONEC", "CELKOVÝ ČAS"));
        sb.append(System.getProperty("line.separator"));
        sb.append("=".repeat(120));
        sb.append(System.getProperty("line.separator"));

        this.registrations.forEach(r -> {
            sb.append(String.format("%s %20s %20s %20s %20s %20s",
                    r.getRegistrationNumber(),
                    r.getCompetitor().getFullname(),
                    r.getCategory().name(),
                    TimeTools.longToString(r.getStartTime()),
                    TimeTools.longToString(r.getFinishTime()),
                    TimeTools.longToString(r.getRunningTime())
            ));

            sb.append(System.getProperty("line.separator"));
        }
        );

        return sb.toString();
    }

}
