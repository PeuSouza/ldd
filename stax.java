import org.xml.sax.helpers.DefaultHandler;
import javax.xml.stream.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class STAX extends DefaultHandler{


       public static void main(String[] args) throws IOException, XMLStreamException {
           XMLOutputFactory xmlof = XMLOutputFactory.newFactory();
           Writer writer = new FileWriter("saidab.html");
           XMLStreamWriter xmlsw = (xmlof.createXMLStreamWriter(writer));

           XMLInputFactory xmlif = XMLInputFactory.newFactory();

           // leitor
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
           List<String> percenty = new ArrayList();

           List<String> ivendor = new ArrayList();

           boolean qvendor = false;
           boolean qname = false;
           boolean qbuy = false;
           boolean qmsrp = false;

           String element = null;

           while (xmlsr.hasNext()) {
               switch (xmlsr.next()) {
                   case XMLStreamReader.START_ELEMENT:

                       element = xmlsr.getLocalName();

                       if (element.equals("name")) {
                           qname = true;
                       }

                       if (element.equals("buyPrice")) {
                           qbuy = true;
                       }

                       if (element.equals("MSRP")) {
                           qmsrp = true;
                       }
                       break;

                   case XMLStreamReader.END_ELEMENT:

                       if (xmlsr.getLocalName().equals("product")) {
                       }
                       break;

                   case XMLStreamReader.CHARACTERS:

                       if (qname == true) {
                           name.add(xmlsr.getText());
                           qname = false;
                       }

                       if (qbuy == true) {
                           buy.add(xmlsr.getText());
                           qbuy = false;
                       }


                       if (qmsrp == true) {
                           msrp.add(xmlsr.getText());
                           qmsrp = false;
                       }
                       break;

               }
           }

           for (int a = 0; a < name.size(); a++) {
               double valorx = Double.parseDouble(buy.get(a));
               double valory = Double.parseDouble(msrp.get(a));


               double calculo = (valorx * 100) / valory;

               percent.add(String.valueOf((int) Math.round(calculo)));

           }


           for (int i = 0; i < percent.size(); i++) {
               percentx.add(percent.get(i));
           }

           percent.sort((o1, o2) -> (int) Math.round(Float.parseFloat(o2)) - (int) Math.round(Float.parseFloat(o1)));

           XMLOutputFactory xmlof2 = XMLOutputFactory.newFactory();

           xmlsw.writeStartDocument();

           xmlsw.writeStartElement("ol");
           for (int e = 0; e < percent.size(); e++) {
               for (int p = 0; p < percent.size(); p++) {
                   if (percent.get(e).contains(percentx.get(p))){
                       if (!name2.contains(name.get(p))) {
                           name2.add(name.get(p));
                           xmlsw.writeStartElement("li");
                           xmlsw.writeCharacters(name.get(p) + " (" + percent.get(e) + ")");
                           xmlsw.writeEndElement();
                       }
               }
           }

       }
            xmlsw.writeEndElement();
            xmlsw.writeEndDocument();
            xmlsw.close();
        }
    }






