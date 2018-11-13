/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmlutil;

import static org.toilelibre.libe.curl.Curl.curl;
import static org.toilelibre.libe.curl.Curl.$;
import hrdata.util.FileUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author o.nekriach
 */
public class RESTAPI {

   static String OPTIONS = "Content-Type: application/xml";
   static String cred1 = "";
   static String cred2 = "";

    public static void setOPTIONS(String OPTIONS) {
        RESTAPI.OPTIONS = OPTIONS;
    }

    public static void setCred1(String cred1) {
        RESTAPI.cred1 = cred1;
    }

    public static void setCred2(String cred2) {
        RESTAPI.cred2 = cred2;
    }

    String httpClientGetObject(String url) {
        String result = $("curl -u " + cred1 + ":" + cred2 + " -X GET " + url);
        //System.out.println("result " + result);
        return result;
    }

    String httpClientSendDataFile(String url, String method, String searchFilter) throws IOException {

        String result = $("curl -X " + method + " -H \"" + OPTIONS + "\" -u " + cred1 + ":" + cred2 + " " + url + " -d '" + searchFilter + "'");
        //System.out.println(result);
        return result;
    }

    public static void test() throws IOException, ParserConfigurationException, SAXException {
        RESTAPI rest = new RESTAPI();
        setCred1("administrator");
        setCred2("<SECRET>");
        String file = "/home/o.nekriach/Documents/IAM/MidPoint/git_clone/midpoint/samples/rest/query-filtered-roles3.xml";
        String searchFilter = FileUtil.readFileToString(file, Charset.defaultCharset());
       String resource = rest.httpClientGetObject("https://idm-test.dyninno.net/midpoint/ws/rest/resources/b13cf619-3920-4c81-b3b6-aedbb540de88");
        
        String role=rest.httpClientSendDataFile("http://idm-test.dyninno.net:8888/midpoint/ws/rest/roles/search", "POST", searchFilter);
        System.out.println("role:\r\n"+role);
        
        xmlutil.XmlUtil.getFieldValue(resource, "name");
        xmlutil.XmlUtil.getOIDValue(role, "");
        xmlutil.XmlUtil.getNamesOIDMap(role,"",false);
        
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        test();

    }
}
