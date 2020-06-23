package com.example.demo.controller;

import java.util.Enumeration;
import java.util.List;

import com.example.demo.entity.HoaDonDto;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import com.example.demo.service.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;


@Controller
public class ControllerHome {
	@Autowired
	private CartManager cartManager;

 
 @Autowired   
service  service ;
 @Autowired
 ProductService service2;
@RequestMapping("/")
public String home() {
	return"index";
}
@RequestMapping("/index.html")
public String home1() {
	return"index";
}
@RequestMapping("/about.html")
public String home2() {
	return"about";
}
@RequestMapping("/store.html")
public String home3() {
	return"store";
}
@RequestMapping("login")
public String home4() {
	return"login";
}
@RequestMapping("/giohang")
public String home5(HttpSession session , Model modal) {
	Cart cart = cartManager.getCart(session);
	double tong = 0;
	for (CartItem item : cart.getItems()) {
		tong += item.getSubTotal();
	}
	modal.addAttribute("tongtien", tong);
	return"shoppingCart";
}
	@RequestMapping("/thanhtoan")
	public String home6(HttpSession session , Model modal,
						@RequestParam("namethanhtoan") String name,
						@RequestParam("emailthanhtoan") String email,
						@RequestParam("sdtthanhtoan") Integer sdt,
						@RequestParam("diachithanhtoan") String diachi) {
	HoaDonDto hoaDonDto = new HoaDonDto(name,email,sdt,diachi);
	session.setAttribute("hoadondto",hoaDonDto);
		Cart cart = cartManager.getCart(session);
		double tong = 0;
		for (CartItem item : cart.getItems()) {
			tong += item.getSubTotal();
		}
		modal.addAttribute("tongtien", tong );
		return"admin/thanhtoan";
	}
	@RequestMapping("/products.html")
public String product(ModelMap map) {
	List<ProductEntity> products = service.listAll();
	map.addAttribute("product", products);
	
	return"products";
}
@RequestMapping("/addproduct")
public String addproduct(ModelMap map) {
	List<ProductEntity> products = service.listAll();
	map.addAttribute("product", products);	
	map.addAttribute("products", new  ProductEntity());	
	return"admin/addproduct";
}

@RequestMapping("/admin")
public String admin(ModelMap map) {
	//Page<ProductEntity> products = service.listAl1();
	List<ProductEntity> products = service.listAll();
	map.addAttribute("product", products);
	return "admin/admin";
}
@RequestMapping("admin/timkiem")
public String admin1(ModelMap map, @RequestParam("search") String search) {
	//Page<ProductEntity> products = service.listAl1();
	List<ProductEntity> products = service2.find(search);
	map.addAttribute("product", products);
	return "admin/admin";
}
@RequestMapping("/save")
public String save( @ModelAttribute("products") ProductEntity products ,ModelMap map) {
	service.save(products);	
	List<ProductEntity> product = service.listAll();
	map.addAttribute("product", product);	
	return"redirect:/addproduct";
}
@RequestMapping("/edit")
public String save2( @ModelAttribute("products") ProductEntity products ,ModelMap map) {
	service.save(products);	
	return"redirect:/admin";
}
//@RequestMapping("/save")
//public String addproduct(@RequestParam("product_name") String name,
//		                @RequestParam("product_price") Double price,
//		                @RequestParam("product_desc") String desc,
//		                @RequestParam("product_desc") String img) {
//	// ProductEntity product = new ProductEntity(name, price, desc, img);
//	//productService.save(product);

//	return"admin/index";
//}
//@GetMapping("/employee/create")
//public String create(ModelMap model) {
//	model.addAttribute("employee", new ProductEntity());
//	List<ProductEntity> products = service.listAll();
//	model.addAttribute("product", products);	
//	return "admin/index2";
//}
//@RequestMapping(value = "/save" ,method = RequestMethod.POST)
//public String save ( ModelMap map,@RequestParam("product_name") String name,
//        @RequestParam("product_price") Double price,       
//        @RequestParam("product_desc") String desc,
//        @RequestParam("filebutton") String img) {
//	ProductEntity product =  new ProductEntity(name, price, desc, img);
//	 service.save(product);
//		List<ProductEntity> products = service.listAll();
//		map.addAttribute("product", products);	
//	return"admin/index2";
//}
	
@RequestMapping(value = "/delete/{magigido}",method=RequestMethod.GET)
public String deteproduct(@PathVariable(name = "magigido")String id) {
	
	service.delete(Integer.parseInt(id));
	return"redirect:/admin";
	
	
}
@RequestMapping("/edit/{id}")
public ModelAndView edit(@PathVariable(name="id") int id) {	
	ModelAndView mav = new ModelAndView("/edit");
	ProductEntity products = service.get(id);
	List<ProductEntity> product = service.listAll();

	mav.addObject("products",product);
	mav.addObject("product", products);
	return mav;
	
}

}
