package com.kinnar.bigdataproject.recommendation_sys;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class SecondarySortReducer extends Reducer<CompositeKeyMr, NullWritable, CompositeKeyMr, NullWritable> {

	@Override
	protected void reduce(CompositeKeyMr key, Iterable<NullWritable> values, Context context)
			throws IOException, InterruptedException {

		for (NullWritable val : values) {
			context.write(key, val);
		}
	}

}
