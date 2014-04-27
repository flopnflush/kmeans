package fnf.clustering.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fnf.clustering.*;
import fnf.clustering.KMeansPlusPlusClusterer.EmptyClusterStrategy;
import fnf.clustering.distance.*;

public class ClusterTest {
	static final int N = 100000;
	static final int K = 1000;
	static final int ITERATIONS = 1000;
	static final int TRIALS = 8;
	static final int PARALLEL_TRIALS = 4;

	public static void main(String[] args) {
		Random rnd = new Random();

		List<DoublePoint> data = new ArrayList<DoublePoint>();

		for (int i = 0; i < N; i++) {
			double[] point = new double[100];
			for (int j = 0; j < point.length; j++)
				point[j] = rnd.nextDouble();
			data.add(new DoublePoint(point));
		}

		MultiKMeansPlusPlusClusterer<DoublePoint> multiClusterer = new MultiKMeansPlusPlusClusterer<DoublePoint>(
				K, ITERATIONS, new EarthMoversDistance(),
				EmptyClusterStrategy.LARGEST_VARIANCE, TRIALS, PARALLEL_TRIALS);
		long start = System.currentTimeMillis();

		List<CentroidCluster<DoublePoint>> clusterResults = multiClusterer.cluster(data);

		long stop = System.currentTimeMillis();

		System.out.println(stop - start + " ms");

		/*
		 * for (int i=0; i<clusterResults.size(); i++) {
		 * System.out.println("Cluster " + i); for (DoublePoint locationWrapper
		 * : clusterResults.get(i).getPoints()) { for (int j=0;
		 * j<locationWrapper.getPoint().length; j++)
		 * System.out.print(locationWrapper.getPoint()[j] + " ");
		 * System.out.println(); }
		 * 
		 * System.out.println(); }
		 */

	}
}
