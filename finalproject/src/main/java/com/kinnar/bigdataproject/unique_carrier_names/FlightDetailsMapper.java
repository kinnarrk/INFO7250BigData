package com.kinnar.bigdataproject.unique_carrier_names;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlightDetailsMapper extends Mapper<LongWritable, Text, Text, Text> {
	Text word = new Text();
	IntWritable one = new IntWritable(1);

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split("\t");

		String newKey = data[0];
		word.set(newKey);
		System.out.println("Bkey:" + newKey + ":");
		String outValue = "B" + data[1]; // right table
		context.write(word, new Text(outValue));
	}
}
