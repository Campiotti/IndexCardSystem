package view;


import javafx.scene.control.Control;

import java.awt.*;

public interface IView {

    void setText(TextComponent textComponent, String text, boolean append);

    void setEnabled (Control control, boolean enable);

}
