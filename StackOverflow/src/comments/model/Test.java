package comments.model;

import javax.xml.bind.JAXBException;

public class Test {
   public static final String FILE_NAME = "test.xml";

   public static void main(String[] args) {
      // testWriteToFile();
      testReadFromFile();
   }

   private static void testReadFromFile() {
      try {
         CommentCollection comCol = XmlUtil.fromXmlFile(FILE_NAME);
         System.out.println(comCol);
      } catch (JAXBException e) {
         e.printStackTrace();
      }
   }

   @SuppressWarnings("unused")
   private static void testWriteToFile() {
      try {
         XmlUtil.toXmlFile(FILE_NAME, createTestComCollection());
      } catch (JAXBException e) {
         e.printStackTrace();
      }
   }

   private static CommentCollection createTestComCollection() {
      CommentCollection comCol = new CommentCollection();
      Comment comment = new Comment("title 1", "text 1");
      comment.addTag(new Tag("tag 1"));
      comment.addTag(new Tag("tag 2"));
      comment.addTag(new Tag("tag 3"));
      comCol.addComment(comment);

      comment = new Comment("title 2", "text 2");
      comment.addTag(new Tag("tag 1"));
      comment.addTag(new Tag("tag 2"));
      comment.addTag(new Tag("tag 4"));
      comCol.addComment(comment);
      return comCol;
   }

}
