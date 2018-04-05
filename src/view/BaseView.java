package view;

import helper.ErrorLogger;

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
    public void setEnabled(String object, boolean enable) {
        try {
            Object myobj = this.getClass().getField(object);
        } catch (NoSuchFieldException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }
}
