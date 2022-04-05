package com.meli.challenge.morse.usecases.morsecode.kmeans;

import lombok.Data;

import java.util.ArrayList;

@Data
public class KMeansCluster implements Comparable<KMeansCluster> {

    private float location;
    private float centroid;

    private ArrayList<Integer> currentPoints = new ArrayList<>();
    private ArrayList<Integer> previousPoints = new ArrayList<>();

    public KMeansCluster(float loc) {
        location = loc;
    }

    public KMeansCluster() {
        location = -1;
    }

    public void addPoint(int i) {
        currentPoints.add(i);
    }

    public boolean isChanged() {
        if (previousPoints.size() != currentPoints.size()) {
            return true;
        }
        else {
            return !currentPoints.equals(previousPoints);
        }
    }

    public void clearPoints() {
        previousPoints = (ArrayList<Integer>) currentPoints.clone();
        currentPoints.clear();
    }

    public void update() {
        float sum = 0;
        for (Integer p : currentPoints) {
            sum += p;
        }
        centroid = (sum / currentPoints.size());
        this.setLocation(centroid);
    }

    public float getDistance(int point) {
        return Math.abs(location - point);
    }

    @Override
    public int compareTo(KMeansCluster t) {
        int result = 0;
        if (this.getLocation() > t.getLocation()) {
            result = 1;
        }
        else if (this.getLocation() < t.getLocation()) {
            result = -1;
        }
        return result;
    }
}
