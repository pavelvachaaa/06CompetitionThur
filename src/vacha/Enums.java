/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vacha;


enum RacerState {
    PREPARED, RUNNING, FINISHED, DISQUALIFIED;
}

enum Gender {
    MALE("M"), FEMALE("F"), OTHER("O"), HELICOPTER("O");
    private String value;

    private Gender(String value) {
        this.value = value;
    }

    public static Gender of(String value) {
        for (Gender g : Gender.values()) {
            if (g.value.equals(value)) {
                return g;
            }
        }
        return Gender.OTHER;
    }
}

enum Categories {
    MUZIDO20(20),
    MUZIDO40(40),
    MUZIDO60(60),
    ZENYDO20(20),
    ZENYDO40(40),
    ZENYDO60(60),
    ZBYTEK(0);

    private final int maxAge;

    private Categories(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

}
