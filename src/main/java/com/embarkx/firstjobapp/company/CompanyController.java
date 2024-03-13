package com.embarkx.firstjobapp.company;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll(){
        return ResponseEntity.ok(companyService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company!=null){
            return ResponseEntity.ok(company);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean f = companyService.deleteCompanyById(id);
        if(f){
            return ResponseEntity.ok("Deleted successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id,@RequestBody Company company){
        boolean f = companyService.updateCompanyById(id,company);
        if(f){
            return ResponseEntity.ok("Updated successfully!");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
