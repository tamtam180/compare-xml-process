/**
 * 
 */
package at.orz.sample.compxml;

import static at.orz.sample.compxml.parser.ParserFactory.TYPE.DOM;
import static at.orz.sample.compxml.parser.ParserFactory.TYPE.SAX;

import java.io.File;

import at.orz.sample.compxml.parser.ParserFactory;
import at.orz.sample.compxml.parser.ParserFactory.TYPE;

/**
 * @author tamtam
 * 
 */
public class Main {
	
	private static class BenchmarkResult {
		private String desc;
		private long min1; 		// 1回あたりの最速
		private long max1; 		// 1回あたりの最遅
		//private long avg1;	// 1回の平均
		private long rmin; 		// 1ラウンドあたりの最速
		private long rmax; 		// 1ラウンドあたりの最遅
		private long ravg;		// 1ラウンドの平均
		private long total;		// 合計時間
		@Override
		public String toString() {
			return String.format(
				"=====%s=====%n" +
				"min1  %10d%n" +
				"max1  %10d%n" +
				"rmin  %10d%n" +
				"rmax  %10d%n" +
				"ravg  %10d%n" +
				"total %10d%n" +
				"==========%n"
				, desc, min1, max1, rmin, rmax, ravg, total);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		File[] xmlFiles = new File[]{
			new File("file1.xml"),
			new File("file2.xml")
		};
		
		// 計測
		final int times = 10;
		final int maxLoop = 10000;
				
		for (File xmlFile : xmlFiles) {
			System.out.println(benchmark(xmlFile, times, maxLoop, SAX));
			System.out.println(benchmark(xmlFile, times, maxLoop, DOM));
			System.out.println(benchmark(xmlFile, times, maxLoop, TYPE.STAX_C));
		}
		
	}
	
	private static BenchmarkResult benchmark(File xmlFile, int times, int maxLoop, TYPE type) throws Exception {
		
		if (times < 0 || maxLoop < 0) {
			throw new IllegalArgumentException("ぷんすか!!");
		}
		
		BenchmarkResult benchResult = new BenchmarkResult();
		benchResult.desc = type.name();
		benchResult.min1 = Long.MAX_VALUE;
		benchResult.max1 = Long.MIN_VALUE;
		benchResult.rmin = Long.MAX_VALUE;
		benchResult.rmax = Long.MIN_VALUE;
		
		System.out.println("TYPE=" + type + ", File:" + xmlFile.getName());
		
		for (int k = 0; k < times; k++) {
			long time = System.currentTimeMillis();
			for (int i = 0; i < maxLoop; i++) {
				long ptime = System.currentTimeMillis();
				ParserFactory.newImageSearchParser(type).parse(xmlFile);
				ptime = System.currentTimeMillis() - ptime; // 1回あたりの処理時間
				benchResult.min1 = Math.min(benchResult.min1, ptime);
				benchResult.max1 = Math.max(benchResult.max1, ptime);
			}
			time = System.currentTimeMillis() - time; // 1ラウンドあたりの処理時間
			System.out.println("  " + time + "[ms] / avg=" + (time * 1000L / maxLoop) + "[us]");
			
			benchResult.rmin = Math.min(benchResult.rmin, time);
			benchResult.rmax = Math.max(benchResult.rmax, time);
			benchResult.total += time;
		}
		benchResult.ravg = benchResult.total / times;
		
		return benchResult;
	}

}
