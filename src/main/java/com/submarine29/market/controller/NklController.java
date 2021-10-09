package com.submarine29.market.controller;

import com.submarine29.market.domain.Nkl;
import com.submarine29.market.repo.NklRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nkls")
public class NklController {
    private final NklRepo nklRepo;

    @Autowired
    public NklController (NklRepo nklRepo){
        this.nklRepo = nklRepo;
    }

    @GetMapping
    public List<Nkl> list(){
        return nklRepo.findAll();
    }

    @GetMapping("{id}")
    public Nkl getOne(@PathVariable("id") Nkl nkl){
        return nkl;
    }

    @PostMapping
    public Nkl create(@RequestBody Nkl nkl){
        return nklRepo.save(nkl);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Nkl nkl){
        nklRepo.delete(nkl);
    }

    @PutMapping("{id}")
    public Nkl update(@PathVariable("id") Nkl nklFromDB, @RequestBody Nkl nklNew){
        BeanUtils.copyProperties(nklNew,nklFromDB,"id");
        return nklRepo.save(nklFromDB);
    }
}
