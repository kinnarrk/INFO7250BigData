package com.kinnar.bigdataproject.top10_busy_airports;

import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class SrcDestMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	Text word = new Text();
	IntWritable one = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String line = value.toString();
		String[] data = line.split(",");
		if (data[0].equals("Year"))
			return;
		String orig_dest_pair = data[16] + "-" + data[17];
		word.set(orig_dest_pair);
		context.write(word, one);
	}
}
