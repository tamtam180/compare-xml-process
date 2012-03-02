package at.orz.sample.compxml;

import static at.orz.sample.compxml.parser.ParserFactory.TYPE.DOM;
import static at.orz.sample.compxml.parser.ParserFactory.TYPE.SAX;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;

import org.junit.Test;

import at.orz.sample.compxml.parser.ParserFactory;
import at.orz.sample.compxml.parser.ParserFactory.TYPE;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 
 * @author tamtam
 *
 */
public class MainTest {
	
	/**
	 * すべてのパーサーが同じ結果を返すか？
	 */
	@Test
	public void sameReturnsEarchParser() throws Exception {
		
		File[] xmlFiles = new File[] { 
				new File("file1.xml"),
				new File("file2.xml")
				};

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// check a same result each parser.
		for (File xmlFile : xmlFiles) {
			
			// Sax
			String saxResult = gson.toJson(ParserFactory.newImageSearchParser(SAX).parse(xmlFile));
			// Dom
			String domResult = gson.toJson(ParserFactory.newImageSearchParser(DOM).parse(xmlFile));
			// Stax
			String staxCResult = gson.toJson(ParserFactory.newImageSearchParser(TYPE.STAX_C).parse(xmlFile));

			assertThat(domResult, is(notNullValue()));
			assertThat(domResult, is(saxResult));
			assertThat(domResult, is(staxCResult));
			
		}
		
	}
	
}
