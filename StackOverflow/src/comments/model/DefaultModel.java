package comments.model;

import java.beans.PropertyChangeListener;

import javax.swing.event.SwingPropertyChangeSupport;

public class DefaultModel implements Model {
   private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(this);
   private CommentCollection commentCollection;
   
   // TODO_NOW Model, default model .... complete this first!!!

   public CommentCollection getCommentCollection() {
      return commentCollection;
   }

   @Override
   public void cleanUpForExit() {
      // TODO Model: clean up for exit method.
   }

   @Override
   public void saveComments() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void setCommentCollection(CommentCollection commentCollection) {
      CommentCollection oldValue = this.commentCollection;
      CommentCollection newValue = commentCollection;
      this.commentCollection = commentCollection;
      pcSupport.firePropertyChange(COMMENT_COLLECTION, oldValue, newValue);
   }

   @Override
   public void addPropertyChangeListener(PropertyChangeListener listener) {
      pcSupport.addPropertyChangeListener(listener);
   }

   @Override
   public void removePropertyChangeListener(PropertyChangeListener listener) {
      pcSupport.removePropertyChangeListener(listener);
   }

}
