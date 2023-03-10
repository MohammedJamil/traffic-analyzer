package org.traffic.normalizer;

import java.util.Objects;

public class TagMasterNormalizer implements Normalizer{
    /**
     * Class Attributes
     */
    private final String station;

    /**
     * Class Constructor.
     */
    public TagMasterNormalizer(String station) {
        this.station = station;
    }

    /**
     * Returns category.
     */
    private String vehicleCategory(String c) {
        if (Objects.equals(c, "Bus")) {
            return "PL";
        } else if (Objects.equals(c, "Deux roues")) {
            return "2R";
        } else {
            return c;
        }
    }

    /**
     * Checks if record is valid.
     */
    public Boolean isValid(String record){
        String[] tokens = record.split(",");

        if (tokens.length != 8){
            return false;
        }

        return true;
    }

    /**
     * Normalizes records captured by a TagMaster radar.
     */
    public String normalize(String record) {
        String[] tokens = record.split(",");
        String category = vehicleCategory(tokens[7]);
        String date = tokens[0];
        String direction;
        String io;
        if (Objects.equals(tokens[2], "Sortie Fac")) {
            direction = "sortie";
            io = "out";
        } else {
            direction = "entree";
            io = "in";
        }
        String speed = tokens[3] + "." + tokens[4];
        return station + "," + date + "," + category + "," + direction + "," + speed + "," + io;
    }
}