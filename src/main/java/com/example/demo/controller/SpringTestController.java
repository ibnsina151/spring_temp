package com.example.demo.controller;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.SpringTestRepository;
import com.example.demo.repository.model.Customer;
import com.example.demo.repository.model.SpringTest;
import com.example.demo.viewmodel.CustomerUI;
import com.example.demo.viewmodel.SpringTestUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SpringTestController {
    @Autowired
    SpringTestRepository repository;

    @GetMapping("/createSpringTest")
    public String bulkcreate(){
        repository.save(new SpringTest("Munim","IbnSina","male","+80017297258701"));
        repository.saveAll(Arrays.asList(new SpringTest("Kaium","Hossain","male","+880198307544"),
                new SpringTest("Rakibul","Islam","Female","+8801000024512"),
                new SpringTest("Imran","Hossain","Female","+8801524587456"),
                new SpringTest("Fahim","batpar","Female","+8807865468767"),
                new SpringTest("Shetu","Raju","male","+88046786485648")));

        return "Spring Test Data are Created Successfully";
    }

    @GetMapping("/findSpringTestAll")
    public List<SpringTestUI> findAll(){
        List<SpringTest> springTests = repository.findAll();
        List<SpringTestUI> springTestUIS = new ArrayList<>();
        for (SpringTest test: springTests){
            springTestUIS.add(new SpringTestUI(test.getFirstName(),test.getLastName(),test.getGender(),test.getMobile()));
        }

        return springTestUIS;
    }

    @GetMapping("/search/{id}")
    public String search(@PathVariable long id){
        String customer = "";
        customer = repository.findById(id).toString();
        return customer;
    }


    @GetMapping("/search")
    public List<SpringTestUI> getSpringTestGender(@RequestParam String gender){
        List<SpringTest> data = repository.findTop2ByGender(gender);
        List<SpringTestUI> springTestUIS = new ArrayList<>();
        for (SpringTest test: data){
            springTestUIS.add(new SpringTestUI(test.getFirstName(),test.getLastName(),test.getGender(),test.getMobile()));
        }
        return springTestUIS;
    }


}
