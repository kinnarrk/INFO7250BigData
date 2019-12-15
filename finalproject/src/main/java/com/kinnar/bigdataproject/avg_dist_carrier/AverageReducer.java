package com.kinnar.bigdataproject.avg_dist_carrier;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageReducer extends Reducer<Text, AvgDisTimeTuple, Text, AvgDisTimeTuple> {

	private AvgDisTimeTuple avgDistTimeTuple = new AvgDisTimeTuple();

	@Override
	protected void reduce(Text key, Iterable<AvgDisTimeTuple> values, Context context)
			throws IOException, InterruptedException {

		int totalFlights = 0;
		int totalDistance = 0;
		int totalAirTimes = 0;

		for (AvgDisTimeTuple dt : values) {
			totalFlights += dt.getTotalFlights();
			totalDistance += dt.getTotalDistance();
			totalAirTimes += dt.getTotalAirtime();
		}

		double avgDist = (double) totalDistance / totalFlights;
		double avgAirTime = (double) totalAirTimes / totalFlights;

		avgDistTimeTuple.setAvgAirTime(totalAirTimes);
		avgDistTimeTuple.setTotalDistance(totalDistance);
		avgDistTimeTuple.setTotalFlights(totalFlights);
		avgDistTimeTuple.setAvgDistance(avgDist);
		avgDistTimeTuple.setAvgAirTime(avgAirTime);

		context.write(key, avgDistTimeTuple);
	}
}
