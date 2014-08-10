package comments.control;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public abstract class MyAction extends AbstractAction {
   public MyAction(String name, int mnemonic) {
      super(name);
      putValue(MNEMONIC_KEY, mnemonic);
   }
}