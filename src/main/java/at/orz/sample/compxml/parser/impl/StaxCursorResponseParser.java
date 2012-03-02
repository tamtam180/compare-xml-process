/**
 * 
 */
package at.orz.sample.compxml.parser.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import at.orz.sample.compxml.entity.ImageSearchResultEntitty;
import at.orz.sample.compxml.entity.ImageSearchResultSet;
import at.orz.sample.compxml.entity.ImageSearchThumbnailEntity;
import at.orz.sample.compxml.parser.ResponseParser;

/**
 * @author tamtam
 *
 */
public class StaxCursorResponseParser implements ResponseParser {

	private Map<String, String> toAttributeMap(XMLStreamReader reader) {
		TreeMap<String, String> attrMap = new TreeMap<>();
		for (int i = 0, imax = reader.getAttributeCount(); i < imax; i++) {
			String key = reader.getAttributeName(i).getLocalPart();
			String value = reader.getAttributeValue(i);
			attrMap.put(key, value);
		}
		return attrMap;
	}
	
	private boolean seek(XMLStreamReader reader, String nodeName) throws XMLStreamException {
		
		if (reader.isStartElement() && nodeName.equals(reader.getName().getLocalPart())) {
			return true;
		}
		
		while (reader.hasNext()) {
			if (reader.next() == XMLStreamConstants.START_ELEMENT && 
					nodeName.equals(reader.getName().getLocalPart())) {
				return true;
			}
		}
		return false;
	}
	private String getValue(XMLStreamReader reader) throws XMLStreamException {
		if (!reader.isStartElement()) {
			throw new IllegalStateException();
		}
		StringBuilder buffer = new StringBuilder();
		while (reader.hasNext()) {
			reader.next();
			if (reader.isCharacters()) {
				buffer.append(reader.getText());
			} else if (reader.isEndElement()) {
				return buffer.toString();
			}
		}
		throw new IllegalStateException();
	}

	private ImageSearchResultEntitty processResult(XMLStreamReader reader) throws XMLStreamException {
		
		ImageSearchResultEntitty result = new ImageSearchResultEntitty();
		
		while (reader.hasNext()) {
			reader.next();
			if (reader.isStartElement()) {
				String tagName = reader.getName().getLocalPart();
				if (tagName != null) {
					switch (tagName) {
					case "RefererUrl":
						result.setRefererUrl(getValue(reader));
						break;
					case "ClickUrl":
						result.setClickUrl(getValue(reader));
						break;
					case "Url":
						result.setUrl(getValue(reader));
						break;
					case "Title":
						result.setTitle(getValue(reader));
						break;
					case "Summary":
						result.setSummary(getValue(reader));
						break;
					case "Width":
						result.setWidth(Integer.parseInt(getValue(reader)));
						break;
					case "Height":
						result.setHeight(Integer.parseInt(getValue(reader)));
						break;
					case "FileSize":
						result.setFileSize(getValue(reader));
						break;
					case "FileFormat":
						result.setFileFormat(getValue(reader));
						break;
					case "Thumbnail":
						result.setThumbnail(processThumbnail(reader));
						break;
					}
				} 
			} else if (reader.isEndElement()) {
				if ("Result".equals(reader.getName().getLocalPart())) {
					break;
				}
			}
		}
		
		return result;
		
	}
	
	private ImageSearchThumbnailEntity processThumbnail(XMLStreamReader reader) throws XMLStreamException {
		
		ImageSearchThumbnailEntity thumbnail = new ImageSearchThumbnailEntity();
		
		while (reader.hasNext()) {
			reader.next();
			if (reader.isStartElement()) {
				String tagName = reader.getName().getLocalPart();
				if (tagName != null) {
					switch (tagName) {
					case "Url":
						thumbnail.setUrl(getValue(reader));
						break;
					case "Width":
						thumbnail.setWidth(Integer.parseInt(getValue(reader)));
						break;
					case "Height":
						thumbnail.setHeight(Integer.parseInt(getValue(reader)));
						break;
					}
				}
			} else if (reader.isEndElement()) {
				if ("Thumbnail".equals(reader.getName().getLocalPart())) {
					break;
				}
			}
		}
		
		return thumbnail;
		
	}
	
	@Override
	public ImageSearchResultSet parse(File xmlFile) throws Exception {
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new BufferedInputStream(new FileInputStream(xmlFile)));
		
		if (!seek(reader, "ResultSet")) {
			return null;
		}
		Map<String, String> resultSetAttrMap = toAttributeMap(reader);
		
		ArrayList<ImageSearchResultEntitty> list = new ArrayList<>();
		
		while (seek(reader, "Result")) {
			ImageSearchResultEntitty result = processResult(reader);
			list.add(result);
		}
		reader.close();
		
		ImageSearchResultSet resultSet = new ImageSearchResultSet(list);
		resultSet.setTotalResultsAvailable(Long.parseLong(resultSetAttrMap.get("totalResultsAvailable")));
		resultSet.setTotalResultsReturned(Integer.parseInt(resultSetAttrMap.get("totalResultsReturned")));
		resultSet.setFirstResultPosition(Integer.parseInt(resultSetAttrMap.get("firstResultPosition")));
		
		return resultSet;
	}

}
