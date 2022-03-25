package dataFile;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlReader {

    String filePath;


    public XmlReader(String filePath){
        this.filePath = filePath;
    }

    public NodeList getDataList(){
        NodeList userList = null;
        try{
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbf.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            userList = doc.getElementsByTagName("user");

        }catch (Exception e){
            e.printStackTrace();
        }

        return userList;
    }
}
