package com.kinnar.bigdataproject.daily_delay_cancel;

import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import org.apache.hadoop.io.Text;

public class DailyDelayCancelReducer extends Reducer<Text, DelayRatioTuple, Text, DelayRatioTuple> {

	private DelayRatioTuple tuple = new DelayRatioTuple();

	@Override
	protected void reduce(Text key, Iterable<DelayRatioTuple> values, Context context)
			throws IOException, InterruptedException {
		int total = 0;
		int delayedTotal = 0;
		int cancelledTotal = 0;

		for (DelayRatioTuple dt : values) {
			total += dt.getFlightsCount();
			delayedTotal += dt.getDelayedFlightsCount();
			cancelledTotal += dt.getCanceledFlightsCount();
		}

		double delayPercentage = ((double) delayedTotal / total) * 100;
		double cancelledPercentage = ((double) cancelledTotal / total) * 100;

		tuple.setFlightsCount(total);
		tuple.setDelayedFlightsCount(delayedTotal);
		tuple.setDelayPercentage(delayPercentage);
		tuple.setCanceledFlightsCount(cancelledTotal);
		tuple.setCanceledPercentage(cancelledPercentage);

		context.write(key, tuple);
	}
}
