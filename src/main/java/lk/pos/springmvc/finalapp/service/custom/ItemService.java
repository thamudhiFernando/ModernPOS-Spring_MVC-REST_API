package lk.pos.springmvc.finalapp.service.custom;

import lk.pos.springmvc.finalapp.dto.ItemDTO;
import lk.pos.springmvc.finalapp.service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {

    public List<ItemDTO> getAllItems() ;

    public String saveItem(ItemDTO item);

    public void updateItem(ItemDTO item) ;

    public void deleteItem(String code) ;

    public ItemDTO getItemById(String code);

    boolean isItemExists(String code);
}
