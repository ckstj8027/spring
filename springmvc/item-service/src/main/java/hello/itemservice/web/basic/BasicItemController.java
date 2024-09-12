package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@RequestMapping("/basic/items")
@RequiredArgsConstructor
@Controller
public class BasicItemController {


    private final ItemRepository itemRepository;

    // 상품 전체 뷰
    @GetMapping
    public  String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);



        return "basic/items";
    }




    //상품상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId , Model model){
        Item item=itemRepository.findById(itemId);
        model.addAttribute("item",item);

        return "basic/item";


    }
    // 상품 전체 뷰 에서 에드폼 화면 갯 하기
    @GetMapping("/add")
    public String addForm(){
        return "/basic/addForm";
    }

  //  @PostMapping("/add")
    public String save(@RequestParam String itemName ,Integer price ,Integer quantity ,Model model ){

        Item item=new Item(itemName,price,quantity);

        itemRepository.save(item);


        model.addAttribute("item",item);


        return "basic/item";



    }


   // @PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item,Model model ){

        itemRepository.save(item);

        model.addAttribute("item",item);

        return "basic/item";


    }


 //   @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item ){

        itemRepository.save(item);



        return "basic/item";


    }


  //  @PostMapping("/add")
    public String addItemV3(@ModelAttribute("item") Item item ){

        itemRepository.save(item);



        return "redirect:/basic/items/" + item.getId();


    }
    // 저장한거 폼 내용 서버에서 받기
    @PostMapping("/add")
    public String addItemV4(@ModelAttribute("item") Item item , RedirectAttributes redirectAttributes){

        Item savedItem = itemRepository.save(item);
        // 여기선 패스베리어블로 itemId 를 받지 않았기때문에 아이템으로 부터 아이디를 얻고
        //리다이렉트에다가 모델마냥 저장
        redirectAttributes.addAttribute("itemId",savedItem.getId());
        redirectAttributes.addAttribute("status",true);


        return "redirect:/basic/items/{itemId}" ;


    }




    // 편집하는 폼으로 보냄

    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId,Model model){
        Item item=itemRepository.findById(itemId);


        model.addAttribute("item",item);

        return "/basic/editForm";


    }

    //편집하는 폼 내용 서버에서 받기
    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){

        itemRepository.update(itemId,item);



        return "redirect:/basic/items/{itemId}";


    }









    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,11));
        itemRepository.save(new Item("itemB",20000,33));
    }



}
