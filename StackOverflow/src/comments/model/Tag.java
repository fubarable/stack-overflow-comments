package comments.model;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

/**
 * Tags for sorting and grouping comments
 * equals, hash, compare are case insensitive
 * implements case insensitive Comparable
 * @author Pete
 *
 */
@XmlRootElement
public class Tag implements Comparable<Tag>, Serializable {
   private static final long serialVersionUID = 2456238546839385214L;
   private String tagName;

   public Tag(String tagName) {
      this.tagName = tagName.toLowerCase();
   }
   
   public Tag() {
      this("");
   }

   @Override
   public int compareTo(Tag o) {
      return tagName.toLowerCase().compareTo(o.tagName.toLowerCase());
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Tag other = (Tag) obj;
      if (tagName == null) {
         if (other.tagName != null)
            return false;
      } else if (!tagName.equalsIgnoreCase(other.tagName))
         return false;
      return true;
   }

   @XmlElement(name="name")
   public String getTagName() {
      return tagName;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result
            + ((tagName == null) ? 0 : tagName.toLowerCase().hashCode());
      return result;
   }

   public void setTagName(String tagName) {
      this.tagName = tagName.toLowerCase();
   }

   @Override
   public String toString() {
      return tagName;
   }

}
