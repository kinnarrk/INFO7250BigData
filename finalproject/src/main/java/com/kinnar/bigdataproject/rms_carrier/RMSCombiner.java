package com.kinnar.bigdataproject.rms_carrier;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class RMSCombiner extends Reducer<Text, RMSTuple, Text, RMSTuple> {

	private RMSTuple rmsTupple = new RMSTuple();

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

		rmsTupple.setTotalFlights(totalFlights);
		rmsTupple.setArrivalDelay(arrivalDelay);
		rmsTupple.setDepartureDelay(departureDelay);

		context.write(key, rmsTupple);
	}
}
