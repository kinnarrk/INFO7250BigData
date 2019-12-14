package com.kinnar.bigdataproject.yearly_delay;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class YearlyDelayMapper extends Mapper<Object, Text, Text, DelayRatioTuple> {
	private DelayRatioTuple tuple = new DelayRatioTuple();
	boolean flag = true;

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split(",");

		if (data[0].equals("Year"))
			return;

		String year = data[0];
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

		context.write(new Text(year), tuple);
	}
}
