package comments.control;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import comments.model.Model;
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
         // TODO Control: New Comment Action
         
      }
   }

   private class EditCommentAction extends MyAction {
      public EditCommentAction(String name, int mnemonic) {
         super(name, mnemonic);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Control: Edit Comment Action
         
      }
   }
   
   private class SaveCommentsAction extends MyAction {
      public SaveCommentsAction(String name, int mnemonic) {
         super(name, mnemonic);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Control: Save Comment List Action
         
      }
   }
   
   private class LoadCommentsAction extends MyAction {
      public LoadCommentsAction(String name, int mnemonic) {
         super(name, mnemonic);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Control: Load Comment List Action
         
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