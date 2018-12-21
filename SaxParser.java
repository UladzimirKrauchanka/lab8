package by.gsu.mslab8;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser {
	public static void main(String[] args) {

	      try {
	    	 String url="https://naviny.by/rss/alls.xml";
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         UserHandler userhandler = new UserHandler();
	         saxParser.parse(url, userhandler);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   
	}

	class UserHandler extends DefaultHandler {

		boolean title = false;
		boolean link = false;
		boolean description = false;
		boolean pubDate = false;
		boolean guid = false;
	   

	   @Override
	   public void startElement(
	      String uri, String localName, String qName, Attributes attributes)
	      throws SAXException {

	      if (qName.equalsIgnoreCase("item")) {
	      } else
		  if (qName.equalsIgnoreCase("title")) {
			  title = true;
		  } else
		  if (qName.equalsIgnoreCase("link")) {
			  link = true;
		  } else
		  if (qName.equalsIgnoreCase("description")) {
			  description = true;
		  } else if (qName.equalsIgnoreCase("pubDate")) {
			  pubDate = true;
		  } else if (qName.equalsIgnoreCase("guid")) {
			  guid = true;
		  }
	   }
	   
	   @Override
	   public void endElement(String uri, 
			   String localName, String qName) throws SAXException {
			      if (qName.equalsIgnoreCase("item")) {
			    	  System.out.println();
			      }
			   }

	   @Override
	   public void characters(char ch[], int start, int length) throws SAXException {

	      if (title) {
	         System.out.println("Title: " + new String(ch, start, length));
	         title = false;
	      } else
	      	if (description) {
	         System.out.println("Description: " + new String(ch, start, length));
	         description = false;
	      } else
	      	if (link) {
	         System.out.println("Link: " + new String(ch, start, length));
	         link = false;
	      } else
	      	if (pubDate) {
	         System.out.println("PubDate: " + new String(ch, start, length));
	         pubDate = false;
	      } else
	      	if (guid) {
		     System.out.println("Guid: " + new String(ch, start, length));
		     guid = false;
		  }
	   }
}
