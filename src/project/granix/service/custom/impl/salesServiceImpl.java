package service.custom.impl;

import java.util.ArrayList;

import Entity.salesEntity;
import dao.DaoFactory;
import dao.custom.salesDao;
import dto.salesdto;
import service.custom.salesService;

public class salesServiceImpl implements salesService{
    salesDao salesDao = (salesDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.SALES);

    @Override
    public String addsales(salesdto dto) throws Exception {
        salesEntity salesEntity = new salesEntity(dto.getStock_ID(), dto.getBuyer_ID(), dto.getQuantity_obtained());
        
        if(salesDao.add(salesEntity)){
            return "successfully added";
        }else{
            return "fail";
        }
    }

    @Override
    public String updatesales(salesdto dto) throws Exception {
        salesEntity salesEntity = new salesEntity(dto.getStock_ID(), dto.getBuyer_ID(), dto.getQuantity_obtained());
        
        if(salesDao.update(salesEntity)){
            return "successfully updated";
        }else{
            return "fail";
        }
    }

    

    @Override
    public salesdto getsales(String id) throws Exception {
        salesEntity entity = salesDao.get(id);
        return new salesdto(entity.getStock_ID(), entity.getBuyer_ID(), entity.getQuantity_obtained());
    }

    @Override
    public ArrayList<salesdto> getAllsales() throws Exception {
        ArrayList<salesdto> salesDtos = new ArrayList<>();
        ArrayList<salesEntity> salesEntitys = salesDao.getAll();
        
        for (salesEntity entity : salesEntitys) {
            salesdto dto = new salesdto(entity.getStock_ID(), entity.getBuyer_ID(), entity.getQuantity_obtained());
            salesDtos.add(dto);
        }
        return salesDtos;
    
    }

    @Override
    public String deletesales(String id) throws Exception {
        if (salesDao.delete(id)) {
            return "Successfully Delete";
        } else {
            return "Fail";
        }
    }
}
