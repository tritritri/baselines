package ch.epfl.lsir.wattalyst.weather;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * 
 * @author vasirani
 * 
 */
public class YahooWeatherDataReader {

	private static final String APPID = "9FQR1RDV34EUEKDwKugXqdoZDP.pIQ8rVlec6G1WUAAze9pgeYX1ymFlyFCpI2_hQKI8JhR4";
	private static final double FAHRENHEIT_TO_CELSIUS = -17.22222222222222;

	/**
	 * Query Yahoo weather service and return tomorrow temperature forecast.
	 * 
	 * @param WOEID
	 * @return a TemperatureForecast object containing the target day, the min temperature
	 * and the max temperature, or null in case of error
	 */
	public TemperatureForecast get1DayForecast(String location) {
		
		String WOEID = getWOEIDForSpecificPlace(location);
		
		if(WOEID != null){
			
			RestTemplate restTemplate = new RestTemplate();
			String RSSresult = restTemplate.getForObject(
					"http://weather.yahooapis.com/forecastrss?w={WOEID}&u=c",
					String.class, WOEID);
	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				return null;
			}
			InputSource is = new InputSource(new StringReader(RSSresult));
			Document doc;
			try {
				doc = builder.parse(is);
			} catch (SAXException | IOException e) {
				e.printStackTrace();
				return null;
			}
	
			// Get the document's root XML node
			NodeList root = doc.getChildNodes();
	
			// Navigate down the hierarchy to get to the yweather:forecast nodes
			Node rss = getNode("rss", root);
			Node channel = getNode("channel", rss.getChildNodes());
			Node item = getNode("item", channel.getChildNodes());
			
			// TODO
			// Check unit of measurements of temp
			
			List<Node> yweatherForecastNodes = getNodes("yweather:forecast", item.getChildNodes()); 
			if(yweatherForecastNodes.size() == 2){
				Node yweatherForecast = yweatherForecastNodes.get(1);
			
				try {
					Date date = new SimpleDateFormat("dd MMM yyyy").parse(getNodeAttr("date", yweatherForecast));
					double minTempInF = Double.valueOf(getNodeAttr("low", yweatherForecast));
					double maxTempInF = Double.valueOf(getNodeAttr("high", yweatherForecast));
					return new TemperatureForecast(date, minTempInF, maxTempInF);
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	/*
	 * 
	 */
	private String getWOEIDForSpecificPlace(String location) {

		RestTemplate restTemplate = new RestTemplate();
		String XMLresult = restTemplate
				.getForObject(
						"http://where.yahooapis.com/v1/places.q({location})?appid={appid}",
						String.class, location, APPID);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		InputSource is = new InputSource(new StringReader(XMLresult));
		Document doc;
		try {
			doc = builder.parse(is);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}

		// Get the document's root XML node
		NodeList root = doc.getChildNodes();

		// Navigate down the hierarchy to get to the place node
		Node places = getNode("places", root);
		Node place = getNode("place", places.getChildNodes());

		if(place != null){
			// Load the woeid data from the XML
			NodeList placeChildNodes = place.getChildNodes();
			return getNodeValue("woeid", placeChildNodes);
		}
		return null;
	}

	/*
	 * 
	 */
	private String getNodeValue(String tagName, NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				NodeList childNodes = node.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node data = childNodes.item(j);
					if (data.getNodeType() == Node.TEXT_NODE)
						return data.getNodeValue();
				}
			}
		}
		return "";
	}

	/*
	 * 
	 */
	private Node getNode(String tagName, NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				return node;
			}
		}
		return null;
	}
	
	/*
	 * 
	 */
	private List<Node> getNodes(String tagName, NodeList nodes) {
		List<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				nodeList.add(node);
			}
		}
		return nodeList;
	}
	
	/*
	 * 
	 */
	private String getNodeAttr(String attrName, Node node) {
	    NamedNodeMap attrs = node.getAttributes();
	    for (int i = 0; i < attrs.getLength(); i++ ) {
	        Node attr = attrs.item(i);
	        if (attr.getNodeName().equalsIgnoreCase(attrName)) {
	            return attr.getNodeValue();
	        }
	    }
	    return "";
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		YahooWeatherDataReader reader = new YahooWeatherDataReader();
		System.out.println(reader.get1DayForecast("Lulea"));
	}
}