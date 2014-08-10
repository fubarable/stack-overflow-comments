package comments.view;

import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import comments.model.Model;

public class ButtonRowDisplay implements MainPanelView {
   private static final String TITLE = "Button Display";
   private static final int GL_GAP = 5;
   private JComponent mainPanel = new JPanel();
   private Model model;

   public ButtonRowDisplay() {
      mainPanel.setLayout(new GridLayout(1, 0, GL_GAP, GL_GAP));
   }

   @Override
   public JComponent getMainPanel() {
      return mainPanel;
   }

   public void addAction(Action action) {
      mainPanel.add(new JButton(action));
   }

   @Override
   public String getTitle() {
      return TITLE;
   }

   @Override
   public void setModel(Model model) {
      this.model = model;
   }

   @Override
   public Model getModel() {
      return model;
   }
}
