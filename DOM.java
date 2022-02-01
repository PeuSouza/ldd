
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.processing.SupportedSourceVersion;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.*;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import static java.lang.System.out;


public class DOM {


    public static void main(String[] args) {


            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new File("products.xml"));
                Document out = db.newDocument();

                NodeList ListadeProdutos = doc.getElementsByTagName("product");


                int tamanhodaLista = ListadeProdutos.getLength();

                System.out.println("<table>");
                System.out.println("  <thread>");
                System.out.println("  <thread>");
                System.out.println("     <tr>");
                System.out.println("       <th>vendor</th>");
                System.out.println("       <th>name</th>");

                System.out.println("     </tr>");
                System.out.println("  </thread>");
                System.out.println("<tbody>");


                for (int i = 0; i < tamanhodaLista; i++) {
                    Node noproduto = ListadeProdutos.item(i);
                    if (noproduto.getNodeType() == Node.ELEMENT_NODE) {
                        Element elementoproduct = (Element) noproduto;
                        String products = elementoproduct.getAttribute("product");

                        NodeList noTags = elementoproduct.getChildNodes();

                        int tamanholistatags = noTags.getLength();
                        for (int x = 0; x < tamanholistatags; x++) {
                            Node notag = noTags.item(x);
                            if (notag.getNodeType() == Node.ELEMENT_NODE) {
                                Element elementtag = (Element) notag;


                                switch (elementtag.getTagName()) {

                                    case "vendor":
                                        System.out.println("      <td>" + elementtag.getTextContent() + "</td>");
                                        System.out.println("      <td>"  );
                                        System.out.println("     <ul>");

                                        break;

                                    case "name":
                                         System.out.println("        <li>" + elementtag.getTextContent() + "</li>");
                                        break;


                                }
                            }


                        }
                    }

                }


            } catch (ParserConfigurationException ex) {
                Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }
