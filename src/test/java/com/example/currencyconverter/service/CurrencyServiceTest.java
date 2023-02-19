package com.example.currencyconverter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTest {
	@Autowired
	CurrencyService currencyService;

	@Test
	void parseCurrencyForXMLTest() throws Exception {
		//currencyService.parseCurrencyForXML();
//		// Arrange
//		URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		Document doc = dBuilder.parse(url.openStream());
//		doc.getDocumentElement().normalize();
//
//		NodeList nodeList = doc.getElementsByTagName("Valute");
//
//		// Act and Assert
//		for (int i = 0; i < nodeList.getLength(); i++) {
//			Element element = (Element) nodeList.item(i);
//			String charCode = element.getElementsByTagName("CharCode").item(0).getTextContent();
//			switch (charCode) {
//				case "USD":
//					assertEquals("$", charCode);
//					break;
//				case "EUR":
//					assertEquals("€", charCode);
//					break;
//				case "JPY":
//					assertEquals("¥", charCode);
//					break;
//				default:
//					// Проверяем, что у всех элементов Valute charCode имеет ожидаемое значение
//					assertEquals(3, charCode.length());
//					break;
//			}
//		}
//	}
	}
}

