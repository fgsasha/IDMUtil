/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlutil;

import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author o.nekriach
 */
public class XmlUtil {
    final static String OID="oid";
    final static String OBJECTNODENAME="apti:object";

    public static void getFieldValue(String xml, String keyName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));

        Document doc = builder.parse(src);
//        NodeList nodes = doc.getChildNodes();
//        
//        for (int i = 0; i < nodes.getLength(); i++) {
//        System.out.println(i+"="+nodes.item(i).getAttributes().getNamedItem("oid"));
//            //System.out.println("" + nodes.item(i).getTextContent());
//         }
        //String age = doc.getElementsByTagName("age").item(0).getTextContent();
        String value = doc.getElementsByTagName(keyName).item(0).getTextContent();
        System.out.println(keyName + " : " + value);

    }
        public static String getOIDValue(String xml,String nodeName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));

        Document doc = builder.parse(src);
        if(nodeName==null || nodeName.isEmpty()){
        nodeName=OBJECTNODENAME;
        }
//        NodeList rootNode =  doc.getElementsByTagName("t:object");
//        Element rootElement=(Element) rootNode.item(0);
//        NodeList node2 = rootElement.getElementsByTagName("apti:object");
//        Element element2=(Element) node2.item(0);
//        String oid=element2.getAttribute(OID);
//        System.out.println("oid:"+oid);
        NodeList node = doc.getElementsByTagName(nodeName);
        Element element=(Element) node.item(0);
        String oid=element.getAttribute(OID);
        System.out.println("oid: "+oid);
        //System.out.print("name: "+element.getElementsByTagName("name").item(0).getTextContent());
        return oid;
        }
        
        static public HashMap<String,String> getNamesOIDMap(String xml,String nodeName, Boolean nameToLowcase) throws ParserConfigurationException, IOException, SAXException {
        HashMap<String,String> output = new HashMap<String,String>();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(xml));

        Document doc = builder.parse(src);
        if(nodeName==null || nodeName.isEmpty()){
        nodeName=OBJECTNODENAME;
        }
//        NodeList rootNode =  doc.getElementsByTagName("t:object");
//        Element rootElement=(Element) rootNode.item(0);
//        NodeList node2 = rootElement.getElementsByTagName("apti:object");
//        Element element2=(Element) node2.item(0);
//        String oid=element2.getAttribute(OID);
//        System.out.println("oid:"+oid);
        NodeList node = doc.getElementsByTagName(nodeName);
        for(int i=0; i < node.getLength(); i++){
        Element element=(Element) node.item(i);
        String oid=element.getAttribute(OID);
        String name=element.getElementsByTagName("name").item(0).getTextContent();
        if(nameToLowcase){
        name=name.toLowerCase();
        }
        output.put(name, oid);
        System.out.println(i+"- name: "+name+"; oid: "+oid);        
        }
        return output;
        }

    public static void main(String[] args) {

    }
}
