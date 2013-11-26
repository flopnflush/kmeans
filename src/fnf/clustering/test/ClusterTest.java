package fnf.clustering.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fnf.clustering.*;
import fnf.clustering.distance.*;

public class ClusterTest {
	static final int N = 10000;
	static final int K = 100;
	static final int ITERATIONS = 100;
	static final int TRIALS = 5;
	
	public static void main(String[] args) {
		Random rnd = new Random();
		
		List<DoublePoint> data = new ArrayList<DoublePoint>();
		
		for (int i=0; i<N; i++) {
			data.add(new DoublePoint(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble()));
		}
		
		KMeansPlusPlusClusterer<DoublePoint> clusterer = new KMeansPlusPlusClusterer<DoublePoint>(K, ITERATIONS, new EarthMoversDistance());
		
		MultiKMeansPlusPlusClusterer<DoublePoint> multiClusterer = new MultiKMeansPlusPlusClusterer<DoublePoint>(clusterer, TRIALS);
		long start = System.currentTimeMillis();
		
		List<CentroidCluster<DoublePoint>> clusterResults = multiClusterer.cluster(data);
		
		long stop = System.currentTimeMillis();
		
		System.out.println(stop - start + " ms");
		
		/*
		for (int i=0; i<clusterResults.size(); i++) {
		    System.out.println("Cluster " + i);
		    for (DoublePoint locationWrapper : clusterResults.get(i).getPoints()) {
		        for (int j=0; j<locationWrapper.getPoint().length; j++)
		        	System.out.print(locationWrapper.getPoint()[j] + " ");
		    	System.out.println();
		    }
		        	
		    System.out.println();
		}
		*/

	}
}
