-- Top 10 airports by traffic - only year 2008 data
-- First, load the data
RAW_DATA = LOAD '/home/kinnar/Desktop/proj_dataset/2008.csv' USING PigStorage(',') AS 
	(year: int, month: int, day: int, dow: int, 
	dtime: int, sdtime: int, arrtime: int, satime: int, 
	carrier: chararray, fn: int, tn: chararray, 
	etime: int, setime: int, airtime: int, 
	adelay: int, ddelay: int, 
	scode: chararray, dcode: chararray, dist: int, 
	tintime: int, touttime: int, 
	cancel: chararray, cancelcode: chararray, diverted: int, 
	cdelay: int, wdelay: int, ndelay: int, sdelay: int, latedelay: int);


-- INBOUND TRAFFIC, PER MONTH, TOP 10
-- projection for only getting useful fields: only month and destination ID
INBOUND = FOREACH RAW_DATA GENERATE month AS m, dcode AS d;
-- group by month, then sort ID
GROUP_INBOUND = GROUP INBOUND BY (m,d);
-- aggregate and flatten group so that output relation has 3 fields
COUNT_INBOUND = FOREACH GROUP_INBOUND GENERATE FLATTEN(group), COUNT(INBOUND) AS count;
-- aggregate over months
GROUP_COUNT_INBOUND = GROUP COUNT_INBOUND BY m;
-- apply UDF to compute top k (k=10)
topMonthlyInbound = FOREACH GROUP_COUNT_INBOUND {
    result = TOP(10, 2, COUNT_INBOUND); 
    GENERATE FLATTEN(result);
}

-- dump topMonthlyInbound
STORE topMonthlyInbound INTO '/home/kinnar/Documents/project/INFO7200BigData/pig_output/1top_inbound' USING PigStorage(',');


-- OUTBOUND TRAFFIC, PER MONTH, TOP 10 - same as above
OUTBOUND = FOREACH RAW_DATA GENERATE month AS m, scode AS s;
GROUP_OUTBOUND = GROUP OUTBOUND BY (m,s);
COUNT_OUTBOUND = FOREACH GROUP_OUTBOUND GENERATE FLATTEN(group), COUNT(OUTBOUND) AS count;
GROUP_COUNT_OUTBOUND = GROUP COUNT_OUTBOUND BY m;
topMonthlyOutbound = FOREACH GROUP_COUNT_OUTBOUND {
    result = TOP(10, 2, COUNT_OUTBOUND); 
    GENERATE FLATTEN(result);
}

-- dump topMonthlyOutbound
STORE topMonthlyOutbound INTO '/home/kinnar/Documents/project/INFO7200BigData/pig_output/1top_outbound' USING PigStorage(',');


-- TOTAL TRAFFIC, PER MONTH, TOP 10
UNION_TRAFFIC = UNION COUNT_INBOUND, COUNT_OUTBOUND;
GROUP_UNION_TRAFFIC = GROUP UNION_TRAFFIC BY (m,d);
TOTAL_TRAFFIC = FOREACH GROUP_UNION_TRAFFIC GENERATE FLATTEN(group) AS (m,code), SUM(UNION_TRAFFIC.count) AS total; 
TOTAL_MONTHLY = GROUP TOTAL_TRAFFIC BY m;

topMonthlyTraffic = FOREACH TOTAL_MONTHLY {
    result = TOP(10, 2, TOTAL_TRAFFIC); 
    GENERATE FLATTEN(result) AS (month, iata, traffic);
}


STORE topMonthlyTraffic INTO '/home/kinnar/Documents/project/INFO7200BigData/pig_output/1monthly-top-traffic/' USING PigStorage(',');

explain -brief -dot -out ./ topMonthlyTraffic
