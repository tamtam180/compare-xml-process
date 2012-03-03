/**
 * 
 */
package at.orz.sample.compxml.parser.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import at.orz.sample.compxml.entity.ImageSearchResultEntity;
import at.orz.sample.compxml.entity.ImageSearchResultSet;
import at.orz.sample.compxml.entity.ImageSearchThumbnailEntity;
import at.orz.sample.compxml.parser.ResponseParser;

/**
 * @author tamtam
 *
 */
public class SaxResponseParser implements ResponseParser {
	
	private static class SaxStackElement {
		
		protected String qName;
		protected Attributes attributes;
		protected StringBuilder textContent;
		
		public SaxStackElement(String qName, Attributes attributes) {
			this.qName = qName;
			this.attributes = attributes;
			this.textContent = new StringBuilder();
		}
		
	}
	
	@Override
	public ImageSearchResultSet parse(File xmlFile) throws Exception {
		
		final ArrayList<ImageSearchResultEntity> list = new ArrayList<>();
		final ImageSearchResultSet resultSet = new ImageSearchResultSet(list);
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFile, new DefaultHandler() {
			private Stack<SaxStackElement> stack = new Stack<>();
			private ImageSearchResultEntity curResult = null;
			private ImageSearchThumbnailEntity curThumbnail = null;
			//private boolean inThumbnail = false;
			@Override
			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				
				stack.push(new SaxStackElement(qName, attributes));
				
				if ("ResultSet".equals(qName)) {
					resultSet.setTotalResultsAvailable(Long.parseLong(attributes.getValue("totalResultsAvailable")));
					resultSet.setTotalResultsReturned(Integer.parseInt(attributes.getValue("totalResultsReturned")));
					resultSet.setFirstResultPosition(Integer.parseInt(attributes.getValue("firstResultPosition")));
				} else if ("Result".equals(qName)) {
					curResult = new ImageSearchResultEntity();
				} else if ("Thumbnail".equals(qName)) {
					curThumbnail = new ImageSearchThumbnailEntity();
				}
				
			}
			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {
				if (stack.isEmpty()) {
					throw new RuntimeException("XML EndElement Invalid.");
				}
				SaxStackElement elem = stack.pop();
				// FIXME qName and elem.qName confirm whether to be the same value. 
				
				if (curThumbnail == null) { // in Result Node
					switch (qName) {
					case "RefererUrl":
						curResult.setRefererUrl(elem.textContent.toString());
						break;
					case "ClickUrl":
						curResult.setClickUrl(elem.textContent.toString());
						break;
					case "Url":
						curResult.setUrl(elem.textContent.toString());
						break;
					case "Title":
						curResult.setTitle(elem.textContent.toString());
						break;
					case "Summary":
						curResult.setSummary(elem.textContent.toString());
						break;
					case "Width":
						curResult.setWidth(Integer.parseInt(elem.textContent.toString()));
						break;
					case "Height":
						curResult.setHeight(Integer.parseInt(elem.textContent.toString()));
						break;
					case "FileSize":
						curResult.setFileSize(elem.textContent.toString());
						break;
					case "FileFormat":
						curResult.setFileFormat(elem.textContent.toString());
						break;
					case "Result":
						list.add(curResult);
						break;
					} 
				} else { // in Thumbanil Node
					switch (qName) {
					case "Url":
						curThumbnail.setUrl(elem.textContent.toString());
						break;
					case "Width":
						curThumbnail.setWidth(Integer.parseInt(elem.textContent.toString()));
						break;
					case "Height":
						curThumbnail.setHeight(Integer.parseInt(elem.textContent.toString()));
						break;
					case "Thumbnail":
						curResult.setThumbnail(curThumbnail);
						curThumbnail = null;
						break;
					}
				}
				
			}
			@Override
			public void characters(char[] ch, int start, int length)
					throws SAXException {
				if (!stack.isEmpty()) {
					stack.peek().textContent.append(ch, start, length);
				}
			}

		});
		
		return resultSet;
	}
	
	
	
}
