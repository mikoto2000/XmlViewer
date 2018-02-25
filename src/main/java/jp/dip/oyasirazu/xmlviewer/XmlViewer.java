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

    private Document document;
    private XmlView xmlView;
    private ViewBuilder viewBuilder;

    /**
     * Constructor
     */
    public XmlViewer(String... xmlFiles) throws XmlViewerException {
        // とりあえず先頭だけ読み込んで表示する
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.parse(Paths.get(xmlFiles[0]).toFile());

            this.viewBuilder = new DefaultViewBuiler(this);
        } catch (IOException|ParserConfigurationException|SAXException e) {
            throw new XmlViewerException(e);
        }
    }

    public Document getDocument() {
        return this.document;
    }

    public XmlView buildView() throws XmlViewBuildException {
        return this.viewBuilder.build();
    }

    public interface ViewBuilder {
        public XmlView build() throws XmlViewBuildException;
    }

    public interface XmlView {
        public void show();
    }

    public class DefaultViewBuiler implements ViewBuilder {

        private XmlViewer xmlViewer;

        public DefaultViewBuiler(XmlViewer xmlViewer) {
            this.xmlViewer = xmlViewer;
        }

        public XmlView build() throws XmlViewBuildException {
            Document docment = xmlViewer.getDocument();
            try {

                StringWriter sw = new StringWriter();
                TransformerFactory tfactory = TransformerFactory.newInstance();
                Transformer transformer =
                    tfactory.newTransformer(new StreamSource(
                        XmlViewer.class.getResourceAsStream("/style.xsl")));

                transformer.transform(
                        new DOMSource(document), new StreamResult(sw));

                String xmlString = sw.toString().replaceAll("\r\n", "\n");

                return new DefaultXmlView(xmlString);
            } catch (TransformerException e) {
                throw new XmlViewBuildException(e);
            }
        }
    }

    public class DefaultXmlView implements XmlView {

        private String xmlString;

        public DefaultXmlView(String xmlString) {
            this.xmlString = xmlString;
        }

        public void show() {
            System.out.println(this.xmlString);
        }
    }
}

