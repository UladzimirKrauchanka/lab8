package by.gsu.mslab8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxParser {
	public static void main(String[] args) {
		   boolean title = false;
		   boolean link = false;
		   boolean description = false;
		   boolean pubDate = false;
		   boolean guid = false;
	      
	      try {
	    	 String url="https://naviny.by/rss/alls.xml";
	         XMLInputFactory factory = XMLInputFactory.newInstance();
	         XMLEventReader eventReader = factory.createXMLEventReader(new URL(url).openStream());

	         while(eventReader.hasNext()) {
	            XMLEvent event = eventReader.nextEvent();
	               
	            switch(event.getEventType()) {
	               
	               case XMLStreamConstants.START_ELEMENT:
	                  StartElement startElement = event.asStartElement();
	                  String qName = startElement.getName().getLocalPart();

	               if (qName.equalsIgnoreCase("item")) {
	                  Iterator<Attribute> attributes = startElement.getAttributes();
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
	               break;

	               case XMLStreamConstants.CHARACTERS:
	                  Characters characters = event.asCharacters();
	               if(title) {
	                  System.out.println("Title: " + characters.getData());
	                  title = false;
	               }
	               if(link) {
	                  System.out.println("Link: " + characters.getData());
	                  link = false;
	               }
	               if(description) {
	                  System.out.println("Description: " + characters.getData());
	                  description = false;
	               }
	               if(pubDate) {
	                  System.out.println("PubDate: " + characters.getData());
					   pubDate = false;
	               }
	               if(guid) {
		              System.out.println("Guid: " + characters.getData());
		              guid = false;
		               }
	               break;

	               case XMLStreamConstants.END_ELEMENT:
	                  EndElement endElement = event.asEndElement();
	                  
	               if(endElement.getName().getLocalPart().equalsIgnoreCase("Item")) {
	            	   System.out.println();
	               }
	               break;
	            } 
	         }
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (XMLStreamException e) {
	         e.printStackTrace();
	      } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }
}
