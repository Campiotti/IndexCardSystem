package view;

import javafx.scene.control.Control;

import java.awt.*;

public abstract class BaseView implements IView{

    @Override
    public void setText(TextComponent textComponent, String text, boolean append) {
        if (append)
            textComponent.setText(textComponent.getText() + text);
        else
            textComponent.setText(text);
    }

    @Override
    public void setEnabled(Control control, boolean enable) {

            control.disableProperty().set(!enable);

    }
}
