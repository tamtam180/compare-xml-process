/**
 * 
 */
package at.orz.sample.compxml.entity;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author tamtam
 *
 */
public class ImageSearchResultSet implements Iterable<ImageSearchResultEntity> {
	
	protected List<ImageSearchResultEntity> results;
	protected long totalResultsAvailable;
	protected int totalResultsReturned;
	protected int firstResultPosition;
	
	public ImageSearchResultSet() {
	}
	
	public ImageSearchResultSet(List<ImageSearchResultEntity> results) {
		this.results = results;
	}
	
	@Override
	public Iterator<ImageSearchResultEntity> iterator() {
		if (results == null) {
			return Collections.emptyIterator();
		}
		return results.iterator();
	}

	public List<ImageSearchResultEntity> getResults() {
		return results;
	}

	public void setResults(List<ImageSearchResultEntity> results) {
		this.results = results;
	}

	public long getTotalResultsAvailable() {
		return totalResultsAvailable;
	}

	public void setTotalResultsAvailable(long totalResultsAvailable) {
		this.totalResultsAvailable = totalResultsAvailable;
	}

	public int getTotalResultsReturned() {
		return totalResultsReturned;
	}

	public void setTotalResultsReturned(int totalResultsReturned) {
		this.totalResultsReturned = totalResultsReturned;
	}

	public int getFirstResultPosition() {
		return firstResultPosition;
	}

	public void setFirstResultPosition(int firstResultPosition) {
		this.firstResultPosition = firstResultPosition;
	}
}
