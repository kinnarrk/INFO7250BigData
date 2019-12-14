package com.kinnar.bigdataproject.unique_carrier_names;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class CarriersApp {
	public static void main(String[] args) throws Exception {

		Configuration conf1 = new Configuration();

		Job job1 = Job.getInstance(conf1);
		job1.setJarByClass(CarriersApp.class);
		job1.setJobName("Unique carrier count");

		FileInputFormat.setInputPaths(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1] + "/temp/carriersintermediate"));

		job1.setMapperClass(UniqueCarrierMapper.class);
		job1.setReducerClass(UniqueCarrierReducer.class);

		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(IntWritable.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);

		if (!job1.waitForCompletion(true)) {
			System.exit(1);
		}
	}
}
