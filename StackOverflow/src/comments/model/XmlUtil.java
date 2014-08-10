package comments.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlUtil {
   public static final String FILE_NAME = "comments.xml";

   public static void toXmlFile(String fileName,
         CommentCollection commentCollection) throws JAXBException {
      File file = null;
      file = new File(fileName);
      JAXBContext context = JAXBContext.newInstance(CommentCollection.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marshaller.marshal(commentCollection, file);
      // marshaller.marshal(commentCollection, System.out);
   }
   
   public static CommentCollection fromXmlFile(String fileName) throws JAXBException {
      File file = new File(fileName);
      return fromXmlFile(file);
   }

   public static CommentCollection fromXmlFile(File file) throws JAXBException {
      JAXBContext context = JAXBContext.newInstance(CommentCollection.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      return (CommentCollection) unmarshaller.unmarshal(file);
   }

}
