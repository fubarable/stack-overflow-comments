package comments.model;

import java.beans.PropertyChangeListener;

public interface Model {
   String COMMENT_COLLECTION = "comment collection";

   void cleanUpForExit();

   void saveComments();

   void setCommentCollection(CommentCollection commentCollection);
   
   void addPropertyChangeListener(PropertyChangeListener listener);
   
   void removePropertyChangeListener(PropertyChangeListener listener);

}
