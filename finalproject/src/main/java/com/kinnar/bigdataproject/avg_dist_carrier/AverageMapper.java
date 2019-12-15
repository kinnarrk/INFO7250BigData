package com.kinnar.bigdataproject.avg_dist_carrier;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AverageMapper extends Mapper<Object, Text, Text, AvgDisTimeTuple> {

	private AvgDisTimeTuple avgDistTimeTuple = new AvgDisTimeTuple();

	@Override
	protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] data = value.toString().split(",");

		if (data[0].equals("Year"))
			return;

		String carrierCode = data[8];
		int distance = 0;
		int airTime = 0;

		try {
			distance = Integer.parseInt(data[18]);

			airTime = Integer.parseInt(data[6]);
		} catch (Exception e) {
			return;
		}
		avgDistTimeTuple.setTotalFlights(1);
		avgDistTimeTuple.setTotalDistance(distance);
		avgDistTimeTuple.setTotalAirtime(airTime);

		context.write(new Text(carrierCode), avgDistTimeTuple);
	}
}
