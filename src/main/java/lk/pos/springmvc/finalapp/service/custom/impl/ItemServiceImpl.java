package lk.pos.springmvc.finalapp.service.custom.impl;

import lk.pos.springmvc.finalapp.dto.ItemDTO;
import lk.pos.springmvc.finalapp.entity.Item;
import lk.pos.springmvc.finalapp.repository.ItemRepository;
import lk.pos.springmvc.finalapp.service.custom.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDTO> getAllItems() {
        List<ItemDTO> items = itemRepository.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());
        return items;
    }

    public String saveItem(ItemDTO item) {
        boolean itemExists = isItemExists(item.getCode());
        if (itemExists){
            return null;
        }
        return itemRepository.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).getCode();
    }

    public void updateItem(ItemDTO item) {
        itemRepository.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
    }

    public void deleteItem(String code) {
        itemRepository.deleteById(code);
    }

    @Override
    public ItemDTO getItemById(String code) {
        Item item = itemRepository.getOne(code);
        ItemDTO itemDTO = new ItemDTO(item.getCode(), item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
        return itemDTO;
    }

    @Override
    public boolean isItemExists(String code) {
        return itemRepository.existsById(code);
    }

}
