package ch.heigvd.sitr.map.input;

import ch.heigvd.sitr.autogen.opendrive.OpenDRIVE;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

/**
 * This class is used to unmarshall an XML Schema using JAXB. Unmarshall is the process of
 * binding the XML document using JAXB compiler and generating mapping java classes then
 * constructing the instances with values available in XML document.
 * More informations : https://javapapers.com/jee/jaxb-tutorial/
 */
public class OpenDriveUnmarshaller {
    private static final Class OD_CLASS = ch.heigvd.sitr.autogen.opendrive.OpenDRIVE.class;
    private static final String BASE_PATH = "/map/";
    private static final String XSDFILE = "OpenDRIVE_1.3.xsd";
    private static final String W3C_XML_SCHEMA_NS_URI = "http://www.w3.org/2001/XMLSchema";

    /**
     * This method unmarshalls the xmlFile and generates an instance with values available in
     * XML document. Before that, we have bind the schema and generate the classes
     *
     * @param xmlFile The XML file source to unmarshall with JAXB
     * @return Instance with values available in XML document.
     */
    public OpenDRIVE load(StreamSource xmlFile) {
        OpenDRIVE data = null;

        try {
            InputStream in = getClass().getResourceAsStream(BASE_PATH + XSDFILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            // Build XML Schema based on openDRIVE xsd file
            Schema schema = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI).newSchema(new StreamSource(br));

            // Create the unmarshaller
            JAXBContext context = JAXBContext.newInstance(OD_CLASS);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);

            StreamSource source = xmlFile;
            data = (OpenDRIVE) unmarshaller.unmarshal(source, OD_CLASS).getValue();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return data;
    }
}
