package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/customer")
@PropertySource("classpath:imageFile.properties")
@PropertySource("classpath:audioFile.properties")

public class CustomerController {
    @Value("${file-image}")
    private String imageFile;
    @Value("${file-audio}")
    private String audioFile;

    @Autowired
    ICustomerService customerService;

    @GetMapping("")
    public ModelAndView showCustomerList() {
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("customers", customerService.findAll());
        return mav;
    }

    @GetMapping("create")
    private ModelAndView showCreateForm() {
        ModelAndView mav = new ModelAndView("create");
        mav.addObject("customerForm", new CustomerForm());
        return mav;
    }

    @PostMapping("create")
    private String saveCustomer(@ModelAttribute CustomerForm customerForm) {
        MultipartFile multipartImgFile = customerForm.getImg();
        MultipartFile multipartAudioFile = customerForm.getAudio();
        String fileImgName = multipartImgFile.getOriginalFilename();
        String fileAudioName = multipartAudioFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartImgFile.getBytes(), new File(imageFile + fileImgName));
            FileCopyUtils.copy(multipartAudioFile.getBytes(), new File(audioFile + fileAudioName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Customer customer = new Customer();
        customer.setName(customerForm.getName());
        customer.setAge(customerForm.getAge());
        customer.setAddress(customerForm.getAddress());
        customer.setImg(fileImgName);
        customer.setAudio(fileAudioName);

        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("{id}/edit")
    private ModelAndView updateForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("customer", customerService.findById(id));
        return mav;
    }

    @PostMapping("edit")
    private String updateCustomer(@ModelAttribute Customer customer, MultipartFile newImg, MultipartFile newAudio, RedirectAttributes redirect) {
        String newImgName = newImg.getOriginalFilename();
        String newAudioName = newAudio.getOriginalFilename();

        try {
            FileCopyUtils.copy(newImg.getBytes(),new File(imageFile + newImgName));
            FileCopyUtils.copy(newAudio.getBytes(), new File(audioFile + newAudioName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (newImgName!=""){
            customer.setImg(newImgName);
        }
        if (newAudioName!=""){
        customer.setAudio(newAudioName);
        }
        customerService.save(customer);
        redirect.addFlashAttribute("message","Edit customer successfully!");
        return "redirect:/customer";
    }

    @GetMapping("{id}/detail")
    ModelAndView showDetail(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("customer",customerService.findById(id));
        return mav;
    }

    @GetMapping("{id}/delete")
    ModelAndView showDeleteForm(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("delete");
        mav.addObject("customer",customerService.findById(id));
        return mav;
    }

    @PostMapping("delete")
    String deleteCustomer(Long id,RedirectAttributes redirect){
        customerService.remote(id);
        redirect.addFlashAttribute("message","Remote customer successfully!");
        return "redirect:/customer";
    }


}
