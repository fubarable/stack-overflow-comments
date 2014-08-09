package comments.model;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.SwingPropertyChangeSupport;
import javax.xml.bind.annotation.*;

/**
 * 
 * Comment class</br> field title -- short title String to summarize the
 * Comment.</br> </tt>title must be unique. equality, hash, compare based on
 * this</br> field text -- longer body of Comment text</br>
 * 
 * @author Pete's
 * 
 */
@XmlRootElement
@XmlType(propOrder = { "title", "text", "tagList" })
public class Comment implements Serializable {
   private static final long serialVersionUID = -5408091308440993465L;
   public static final String TAG_ADD = "tag add";
   public static final String TAG_REMOVE = "tag remove";
   public static final String SET_TEXT = "set text";
   public static final String SET_TITLE = "set title";

   private String title;
   private String text;
   private List<Tag> tagList = new ArrayList<>();
   @XmlTransient
   private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(
         this);

   public Comment(String title, String text) {
      this.title = title;
      this.text = text;
   }
   
   public Comment() {
      this("", "");
   }

   public void addPropertyChangeListener(PropertyChangeListener l) {
      pcSupport.addPropertyChangeListener(l);
   }

   public boolean addTag(Tag tag) {
      Tag oldValue = null;
      Tag newValue = tag;
      boolean result = getTagList().add(tag);
      if (result) {
         pcSupport.firePropertyChange(TAG_ADD, oldValue, newValue);
      }
      return result;
   }

   @XmlElement(name="tag")
   public List<Tag> getTagList() {
      return tagList;
   }

   @XmlElement
   public String getText() {
      return text;
   }

   @XmlElement
   public String getTitle() {
      return title;
   }

   public boolean containsTag(Tag tag) {
      return tagList.contains(tag);
   }
   
   public boolean containsAllTags(List<Tag> tags) {
      return tagList.containsAll(tags);
   }
   
   public boolean containsAllTags(Tag... tags) {
      return tagList.containsAll(Arrays.asList(tags));
   }

   public void removePropertyChangeListener(PropertyChangeListener l) {
      pcSupport.removePropertyChangeListener(l);
   }

   public boolean removeTag(Tag tag) {
      Tag oldValue = null;
      Tag newValue = tag;
      boolean result = getTagList().remove(tag);
      if (result) {
         pcSupport.firePropertyChange(TAG_REMOVE, oldValue, newValue);
      }
      return result;
   }

   public void setText(String text) {
      String oldValue = this.text;
      String newValue = text;
      this.text = text;
      pcSupport.firePropertyChange(SET_TEXT, oldValue, newValue);
   }

   public void setTitle(String title) {
      String oldValue = this.title;
      String newValue = title;
      this.title = title;
      pcSupport.firePropertyChange(SET_TITLE, oldValue, newValue);
   }

   public void setTagList(List<Tag> tagList) {
      this.tagList = tagList;
   }
   
   public int getTextLength() {
      return text.length();
   }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Comment: ");
      sb.append("Title: " + title + ", ");
      sb.append("Text: " + text + ", ");
      for (Tag tag : tagList) {
         sb.append(tag.toString() + ", ");
      }
      return sb.toString();
   }

}
