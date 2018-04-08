package model;


import helper.ErrorLogger;
import javafx.beans.property.SimpleStringProperty;
import persistence.ConnectionManager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public abstract class BaseModel<T> implements IEntity{
    private Field[] fields;
    public SimpleStringProperty id = new SimpleStringProperty();
    protected String tableName;
    protected Connection connection;
    protected Map<String, Object> managedProperties = new HashMap<String, Object>();


    public BaseModel() throws SQLException {
        this.fields = getClass().getDeclaredFields();
        try {
            this.connection = ConnectionManager.getConnection();
        } catch (IOException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
        this.tableName = getClass().getSimpleName().toLowerCase();
        this.addProperty("id", this.id);
    }

    public void addProperty(String name, Object property) {
        this.managedProperties.put(name, property);
    }

    private String replaceLastOccurence(String str) {
        int ind = str.lastIndexOf(",");
        if (ind >= 0)
            str = new StringBuilder(str).replace(ind, ind + 1, "").toString();
        return str;
    }

    public void save() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        String query = "INSERT INTO `" + this.tableName + "`(" + this.getColumnValues(true) + ") VALUES ( " + this.getFieldValues(true) + " );";
        Statement stmt = this.connection.createStatement();
        stmt.executeUpdate(query);
        ErrorLogger.getInstance().log(query);
    }


    protected String getColumnValues(boolean ignoreId) {
        StringBuilder columns = new StringBuilder();
        Iterator it = this.managedProperties.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(ignoreId && pair.getKey().equals("id")){
                continue;
            }
            columns.append("`").append(pair.getKey()).append("`,");
        }
        return this.replaceLastOccurence(columns.toString());
    }


    protected String getFieldValues(boolean ignoreId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        StringBuilder values = new StringBuilder();
        Iterator it = this.managedProperties.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Class<?> cls = Class.forName(pair.getValue().getClass().getName());
            Method meth = cls.getMethod("get");
            Object val = meth.invoke(pair.getValue(), null);
            if(pair.getKey().equals("id") && ignoreId){
                continue;
            }
            if(pair.getKey().equals("id") && !ignoreId){
                values.append(",");
            }else if(val == null){
                values.append("null,");
            }else{
                values.append("'").append(val).append("',");
            }
        }
        return this.replaceLastOccurence(values.toString());
    }


    public void update() {
        try {
            List<String> columns = getListByCommaString(getColumnValues(true));
            List<String> values = getListByCommaString(getFieldValues(true));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE ").append(this.tableName).append(" SET ");
            for (int i = 0; i < columns.size(); i++) {
                stringBuilder.append(columns.get(i));
                stringBuilder.append(" = ");
                stringBuilder.append(values.get(i)).append(", ");
            }
            String sql = stringBuilder.toString();
            sql=replaceLastOccurence(sql);
            sql+=" WHERE ID = "+this.id.get();
            Statement stmt = this.connection.createStatement();
            stmt.execute(sql);
        } catch (IllegalAccessException | NoSuchMethodException | ClassNotFoundException | InvocationTargetException | SQLException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }

    }


    protected void saveOrUpdate(T modelToSave) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        if (this.id.get() != null) {
            this.update();
        } else {
            this.save();
        }
    }

    private boolean doesFieldKeyExist(String fieldToSearch) {
        for (Field field : fields) {
            if (field.getName().equals(fieldToSearch)) {
                return true;
            }
        }
        return false;
    }

    public void delete(){
        if(this.id.get() != null){
            String query = "DELETE FROM "+this.tableName+" WHERE id = "+this.id.get();
            try {
                Statement stmt = this.connection.createStatement();
                stmt.execute(query);
            } catch (SQLException e) {
                ErrorLogger.getInstance().log(e.getLocalizedMessage());
            }
        }
    }
    public List<String> getListByCommaString(String commaList){

        return new ArrayList<>(Arrays.asList(commaList.split(",")));
    }
    @Override
    public void patchData(Object data[], boolean ignoreId){
        try{StringBuilder values = new StringBuilder();
        Iterator it = this.managedProperties.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Class<?> cls = Class.forName(pair.getValue().getClass().getName());
            Method meth = cls.getMethod("set");
            Object val = meth.invoke(pair.getValue(), data[i]);
            if(pair.getKey().equals("id") && ignoreId){
                continue;
            }
            if(pair.getKey().equals("id") && !ignoreId){
                values.append(",");
            }else if(val == null){
                values.append("null,");
            }else{
                values.append("'").append(val).append("',");
            }
        }} catch (NoSuchMethodException | IllegalAccessException | ClassNotFoundException | InvocationTargetException e) {
            ErrorLogger.getInstance().log(e.getLocalizedMessage());
        }
    }
    /*protected BaseModel<T> populateObject(ResultSet res, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
        // BaseModel<Stundent> myObject = new BaseModel<Stundent>() ;
        BaseModel<T> myObject = (BaseModel<T>) clazz.newInstance();
        Iterator it = myObject.getManagedProperties().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getValue() instanceof SimpleStringProperty) {
                ((SimpleStringProperty) pair.getValue()).set(res.getString((String) pair.getKey()));
            } else if (pair.getValue() instanceof SimpleIntegerProperty) {
                ((SimpleIntegerProperty) pair.getValue()).set(res.getInt((String) pair.getKey()));
            }
        }
        return myObject;
    }*/

}

// REFLECTION API
/* System.out.println(field.getType().toString().replace("class ", ""));
 *//*Class cls = Class.forName(field.getType().toString().replace("class ", ""));*//*
            SimpleStringProperty myTestObject = new SimpleStringProperty();
            Class<?> cls = Class.forName(field.getClass().getName());
            Method meth = cls.getMethod("get");
            meth.invoke(field,null);
            *//*Object myTestObject = cls.newInstance();*//*
 *//*Method myMethod = field.get(myTestObject).getClass().getDeclaredMethod("get");*//*
            String myObj = ((SimpleStringProperty) field.get(myTestObject)).get();
            Method myMethod = myObj.getClass().getDeclaredMethod("get");
            values.append("`" + myMethod.invoke(field.get(myTestObject)) + "`,");*/
