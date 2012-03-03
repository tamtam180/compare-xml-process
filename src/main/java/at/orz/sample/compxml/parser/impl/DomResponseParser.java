/**
 * 
 */
package at.orz.sample.compxml.parser.impl;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import at.orz.sample.compxml.entity.ImageSearchResultEntity;
import at.orz.sample.compxml.entity.ImageSearchResultSet;
import at.orz.sample.compxml.entity.ImageSearchThumbnailEntity;
import at.orz.sample.compxml.parser.ResponseParser;

/**
 * @author tamtam
 *
 */
public class DomResponseParser implements ResponseParser {

	@Override
	public ImageSearchResultSet parse(File xmlFile) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(xmlFile);
		Element root = doc.getDocumentElement(); // <ResultSet>
		
		if (!"ResultSet".equals(root.getNodeName())) {
			return null;
		}
		
		ArrayList<ImageSearchResultEntity> list = new ArrayList<>();
		ImageSearchResultSet resultSet = new ImageSearchResultSet(list);

		resultSet.setTotalResultsAvailable(Long.parseLong(root.getAttribute("totalResultsAvailable")));
		resultSet.setTotalResultsReturned(Integer.parseInt(root.getAttribute("totalResultsReturned")));
		resultSet.setFirstResultPosition(Integer.parseInt(root.getAttribute("firstResultPosition")));
		
		NodeList resultNodeList = root.getChildNodes(); // <Result>
		for (int i = 0, imax = resultNodeList.getLength(); i < imax; i++) {
			Node resultNode = resultNodeList.item(i);
			if ("Result".equals(resultNode.getNodeName())) {
				ImageSearchResultEntity result = toResultEntity(resultNode);
				list.add(result);
			}
		}
		
		return resultSet;
		
	}
	
	private ImageSearchResultEntity toResultEntity(Node node) {

		ImageSearchResultEntity entity = new ImageSearchResultEntity();

		NodeList resultChildNodes = node.getChildNodes();
		for (int j = 0, jmax = resultChildNodes.getLength(); j < jmax; j++) {
			Node resultChildNode = resultChildNodes.item(j);
			if (resultChildNode.getNodeName() == null) {
				continue;
			}
			switch (resultChildNode.getNodeName()) {
			case "RefererUrl":
				entity.setRefererUrl(resultChildNode.getTextContent());
				break;
			case "ClickUrl":
				entity.setClickUrl(resultChildNode.getTextContent());
				break;
			case "Url":
				entity.setUrl(resultChildNode.getTextContent());
				break;
			case "Title":
				entity.setTitle(resultChildNode.getTextContent());
				break;
			case "Summary":
				entity.setSummary(resultChildNode.getTextContent());
				break;
			case "Width":
				entity.setWidth(Integer.parseInt(resultChildNode.getTextContent()));
				break;
			case "Height":
				entity.setHeight(Integer.parseInt(resultChildNode.getTextContent()));
				break;
			case "FileSize":
				entity.setFileSize(resultChildNode.getTextContent());
				break;
			case "FileFormat":
				entity.setFileFormat(resultChildNode.getTextContent());
				break;
			case "Thumbnail":
				entity.setThumbnail(toThumbnailEntitty(resultChildNode));
				break;
			} 
		}

		return entity;
		
	}
	
	private ImageSearchThumbnailEntity toThumbnailEntitty(Node node) {
		
		ImageSearchThumbnailEntity entity = new ImageSearchThumbnailEntity();
		
		NodeList childNodes = node.getChildNodes();
		for (int i = 0, imax = childNodes.getLength(); i < imax; i++) {
			Node childNode = childNodes.item(i);
			if (childNode.getNodeName() == null) {
				continue;
			}
			switch (childNode.getNodeName()) {
			case "Url":
				entity.setUrl(childNode.getTextContent());
				break;
			case "Width":
				entity.setWidth(Integer.parseInt(childNode.getTextContent()));
				break;
			case "Height":
				entity.setHeight(Integer.parseInt(childNode.getTextContent()));
				break;
			}
		}
		
		return entity;
	}
	
	
}
