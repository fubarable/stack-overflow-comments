package comments.view;

import java.awt.BorderLayout;

import javax.swing.*;

import comments.model.Model;

public class DefaultView implements View {

   private static final String TITLE = "StackOverflow Comments";
   private static final int BL_GAP = 0;
   private JPanel mainComponent = new JPanel();
   private Model model;
   private CommentDisplay commentDisplay = new CommentDisplay();
   private ButtonRowDisplay buttonRowDisplay = new ButtonRowDisplay();
   
   public DefaultView() {
      mainComponent.setLayout(new BorderLayout(BL_GAP, BL_GAP));
      mainComponent.add(commentDisplay.getMainPanel(), BorderLayout.CENTER);
      mainComponent.add(buttonRowDisplay.getMainPanel(), BorderLayout.PAGE_END);
   }
   
   @Override
   public void addButtonRowAction(Action action) {
      buttonRowDisplay.addAction(action);
   }
   
   @Override
   public JComponent getMainPanel() {
      return mainComponent;
   }

   @Override
   public String getTitle() {
      return TITLE;
   }

   @Override
   public void setModel(Model model) {
      this.model = model;
      //!! here! add more sets
   }
   
   // **** test code
   private static void createAndShowGUI() {
      DefaultView view = new DefaultView();

      JFrame frame = new JFrame(view.getTitle()); //!!
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

   @Override
   public Model getModel() {
      return model;
   }

}
