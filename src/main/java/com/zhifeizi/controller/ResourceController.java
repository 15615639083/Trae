package com.zhifeizi.controller;

import com.zhifeizi.entity.Resource;
import com.zhifeizi.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public List<Resource> listResources() {
        return resourceService.listResources();
    }

    @GetMapping("/{id}")
    public Resource getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id);
    }

    @PostMapping
    public boolean saveResource(@RequestBody Resource resource) {
        return resourceService.saveResource(resource);
    }

    @PutMapping
    public boolean updateResource(@RequestBody Resource resource) {
        return resourceService.updateResource(resource);
    }

    @DeleteMapping("/{id}")
    public boolean deleteResource(@PathVariable Long id) {
        return resourceService.deleteResource(id);
    }
}