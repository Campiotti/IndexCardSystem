package view;

import java.awt.*;

public interface IView {

    void setText(TextComponent textComponent, String text, boolean append);

    void setEnabled (String object, boolean enable);

}
