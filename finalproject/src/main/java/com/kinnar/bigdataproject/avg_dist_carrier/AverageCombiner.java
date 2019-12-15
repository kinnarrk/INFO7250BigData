package com.kinnar.bigdataproject.avg_dist_carrier;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageCombiner extends Reducer<Text, AvgDisTimeTuple, Text, AvgDisTimeTuple> {

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

		avgDistTimeTuple.setAvgAirTime(totalAirTimes);
		avgDistTimeTuple.setTotalDistance(totalDistance);
		avgDistTimeTuple.setTotalFlights(totalFlights);

		context.write(key, avgDistTimeTuple);
	}
}
