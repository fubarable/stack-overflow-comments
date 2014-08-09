package comments.main;

import javax.swing.*;

import comments.control.Control;
import comments.model.DefaultModel;
import comments.model.Model;
import comments.view.DefaultView;
import comments.view.View;

public class CommentsMain {
   private static void createAndShowGUI() {
      Model model = new DefaultModel();
      View view = new DefaultView();
      new Control(model, view);

      JFrame frame = new JFrame(view.getTitle());
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.getContentPane().add(view.getMainPanel());
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
