/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 * markerclusterer is an api from google-map
 * @author AdamGu0
 */
public class SplitSmartSwapClusterer {

    private final MapKit mapKit;
    private Point[] data;
    private ArrayList<Cluster> clusters;
    public Cluster[] clustersArray;
    public long duration;
    public boolean startCluster = false;

    public SplitSmartSwapClusterer(MapKit mapKit) {
        this.mapKit = mapKit;
    }

    public int startSplitSmartSwap(Point[] data) {
        if (data == null) {
            return 1;
        } else if (data.length == 0) {
            return 2;
        }
        this.startCluster = true;
        this.data = data.clone();
        doSplitSmartSwap();
        return 0;
    }

    public boolean doSplitSmartSwap() {
        if (!startCluster) return startCluster;
        
        long start = System.currentTimeMillis();
        this.clusters = new ArrayList<Cluster>();
        ClusterData cd = new ClusterData(data, true);
        Cluster[] clusters = cd.smartSwap(10);
        
        duration = System.currentTimeMillis() - start;

        mapKit.setWaypoints(clusters);
        mapKit.repaint();
        this.clustersArray = clusters;
        return startCluster;
    }

}
