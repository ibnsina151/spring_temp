package com.example.demo.controller;

import com.example.demo.repository.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.viewmodel.CustomerUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController  {
    @Autowired
    CustomerRepository repository;

    @GetMapping("/bulkcreate")
    public String bulkcreate(){
        repository.save(new Customer("Munim","IbnSina"));
        repository.saveAll(Arrays.asList(new Customer("Kaium","Hossain"),
                new Customer("Rakibul","Islam"),
                new Customer("Imran","Hossain"),
                new Customer("Fahim","batpar"),
                new Customer("Shetu","Raju")));

        return "Customer are Created Successfully";
    }

    @GetMapping("/findAll")
    public List<CustomerUI> findAll(){
        List<Customer> customers = repository.findAll();
        List<CustomerUI> customerUI = new ArrayList<>();
        for (Customer customer: customers){
            customerUI.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUI;
    }

    @RequestMapping("/search/{id}")
    public String search(@PathVariable long id){
        String customer = "";
        customer = repository.findById(id).toString();
        return customer;
    }

    @RequestMapping("/search/firstname/{firstname}")
    public List<CustomerUI> fetchDataByFirstName(@PathVariable String firstname){
        List<Customer> customers = repository.findByFirstName(firstname);
        List<CustomerUI> customerUIS = new ArrayList<>();
        for (Customer customer:customers){
            customerUIS.add(new CustomerUI(customer.getFirstName(),customer.getLastName()));
        }

        return customerUIS;
    }

    @GetMapping("/create/customer")
    @ResponseBody
    public String getCreateRes(@RequestParam(name = "id") String id,@RequestParam String name){
        return "ID: "+id+"My name is: "+name;
    }

    @GetMapping("/create/optional")
    @ResponseBody
    public String getCreateResOptional(@RequestParam(required = false) String id,@RequestParam String name){
        return "ID: "+id+" My name is :"+ name;
    }

}
