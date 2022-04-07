/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vacha;

import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author pavel.vacha
 */
/**
 *
 * @author pavel.vacha
 */
public class Competitor {

    private String name;
    private String surname;
    private Gender gender;
    private int year;
    private String team;
    private static Calendar calendar = Calendar.getInstance();

    private Competitor(String name, String surname, int year, String gender) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.gender = Gender.of(gender);

    }

    /**
     * Kopie závodníka
     *
     * @param z
     */
    public Competitor(Competitor z) {
        this.name = z.name;
        this.surname = z.surname;
        this.year = z.year;
        this.gender = Gender.of(z.gender.name());
    }

    public static Competitor getInstance(String name, String surname, int year, String gender) {
        return new Competitor(name, surname, year, gender);
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return this.year;
    }

    public Gender getGender() {
        return this.gender;
    }

    public int getAge() {
        return calendar.get(Calendar.YEAR) - this.year;
    }

    public String getFullname() {
        return String.format("%s %s", this.name, this.surname);
    }

}
