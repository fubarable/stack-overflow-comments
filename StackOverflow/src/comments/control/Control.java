package comments.control;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;

import comments.model.CommentCollection;
import comments.model.Model;
import comments.model.XmlUtil;
import comments.view.View;

@SuppressWarnings("serial")
public class Control {

   private Model model;
   private View view;

   public Control(Model model, View view) {
      this.model = model;
      this.view = view;
      
      view.addButtonRowAction(new NewCommentAction("New", KeyEvent.VK_N));
      view.addButtonRowAction(new EditCommentAction("Edit", KeyEvent.VK_E));
      view.addButtonRowAction(new LoadCommentsAction("Load", KeyEvent.VK_L));
      view.addButtonRowAction(new SaveCommentsAction("Save", KeyEvent.VK_S));
      view.addButtonRowAction(new ExitAction("Exit", KeyEvent.VK_X));
   }
   
   private class NewCommentAction extends MyAction {
      public NewCommentAction(String name, int mnemonic) {
         super(name, mnemonic);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         view.createAndShowNewCommentView();
      }
   }

   private class EditCommentAction extends MyAction {
      public EditCommentAction(String name, int mnemonic) {
         super(name, mnemonic);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         view.createAndShowEditCommentView();
      }
   }
   
   private class SaveCommentsAction extends MyAction {
      public SaveCommentsAction(String name, int mnemonic) {
         super(name, mnemonic);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         model.saveComments();
      }
   }
   
   private class LoadCommentsAction extends MyAction {
      public LoadCommentsAction(String name, int mnemonic) {
         super(name, mnemonic);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         JFileChooser chooser = new JFileChooser(System.getProperty("user.dir")); //!!
         chooser.setAcceptAllFileFilterUsed(false);
         chooser.setMultiSelectionEnabled(false);
         FileNameExtensionFilter filter = new FileNameExtensionFilter(
               "XML Files", "xml");
         chooser.setFileFilter(filter);
         int returnVal = chooser.showOpenDialog(view.getMainPanel());
         if(returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
               CommentCollection commentCollection = XmlUtil.fromXmlFile(file);
               model.setCommentCollection(commentCollection);
            } catch (JAXBException e1) {
               // TODO improve response to exception
               e1.printStackTrace();
            }
            System.out.println("You chose to open this file: " +
                 chooser.getSelectedFile().getName());
         }
      }
   }
   
   private class ExitAction extends MyAction {
      public ExitAction(String name, int mnemonic) {
         super(name, mnemonic);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         model.cleanUpForExit();
         Window win = SwingUtilities.getWindowAncestor(view.getMainPanel());
         win.dispose();
      }
   }

}