public class stax {

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

    public class Main extends DefaultHandler {
        public static void main(String[] args) throws IOException, XMLStreamException {
            XMLOutputFactory xmlof = XMLOutputFactory.newFactory();
            Writer writer = new FileWriter("LDD2/STAX.html");
            XMLStreamWriter xmlsw = (xmlof.createXMLStreamWriter(writer));

            XMLInputFactory xmlif = XMLInputFactory.newFactory();

            Reader reader;
            reader = new FileReader("products.xml");
            XMLStreamReader xmlsr = xmlif.createXMLStreamReader(reader);

            List<String> vendor = new ArrayList();

            List<String> name = new ArrayList();
            List<String> buy = new ArrayList();
            List<String> msrp = new ArrayList();
            List<String> percent = new ArrayList();


            List<String> name2 = new ArrayList();
            List<String> percentx = new ArrayList();
            List<String> ppercenty = new ArrayList();

            List<String> ivendor = new ArrayList();

            boolean qvendor = false;
            boolean qname = false;
            boolean qbuy = false;
            boolean qmsrp= false;

            String variavel = null;

            while (xmlsr.hasNext()) {
                switch (xmlsr.next()) {
                   case XMLStreamReader.startvariavel:
                      variavel = xmlsr.getLocalName();

                 if (variavel.equals("name"))
                   {
                     qnome = true;
                   }

                 if (variavel.equals("buyPrice"))
                   {
                     qbuy = true;
                   }

                 if (variavel.equals("MSRP"))
                   {
                     qmsrp = true;
                   }
                   break;

                    case XMLStreamReader.endvariavel:
                        if (xmlsr.getLocalName().equals("product")) {
                        }
                        break;

                    case XMLStreamReader.characters:

                        if (qnome == true) {
                            name.add(xmlsr.getText());
                        }
                            qname = false;
                        break;

                        if (qbuy == true) {
                            buy.add(xmlsr.getText());
                        }
                            qbuy = false;
                        break;

                        if (qmsrp == true) {
                            msrp.add(xmlsr.getText());
                        }
                            qmsrp = false;
                        break;
                }
            }


            for(int a = 0;a < nomes.size();a++){
                double valorx = Double.parseDouble(buys.get(a));
                double valory = Double.parseDouble(msrp.get(a));


                double percenty = (valorx * 100)/  valory;

                percentx.add(String.valueOf((int) Math.round(percenty)));
            }

            System.out.println(percent);

            for (int k = 0; k < percent.size();k++)
            {
                percentx.add(porcentagem.get(k));
            }

            percent.sort((o1, o2) -> (int) Math.round(Float.parseFloat(o2)) - (int) Math.round(Float.parseFloat(o1)));


            XMLOutputFactory xmlof2 = XMLOutputFactory.newFactory();
            Writer writer2 = new FileWriter("STAX.html");

            xmlsw.writeStartDocument();

            xmlsw.writeStartElement("ol");
            for (int q = 0; q < percent.size();q++)
            {
                for (int w = 0; w < percent.size();w++)
                {
                    if (percent.get(q).contains(pescentx.get(w)))
                        if (!name2.contains(name.get(w))){
                            name2.add(name.get(w));
                            xmlsw.writeStartElement("li");
                            xmlsw.writeCharacters(name.get(w) + " ("+ percent.get(q)+ ")");
                            xmlsw.writeEndElement();
                        }
                }
            }

            xmlsw.writeEndElement();
            xmlsw.writeEndDocument();
            xmlsw.close();
       }
    }
}
