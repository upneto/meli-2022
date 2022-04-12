package com.meli.challenge.morse.usecases.morsecode.kmeans;

import java.util.*;

public class KMeans {

    private static final int NUM_CLUSTERS = 3;
    private static final int[] UNIT_TIMES = { 1, 3, 7 };

    private boolean converged = false;
    private KMeansCluster[] clusters;

    private float[] timeUnits = {0, 0, 0};
    private HashMap<Integer, Integer> dist = new HashMap<>();

    /**
     * Construtor
     * @param zeros
     * @param ones
     */
    public KMeans(String[] zeros, String[] ones) {
        this.setNumClusters();
        String[] bitCollection = this.buildBitCollection(zeros, ones);
        this.buildGroupForDistanceCalc(bitCollection);
    }

    /**
     *  Atribui o cluster mais próximo a cada ponto e calcula o centroide
     *  para os Clusters baseados nesses pontos.
     */
    public void makeGrouping() {
        if (!converged) {
            this.assignToClosestCluster();
            while (!converged) {
                this.update();
                this.assignToClosestCluster();
                if (!didChange()) {
                    converged = true;
                }
            }
            this.calculateTimeUnits();
        }
    }

    /**
     * obtem unidade de tempo
     */
    public float getTimeUnit(int index) {
        return this.timeUnits[index];
    }

    /**
     * Cria clusters
     */
    private void setNumClusters() {
        this.clusters = new KMeansCluster[NUM_CLUSTERS];
    }

    /**
     * Cria lista cos valores intercalando os tipos '0' e '1' para futuro processamento
     * @param zeros
     * @param ones
     */
    private String[] buildBitCollection(String[] zeros, String[] ones) {
        String[] bitCollection = new String[ones.length + zeros.length - 1];
        for (int i = 0; i < ones.length - 1; i++) {
            bitCollection[2 * i] = ones[i];
            bitCollection[2 * i + 1] = zeros[i + 1];
        }
        bitCollection[bitCollection.length - 1] = ones[ones.length - 1];
        return bitCollection;
    }

    /**
     * Agrupa os valores de acordo com o tamanho para calculo de distancia
     */
    private void buildGroupForDistanceCalc(String[] bitCollection) {
        Arrays.stream(bitCollection).forEach(element -> {
            int length = element.length();
            if (!dist.containsKey(length)) {
                dist.put(length, 1);
            } else {
                dist.put(length, dist.get(length) + 1);
            }
        });

        this.initializeClustersAndTimeUnits();
    }

    /**
     * Inicializa 3 clusters com localização no Inicio, meio e final da sequencia.
     */
    private void initializeClustersAndTimeUnits() {
        List<Integer> keys = new ArrayList<>(dist.keySet());

        if (keys.size() == 1 || keys.size() == 2) {
            timeUnits[0] = keys.get(0) * UNIT_TIMES[0];
            timeUnits[1] = keys.get(0) * UNIT_TIMES[1];
            timeUnits[2] = keys.get(0) * UNIT_TIMES[2];
            converged = true;
        } else {
            Collections.sort(keys);
            clusters[0] = new KMeansCluster(keys.get(0));
            clusters[1] = new KMeansCluster((keys.get(keys.size() - 1) + keys.get(0)) / 2 + 1);
            clusters[2] = new KMeansCluster(keys.get(keys.size() - 1));
        }
    }

    /**
     * Agrupa valores no centroid
     */
    public void assignToClosestCluster() {
        this.clear();
        dist.keySet().forEach(key -> {
            KMeansCluster bestCluster = new KMeansCluster();
            float closest = Float.MAX_VALUE;
            for (KMeansCluster cluster : clusters) {
                float distance = cluster.getDistance(key);
                if (distance < closest) {
                    closest = distance;
                    bestCluster = cluster;
                }
            }
            for (int j = 0; j < dist.get(key); j++) {
                bestCluster.addPoint(key);
            }
        });
    }

    /**
     * Preenche as unidades de tempo
     * significa, representando a duração média de:
     *      1 unidade de tempo,
     *      3 unidades de tempo e
     *      7 unidades
     */
    private void calculateTimeUnits() {
        KMeansCluster[] sortedClusters = clusters.clone();
        Arrays.sort(sortedClusters);
        timeUnits[0] = sortedClusters[0].getLocation();
        timeUnits[1] = sortedClusters[1].getLocation();
        timeUnits[2] = sortedClusters[2].getLocation();
    }

    /**
     * Limpa os clusters
     */
    private void clear() {
        Arrays.stream(clusters).forEach(element -> element.clearPoints());
    }

    /**
     * Verifica se há mudança no cluster
     * @return
     */
    private boolean didChange() {
        boolean isChanged = false;
        for (KMeansCluster c : clusters) {
            if (c.isChanged()) {
                isChanged = true;
                break;
            }
        }
        return isChanged;
    }

    /**
     * Atualiza cluster
     */
    private void update() {
        Arrays.stream(clusters).forEach(element -> element.update());
    }
}
