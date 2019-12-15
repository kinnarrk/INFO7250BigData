package com.kinnar.bigdataproject.rms_carrier;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class RMSMapper extends Mapper<Object, Text, Text, RMSTuple> {

	private RMSTuple rmsTuple = new RMSTuple();

	@Override
	protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] tokens = value.toString().split(",");

		if (tokens[0].equals("Year"))
			return;

		String source = tokens[16];
		String destination = tokens[17];
		String carrierCode = tokens[8];

		int arrivalDelay = 0;
		int departureDelay = 0;

		try {
			arrivalDelay = Integer.parseInt(tokens[14]);
			departureDelay = Integer.parseInt(tokens[15]);
		} catch (Exception e) {

		}

		String nKey = source + "-" + destination + "\t" + carrierCode;

		rmsTuple.setArrivalDelay(arrivalDelay);
		rmsTuple.setDepartureDelay(departureDelay);
		rmsTuple.setTotalFlights(1);

		context.write(new Text(nKey), rmsTuple);
	}
}
