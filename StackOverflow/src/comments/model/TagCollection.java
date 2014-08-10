package comments.model;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import javax.swing.event.SwingPropertyChangeSupport;

public class TagCollection implements Iterable<Tag>, Serializable {
   private static final long serialVersionUID = -6531627038629134808L;
   public static final String TAG_ADD = "tag add";
   public static final String TAG_REMOVE = "tag remove";
   private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(
         this);
   private NavigableSet<Tag> tagSet = new TreeSet<>();

   public boolean hasTag(Tag tag) {
      return tagSet.contains(tag);
   }

   public boolean addTag(Tag tag) {
      Tag oldValue = null;
      Tag newValue = tag;
      boolean result = tagSet.add(tag);
      if (result) {
         pcSupport.firePropertyChange(TAG_ADD, oldValue, newValue);
      }
      return result;
   }

   public boolean removeTag(Tag tag) {
      Tag oldValue = null;
      Tag newValue = tag;
      boolean result = tagSet.remove(tag);
      if (result) {
         pcSupport.firePropertyChange(TAG_REMOVE, oldValue, newValue);
      }
      return result;
   }

   public int size() {
      return tagSet.size();
   }

   @Override
   public Iterator<Tag> iterator() {
      return tagSet.iterator();
   }

   public void addPropertyChangeListener(PropertyChangeListener l) {
      pcSupport.addPropertyChangeListener(l);
   }

   public void removePropertyChangeListener(PropertyChangeListener l) {
      pcSupport.removePropertyChangeListener(l);
   }
}
