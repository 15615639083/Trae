package com.zhifeizi.controller;

import com.zhifeizi.entity.Resource;
import com.zhifeizi.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public Map<String, Object> listResources() {
        List<Resource> resources = resourceService.listResources();
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("resources", resources);
        return map;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getResourceById(@PathVariable Long id) {
        Resource resource = resourceService.getResourceById(id);
        if (resource == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "资源不存在");
            return map;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("resource", resource);
        return map;
    }

    @PostMapping
    public Map<String, Object> saveResource(@RequestBody Resource resource) {
        boolean result = resourceService.saveResource(resource);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "资源保存成功");
            map.put("resource", resource);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "资源保存失败");
            return map;
        }
    }

    @PutMapping
    public Map<String, Object> updateResource(@RequestBody Resource resource) {
        boolean result = resourceService.updateResource(resource);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "资源更新成功");
            map.put("resource", resource);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "资源更新失败");
            return map;
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteResource(@PathVariable Long id) {
        boolean result = resourceService.deleteResource(id);
        if (result) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", true);
            map.put("message", "资源删除成功");
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "资源删除失败");
            return map;
        }
    }

    // 资源下载
    @GetMapping("/download/{id}")
    public Map<String, Object> downloadResource(@PathVariable Long id) {
        Resource resource = resourceService.getResourceById(id);
        if (resource == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "资源不存在");
            return map;
        }
        
        // 增加下载次数
        resourceService.incrementDownloadCount(id);
        
        // 返回下载链接
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "下载成功");
        map.put("fileUrl", resource.getFileUrl());
        return map;
    }

    // 按分类获取资源
    @GetMapping("/category/{category}")
    public Map<String, Object> getResourcesByCategory(@PathVariable String category) {
        List<Resource> resources = resourceService.getResourcesByCategory(category);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("resources", resources);
        return map;
    }

    // 搜索资源
    @GetMapping("/search")
    public Map<String, Object> searchResources(@RequestParam String keyword) {
        List<Resource> resources = resourceService.searchResources(keyword);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("resources", resources);
        return map;
    }
}
