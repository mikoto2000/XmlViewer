package jp.dip.oyasirazu.xmlviewer;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


/**
 * XmlViewer
 */
public class XmlViewer {
    /**
     * Constructor
     */
    public XmlViewer(String... xmlFiles) throws XmlViewerException {
        // とりあえず先頭だけ読み込んで表示する
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Paths.get(xmlFiles[0]).toFile());

            StringWriter sw = new StringWriter();
            TransformerFactory tfactory = TransformerFactory.newInstance();
            Transformer transformer =
                tfactory.newTransformer(new StreamSource(
                    XmlViewer.class.getResourceAsStream("/style.xsl")));

            transformer.transform(
                    new DOMSource(document), new StreamResult(sw));

            String xmlString = sw.toString().replaceAll("\r\n", "\n");
            System.out.println(xmlString);
        } catch (IOException|ParserConfigurationException|SAXException|TransformerException e) {
            throw new XmlViewerException(e);
        }
    }
}
