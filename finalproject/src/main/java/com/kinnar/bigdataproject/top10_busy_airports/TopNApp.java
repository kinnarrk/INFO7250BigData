package com.kinnar.bigdataproject.top10_busy_airports;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class TopNApp {
	public static void main(String[] args) throws Exception {

		Configuration conf1 = new Configuration();

		Job job1 = Job.getInstance(conf1);
		job1.setJarByClass(TopNApp.class);
		job1.setJobName("Get src-dest airport combination count");

		FileInputFormat.setInputPaths(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1] + "/temp/topnintermediate"));

		job1.setMapperClass(SrcDestMapper.class);
		job1.setReducerClass(SrcDestReducer.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);

		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(IntWritable.class);

		if (!job1.waitForCompletion(true)) {
			System.exit(1);
		}

		Configuration conf2 = new Configuration();

		Job job2 = Job.getInstance(conf2);
		job2.setJarByClass(TopNApp.class);
		job2.setJobName("Top 10 Source Destination airport combination from previous MR job");

		FileInputFormat.setInputPaths(job2, new Path(args[1] + "/temp/topnintermediate"));
		FileOutputFormat.setOutputPath(job2, new Path(args[1] + "/Top10SourceDestinations"));

		job2.setMapperClass(TopNAirportsMapper.class);
		job2.setReducerClass(TopNAirportsReducer.class);

		job2.setMapOutputKeyClass(NullWritable.class);
		job2.setMapOutputValueClass(Text.class);

		job2.setInputFormatClass(TextInputFormat.class);
		job2.setOutputFormatClass(TextOutputFormat.class);

		job2.setMapOutputKeyClass(NullWritable.class);
		job2.setMapOutputValueClass(Text.class);

		if (!job2.waitForCompletion(true)) {
			System.exit(1);
		}
	}
}
