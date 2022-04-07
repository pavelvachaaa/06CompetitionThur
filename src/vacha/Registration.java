/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vacha;

import java.util.Comparator;

/**
 *
 * @author pavel.vacha
 */
public class Registration implements Comparable<Registration> {

    private static int registrationNumberCounter = 1;

    private int registrationNumber;
    private Competitor competitor;
    private Categories category;
    private boolean isFeePaid;

    private RacerState currentState;
    private long startTime;
    private long finishTime;

    private Registration(Competitor user) {
        this.competitor = user;
        this.isFeePaid = false;
        resolveCategory();
        this.registrationNumber = registrationNumberCounter;
        this.currentState = RacerState.PREPARED;
        registrationNumberCounter++;
    }

    public Registration(Registration r) {
        this.competitor = new Competitor(r.competitor);
        this.isFeePaid = r.isFeePaid;
        this.category = r.category;
        this.registrationNumber = r.registrationNumberCounter;
    }

    public static Registration getInstance(Competitor user) {
        if (user == null) {
            throw new IllegalArgumentException("Nevalidní argumenty k registraci kamaráde :)");
        }

        return new Registration(user);
    }

    @Override
    public int compareTo(Registration o) {
        return this.getRunningTime() < o.getRunningTime() ? -1
                : this.getRunningTime() > o.getRunningTime() ? 1 : 0;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public Competitor getCompetitor() {
        return this.competitor;
    }

    public Categories getCategory() {
        return this.category;
    }

    public void payFee() {
        this.isFeePaid = true;
    }

    public long getStartTime() {

        return startTime;
    }

    public long getFinishTime() {

        return finishTime;
    }

    /**
     * Rozřazení uživatelů do kategorií
     */
    private void resolveCategory() {
        int age = this.competitor.getAge();
        Gender gender = this.competitor.getGender();

        if (null == gender) {
            this.category = Categories.ZBYTEK;
        } else {
            switch (gender) {
                case MALE -> {
                    if (age < Categories.MUZIDO20.getMaxAge()) {
                        this.category = Categories.MUZIDO20;

                    } else if (age < Categories.MUZIDO40.getMaxAge()) {
                        this.category = Categories.MUZIDO40;

                    } else {
                        this.category = Categories.MUZIDO60;
                    }
                }
                case FEMALE -> {
                    if (age < Categories.ZENYDO20.getMaxAge()) {
                        this.category = Categories.ZENYDO20;

                    } else if (age < Categories.ZENYDO40.getMaxAge()) {
                        this.category = Categories.ZENYDO40;

                    } else {
                        this.category = Categories.ZENYDO60;
                    }
                }
                default ->
                    this.category = Categories.ZBYTEK;
            }
        }
    }

    public void setStartTime(int hour, int minutes, int seconds) {
        setStartTime(TimeTools.intsToLong(hour, minutes, seconds));
    }

    public void setStartTime(String time) {
        setStartTime(TimeTools.stringToLong(time));
    }

    public void setStartTime(long seconds) {
        this.currentState = RacerState.RUNNING;
        this.startTime = seconds;
    }

    public void setFinishTime(int hour, int minutes, int seconds) {
        setFinishTime(TimeTools.intsToLong(hour, minutes, seconds));
    }

    public void setFinishTime(String time) {
        setFinishTime(TimeTools.stringToLong(time));
    }

    public void setFinishTime(long seconds) {
        this.currentState = RacerState.FINISHED;
        this.finishTime = seconds;
    }

    public long getRunningTime() {
        if (this.currentState == RacerState.FINISHED) {
            return this.finishTime - this.startTime;
        }
        return 0;
    }

   

}
