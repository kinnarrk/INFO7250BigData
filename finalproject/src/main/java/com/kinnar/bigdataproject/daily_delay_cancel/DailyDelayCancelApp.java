package com.kinnar.bigdataproject.daily_delay_cancel;

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class DailyDelayCancelApp {
	public static void main(String[] args) throws Exception {

		Configuration conf1 = new Configuration();

		FileSystem hdfs = FileSystem.get(URI.create("hdfs://localhost:9000"), conf1);

		Path output = new Path(args[1]);
		// delete existing directory
		if (hdfs.exists(output)) {
			hdfs.delete(output, true);
		}

		Job job1 = Job.getInstance(conf1);
		job1.setJarByClass(DailyDelayCancelApp.class);
		job1.setJobName("Daily delay ratio with delay > 15 minutes and cancelled flights ratio");

		FileInputFormat.setInputPaths(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));

		job1.setMapperClass(DailyDelayCancelMapper.class);
		job1.setCombinerClass(DailyDelayCancelReducer.class);
		job1.setReducerClass(DailyDelayCancelReducer.class);

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
