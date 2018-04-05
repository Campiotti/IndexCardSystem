package view;

import helper.ErrorLogger;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseView implements IView{

    @Override
    public void setText(String object, String text, boolean append) {
        try {
            Object myobj = this.getClass().getField(object);
            Class<?> cl = Class.forName(myobj.getClass().getName());
            Class<?> cls = Class.forName(this.getClass().getName());
            Method meth = cls.getMethod("setText");
            meth.invoke(myobj, text,null);

        } catch (NoSuchFieldException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            ErrorLogger.getInstance().log(e.getMessage());
        }
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
