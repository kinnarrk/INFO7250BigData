package com.kinnar.bigdataproject.rms_carrier;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class RMSReducer extends Reducer<Text, RMSTuple, Text, RMSTuple> {

	private RMSTuple rmsTuple = new RMSTuple();

	@Override
	protected void reduce(Text key, Iterable<RMSTuple> values, Context context)
			throws IOException, InterruptedException {

		int totalFlights = 0;
		int arrivalDelay = 0;
		int departureDelay = 0;

		for (RMSTuple tupple : values) {
			totalFlights += tupple.getTotalFlights();
			arrivalDelay += tupple.getArrivalDelay();
			departureDelay += tupple.getDepartureDelay();
		}

		double avgArrivalDelay = (double) arrivalDelay / totalFlights;
		double avgDepartureDelay = (double) departureDelay / totalFlights;

		double rmsValue = Math.sqrt((avgArrivalDelay * avgArrivalDelay) + (avgDepartureDelay * avgDepartureDelay));

		rmsTuple.setTotalFlights(totalFlights);
		rmsTuple.setArrivalDelay(arrivalDelay);
		rmsTuple.setDepartureDelay(departureDelay);
		rmsTuple.setRmsVal(rmsValue);

		context.write(key, rmsTuple);
	}
}
