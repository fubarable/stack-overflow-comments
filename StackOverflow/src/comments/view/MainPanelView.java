package comments.view;

import javax.swing.JComponent;

import comments.model.Model;

public interface MainPanelView {

   public abstract JComponent getMainPanel();

   public abstract String getTitle();

   void setModel(Model model);

   Model getModel();

}