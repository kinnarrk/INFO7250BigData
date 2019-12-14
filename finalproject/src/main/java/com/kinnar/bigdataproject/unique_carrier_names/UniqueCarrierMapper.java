package com.kinnar.bigdataproject.unique_carrier_names;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UniqueCarrierMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	Text word = new Text();
	IntWritable one = new IntWritable(1);

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split(",");

		if (data[0].equals("Year"))
			return;

		String carrier = data[8];
		word.set(carrier);
		context.write(word, one);
	}
}
