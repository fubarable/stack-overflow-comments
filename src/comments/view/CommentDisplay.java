package comments.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import comments.model.Comment;
import comments.model.Model;
import comments.model.Tag;

public class CommentDisplay implements MainPanelView {
   private static final String TITLE = "Comment Display";
   private static final int TF_COLUMNS = 30;
   private static final int TA_ROWS = 15;
   private static final int TA_COLUMNS = TF_COLUMNS;
   private static final int BL_GAP = 0;
   private static final boolean FOCUSABLE = false; //!!! change to false!
   private static final Tag TAG_LIST_PROTOTYPE_CELL = new Tag("01234567890123");
   private static final int TEXT_COUNT_FIELD_COLS = 4;
   protected static final String TEXT_LENGTH_FORMAT = "%03d";
   private JPanel mainPanel = new JPanel();
   private Comment comment;
   private CommentPropertyListener commentPropertyListener = null;
   private JTextField titleField = new JTextField(TF_COLUMNS);
   private JTextArea textArea = new JTextArea(TA_ROWS, TA_COLUMNS);
   private JList<Tag> tagList = new JList<Tag>();
   private JComponent[] focusableComps = { titleField, textArea, tagList };
   private JTextField textLengthField = new JTextField(TEXT_COUNT_FIELD_COLS);
   private Model model;

   public CommentDisplay(Comment comment) {
      setComment(comment);
      textArea.setWrapStyleWord(true);
      textArea.setLineWrap(true);

      tagList.setPrototypeCellValue(TAG_LIST_PROTOTYPE_CELL);

      JScrollPane textAreaScrollPane = new JScrollPane(textArea);
      textAreaScrollPane
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      JScrollPane tagScrollPane = new JScrollPane(tagList);
      JPanel tagScrollWrapPanel = new JPanel(new BorderLayout());
      tagScrollWrapPanel.add(tagScrollPane);
      tagScrollWrapPanel.setBorder(BorderFactory.createTitledBorder("Tags"));
      textLengthField.setFocusable(false);
      
      textArea.getDocument().addDocumentListener(new DocumentListener() {
         
         @Override
         public void removeUpdate(DocumentEvent dEvt) {
            updateTextLength(dEvt);
         }
         
         @Override
         public void insertUpdate(DocumentEvent dEvt) {
            updateTextLength(dEvt);
         }
         
         @Override
         public void changedUpdate(DocumentEvent dEvt) {
            updateTextLength(dEvt);
         }

         private void updateTextLength(DocumentEvent dEvt) {
            int textLength = dEvt.getDocument().getLength();
            String textLengthStr = String.format(TEXT_LENGTH_FORMAT, textLength);
            textLengthField.setText(textLengthStr);
         }
      });

      setFocusable(FOCUSABLE);
      
      JPanel northPanel = new JPanel();
      northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.LINE_AXIS));
      northPanel.add(new JLabel("Title:"));
      northPanel.add(Box.createHorizontalStrut(3));
      northPanel.add(titleField);
      northPanel.add(Box.createHorizontalStrut(10));
      northPanel.add(new JLabel("Text Length:"));
      northPanel.add(Box.createHorizontalStrut(3));
      northPanel.add(textLengthField);

      mainPanel.setLayout(new BorderLayout(BL_GAP, BL_GAP));
      mainPanel.add(northPanel, BorderLayout.PAGE_START);
      mainPanel.add(textAreaScrollPane, BorderLayout.CENTER);
      mainPanel.add(tagScrollWrapPanel, BorderLayout.LINE_END);
   }

   public CommentDisplay() {
      this(new Comment());
   }

   public Comment getComment() {
      return comment;
   }

   /**
    * 
    * removes prop change listener from old comment, nulls both sets comment
    * field and adds prop change listener
    * 
    * @param comment
    */
   public void setComment(Comment comment) {
      if (this.comment != null && commentPropertyListener != null) {
         this.comment.removePropertyChangeListener(commentPropertyListener);
         commentPropertyListener = null;
      }
      this.comment = comment;
      titleField.setText(comment.getTitle());
      textArea.setText(comment.getText());
      DefaultListModel<Tag> tagListModel = new DefaultListModel<>();
      for (Tag tag : comment.getTagList()) {
         tagListModel.addElement(tag);
      }
      tagList.setModel(tagListModel);
      commentPropertyListener = new CommentPropertyListener();
      comment.addPropertyChangeListener(commentPropertyListener);
   }

   @SuppressWarnings("serial")
   public void setFocusable(final boolean focusable) {
      for (JComponent comp : focusableComps) {
         comp.setFocusable(focusable);
         if (comp instanceof JList<?>) {
            ((JList<?>) comp).setCellRenderer(new DefaultListCellRenderer() {

               public Component getListCellRendererComponent(JList<?> list,
                     Object value, int index, boolean isSelected,
                     boolean cellHasFocus) {
                  if (!focusable) {
                     isSelected = false;
                     cellHasFocus = false;
                  }

                  super.getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);

                  return this;
               }
            });
         }
      }
   }

   @Override
   public JComponent getMainPanel() {
      return mainPanel;
   }

   @Override
   public String getTitle() {
      return TITLE;
   }

   public String getTextFieldText() {
      return titleField.getText();
   }

   public String getTextAreaText() {
      return textArea.getText();
   }

   @Override
   public void setModel(Model model) {
      this.model = model;
   }

   @Override
   public Model getModel() {
      return model;
   }

   private class CommentPropertyListener implements PropertyChangeListener {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
         // TODO Listen for changes in the loaded Comment and respond to changes
      }
   }

   private static void createAndShowGUI() {
      CommentDisplay mainPanel = new CommentDisplay();
      Comment comment = new Comment("Title", "This is my comment text");
      comment.addTag(new Tag("tag 1"));
      comment.addTag(new Tag("tag 2"));
      comment.addTag(new Tag("tag 3"));
      mainPanel.setComment(comment);

      JFrame frame = new JFrame(mainPanel.getTitle());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel.getMainPanel());
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      });
   }
}
