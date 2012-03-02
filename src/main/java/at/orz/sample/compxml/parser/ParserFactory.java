/**
 * 
 */
package at.orz.sample.compxml.parser;

import at.orz.sample.compxml.parser.impl.DomResponseParser;
import at.orz.sample.compxml.parser.impl.SaxResponseParser;
import at.orz.sample.compxml.parser.impl.StaxCursorResponseParser;

/**
 * @author tamtam
 *
 */
public class ParserFactory {
	
	public static enum TYPE {
		DOM, SAX, STAX_E, STAX_C
	}
	
	public static ResponseParser newImageSearchParser(TYPE type) {
		switch (type) {
		case DOM:
			return new DomResponseParser();
		case SAX:
			return new SaxResponseParser();
		case STAX_E:
			return null;
		case STAX_C:
			return new StaxCursorResponseParser();
		}
		throw new IllegalArgumentException("type argument is invalid.");
	}
	
}
