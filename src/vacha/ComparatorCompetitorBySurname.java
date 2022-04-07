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
public class ComparatorCompetitorBySurname implements Comparator<Registration> {

    @Override
    public int compare(Registration o1, Registration o2) {
        return o1.getCompetitor().getSurname().compareTo(o2.getCompetitor().getSurname());
    }

}
