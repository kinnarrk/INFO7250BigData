package com.kinnar.bigdataproject.yearly_delay;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class YearlyDelayApp {
	public static void main(String[] args) throws Exception {

		Configuration conf1 = new Configuration();

		Job job1 = Job.getInstance(conf1);
		job1.setJarByClass(YearlyDelayApp.class);
		job1.setJobName("Yearly delay ratio with delay > 15 minutes and cancelled flights ratio");

		FileInputFormat.setInputPaths(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));

		job1.setMapperClass(YearlyDelayMapper.class);
		job1.setCombinerClass(YearlyDelayReducer.class);
		job1.setReducerClass(YearlyDelayReducer.class);

		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(DelayRatioTuple.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(DelayRatioTuple.class);

		if (!job1.waitForCompletion(true)) {
			System.exit(1);
		}
	}
}
