package comments.model;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.SwingPropertyChangeSupport;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "comments")
public class CommentCollection implements Iterable<Comment>, Serializable {
   private static final long serialVersionUID = -7534580258533422773L;
   public static final String COMMENT_ADD = "comment add";
   public static final String COMMENT_REMOVE = "comment remove";
   @XmlTransient
   private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(
         this);

   private List<Comment> commentList = new ArrayList<>();

   // TODO: keep going

   public int size() {
      return getCommentList().size();
   }

   public boolean hasComment(Comment comment) {
      return getCommentList().contains(comment);
   }

   public List<Comment> getComments(Tag... tags) {
      List<Comment> localCommentList = new ArrayList<>();
      for (Comment comment : commentList) {
         if (comment.containsAllTags(tags)) {
            localCommentList.add(comment);
         }
      }
      return localCommentList;
   }

   public List<Comment> getComments(List<Tag> tags) {
      List<Comment> localCommentList = new ArrayList<>();
      for (Comment comment : commentList) {
         if (comment.containsAllTags(tags)) {
            localCommentList.add(comment);
         }
      }
      return localCommentList;
   }

   public boolean addComment(Comment comment) {
      Comment oldValue = null;
      Comment newValue = comment;
      boolean result = getCommentList().add(comment);
      if (result) {
         pcSupport.firePropertyChange(COMMENT_ADD, oldValue, newValue);
      }
      return result;
   }

   public boolean removeComment(Comment comment) {
      Comment oldValue = null;
      Comment newValue = comment;
      boolean result = getCommentList().remove(comment);
      if (result) {
         pcSupport.firePropertyChange(COMMENT_REMOVE, oldValue, newValue);
      }
      return result;
   }

   @Override
   public Iterator<Comment> iterator() {
      return getCommentList().iterator();
   }

   public void addPropertyChangeListener(PropertyChangeListener l) {
      pcSupport.addPropertyChangeListener(l);
   }

   public void removePropertyChangeListener(PropertyChangeListener l) {
      pcSupport.removePropertyChangeListener(l);
   }

   @XmlElement(name = "comment")
   public List<Comment> getCommentList() {
      return commentList;
   }

   public void setCommentList(List<Comment> commentList) {
      this.commentList = commentList;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Comment Collection: ");
      for (Comment comment : commentList) {
         sb.append(comment.toString() + " ");
      }
      return sb.toString();
   }
}
