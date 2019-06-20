package lk.pos.springmvc.finalapp.controller;

import lk.pos.springmvc.finalapp.dto.ItemDTO;
import lk.pos.springmvc.finalapp.service.custom.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("api/v1/items")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping(value = "/{code:I\\d{3}}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> getItem(@PathVariable("code") String code) {
        ItemDTO dto = null;
        if (itemService.isItemExists(code)) {
            dto = itemService.getItemById(code);
        }
        System.out.println("dto : " + dto);
        return new ResponseEntity<ItemDTO>(dto, (dto != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveItem(@RequestBody ItemDTO itemDTO) {
        System.out.println("itemDTO :" +itemDTO);
        if (itemDTO.getCode().isEmpty() || itemDTO.getDescription().isEmpty() || itemDTO.getUnitPrice() == 0.00 || itemDTO.getQtyOnHand() == 0) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } else {
            String saveId = itemService.saveItem(itemDTO);
            return new ResponseEntity<String>("\""+saveId+"\"", (saveId != null) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{code:I\\d{3}}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable("code") String code, @RequestBody ItemDTO itemDTO) {
        if (!itemService.isItemExists(code)) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        if (itemDTO.getDescription().isEmpty() || itemDTO.getUnitPrice() == 0.00 || itemDTO.getQtyOnHand() == 0) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } else {
            itemDTO.setCode(code);
            itemService.updateItem(itemDTO);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{code:I\\d{3}}")
    public ResponseEntity<Void> deleteItem(@PathVariable("code") String code) {
        if (!itemService.isItemExists(code)) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        itemService.deleteItem(code);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
