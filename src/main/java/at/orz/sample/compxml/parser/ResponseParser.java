/**
 * 
 */
package at.orz.sample.compxml.parser;

import java.io.File;

import at.orz.sample.compxml.entity.ImageSearchResultSet;

/**
 * @author tamtam
 *
 */
public interface ResponseParser {
	
	public ImageSearchResultSet parse(File xmlFile) throws Exception;
	
}
