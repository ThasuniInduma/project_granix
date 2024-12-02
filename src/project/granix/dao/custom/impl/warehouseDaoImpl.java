package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import Entity.warehouseEntity;
import dao.CrudUtil;
import dao.custom.warehouseDao;

public class warehouseDaoImpl implements warehouseDao{

    @Override
    public boolean add(warehouseEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO warehouse VALUES(?,?,?,?,?)", t.getWarehouse_ID(),t.getWarehouse_name(),t.getMax_Capacity(),t.getLocation(),t.getWarehouse_Telephone());
    }

    @Override
    public boolean update(warehouseEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE warehouse SET Warehouse_name = ?, Max_Capacity = ?, Location = ?, Warehouse_Telephone = ? WHERE Warehouse_ID = ?",t.getWarehouse_name(),t.getMax_Capacity(),t.getLocation(),t.getWarehouse_Telephone(),t.getWarehouse_ID());
    }


    @Override
    public warehouseEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT*FROM warehouse WHERE Warehouse_ID = ?", id);
        
        while (rst.next()) {            
            warehouseEntity warehouseEntity = new warehouseEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5));
            return warehouseEntity;
        }
        return null;
    }

    @Override
    public ArrayList<warehouseEntity> getAll() throws Exception {
        ArrayList<warehouseEntity> warehouseEntitys = new ArrayList<>();
        
        ResultSet rst = CrudUtil.executeQuery("SELECT*FROM warehouse");
        while (rst.next()) {            
            warehouseEntity warehouseEntity = new warehouseEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5));
                    warehouseEntitys.add(warehouseEntity);
        }
         return warehouseEntitys;
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
