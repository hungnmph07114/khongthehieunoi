package com.example.demo.paypal.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.Role;
import com.example.demo.model.CartItem;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.service.HoaDonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cart;
import com.example.demo.model.CartManager;
import com.example.demo.paypal.config.PaypalPaymentIntent;
import com.example.demo.paypal.config.PaypalPaymentMethod;
import com.example.demo.paypal.service.PaypalService;
import com.example.demo.paypal.utls.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PaymentController {

	
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;
	@Autowired
    private CartManager cartManager;
	@Autowired
	private HoaDonRepository hoadonservice;
	
	@GetMapping("/")
	public String index(){
		return "index";
	}
	
	@PostMapping("/pay")
	public String pay(HttpServletRequest request,@RequestParam("price") double price ){
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(
					price, 
					"USD", 
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.sale,
					"payment description", 
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(){
		return "cancel";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay( HttpSession session,
							  @RequestParam("paymentId") String paymentId,
							  @RequestParam("PayerID") String payerId){

		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				HoaDonDto hoaDonDto = (HoaDonDto) session.getAttribute("hoadondto");
				 Cart cart = cartManager.getCart(session);
				List<ProductEntity>products = new ArrayList<>();
				for (int i = 0; i < cart.getItems().size(); i++) {
					products.add(cart.getItems().get(i).getProduct());
				}

			     HoaDon hoadon = new HoaDon();
				 long millis=System.currentTimeMillis();
				 java.sql.Date date=new java.sql.Date(millis);
				 hoadon.setName(hoaDonDto.getName());
				 hoadon.setEmail(hoaDonDto.getEmail());
				 hoadon.setSdt(hoaDonDto.getSdt());
				 hoadon.setDiachi(hoaDonDto.getDiachi());
				 hoadon.setNgayhoadon(date);
				 hoadon.setTrangthai("da thanh thoan");
				 hoadon.setProducts(products);
				 hoadon.setSoluong(cart.getItems().size());
				 hoadonservice.save(hoadon);
				 cart.clear();
			 	 return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
	
}