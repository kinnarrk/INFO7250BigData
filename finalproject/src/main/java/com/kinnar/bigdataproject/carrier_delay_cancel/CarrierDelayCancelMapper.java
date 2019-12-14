package com.kinnar.bigdataproject.carrier_delay_cancel;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CarrierDelayCancelMapper extends Mapper<Object, Text, Text, DelayRatioTuple> {
	private DelayRatioTuple tuple = new DelayRatioTuple();
	boolean flag = true;

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split(",");

		if (data[0].equals("Year"))
			return;

		String carrier = data[8];
		try {
			int delay = Integer.parseInt(data[14]);

			if (delay > 15) {
				tuple.setDelayedFlightsCount(1);
			} else {
				tuple.setDelayedFlightsCount(0);
			}
		} catch (Exception e) {
			tuple.setDelayedFlightsCount(0);
		}
		try {
			if (data[21].equals("1")) {
				tuple.setCanceledFlightsCount(1);
			} else {
				tuple.setCanceledFlightsCount(0);
			}
		} catch (Exception e) {
			tuple.setCanceledFlightsCount(0);
		}
		tuple.setFlightsCount(1);

		context.write(new Text(carrier), tuple);
	}

}
