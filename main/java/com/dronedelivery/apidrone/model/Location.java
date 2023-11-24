package com.dronedelivery.apidrone.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Location {

    public static final double flat = 6731000;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name="height")
    private double height;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double[] getVector(Location coordinates, double radius){

        return new double[] {
                radius*Math.cos(Math.toRadians(coordinates.getLongitude())),
                radius*Math.sin(Math.toRadians(coordinates.getLongitude())),
                radius*Math.sin(Math.toRadians(coordinates.getLatitude()))
        };
    }

    public double[] subtractVectors(double[] origin, double[] target){

        return new double[] {target[0] - origin[0], target[1] - origin[1], target[2] - origin[2]};
    }

    public double[] resizeVector(double[] vector, double actualLenght, double desiredLenght){

        double cof = actualLenght/desiredLenght;
        return new double[]{vector[0]*cof,vector[1]*cof,vector[2]*cof};
    }
    public double[] getDirection(Location coordinates){

        double[] origin = getVector(this, this.getHeight());
        double[] target = getVector(coordinates, coordinates.getHeight());
        return subtractVectors(target, origin);
    }

    public double getDistance(double[] vector){
        double distance = 0;

        for(int i = 0; i < 3; i++) {distance += Math.pow(vector[i] , 2);}

        distance = Math.sqrt(distance);

        return distance;
    }

    public double calcDistance(Location comp){

        double[] direction = getDirection(comp);
        return getDistance(direction);
    }

}
