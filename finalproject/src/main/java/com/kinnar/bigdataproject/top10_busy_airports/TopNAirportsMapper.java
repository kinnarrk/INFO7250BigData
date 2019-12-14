package com.kinnar.bigdataproject.top10_busy_airports;

import java.io.IOException;
import java.util.TreeMap;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TopNAirportsMapper extends Mapper<Object, Text, NullWritable, Text> {
	private TreeMap<Integer, Text> counter = new TreeMap<>();

	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] val = value.toString().split("\t");

		if (val.length == 2) {
			int count = Integer.parseInt(val[1]);
			counter.put(count, new Text(value));
		}

		if (counter.size() > 10)
			counter.remove(counter.firstKey());
	}

	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		for (Text t : counter.values())
			context.write(NullWritable.get(), t);
	}
}
