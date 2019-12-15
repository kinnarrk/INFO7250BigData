package com.kinnar.bigdataproject.recommendation_sys;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class SecondarySortMapper extends Mapper<LongWritable, Text, CompositeKeyMr, NullWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String[] tokens = value.toString().split("\t", 2);

		try {
			String sourceDestination = tokens[0];
			String carrierInformation = tokens[1];

			CompositeKeyMr compositeKeyMr = new CompositeKeyMr(sourceDestination, carrierInformation);

			context.write(compositeKeyMr, NullWritable.get());
		} catch (Exception e) {			
		}

	}

}
