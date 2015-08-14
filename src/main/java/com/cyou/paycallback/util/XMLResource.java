package com.cyou.paycallback.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLResource {
	private DocumentBuilder builder = null;
	private DocumentBuilderFactory builderFactory = null;
	private static XMLResource resource;

	private XMLResource() {
	}

	public static XMLResource getInstance() {
		if (resource == null) {
			resource = new XMLResource();
			resource.init();
		}
		return resource;
	}

	private void init() {
		try {
			builderFactory = DocumentBuilderFactory.newInstance();
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	public Document getDocument(String url) {
		Document document = null;
		try {
			document = builder.parse(url);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	public Element getRoot(Document document) {
		if (document == null)
			return null;
		return document.getDocumentElement();
	}

	public NodeList getNodeList(Document document, String nodeName) {
		if (StringUtils.isEmpty(nodeName))
			return null;
		return document.getElementsByTagName(nodeName);
	}

	public NodeList getNodeList(Node node) {
		if (node == null)
			return null;
		return node.getChildNodes();
	}

	public String getAttributes(Node node, String attributeName) {
		if (node == null)
			return null;
		return node.getAttributes().getNamedItem(attributeName).getNodeValue();
	}

}
