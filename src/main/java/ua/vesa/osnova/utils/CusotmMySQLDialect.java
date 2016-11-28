package ua.vesa.osnova.utils;

import org.hibernate.dialect.MySQLDialect;

public class CusotmMySQLDialect extends MySQLDialect{
    public String getTableTypeString(){
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
