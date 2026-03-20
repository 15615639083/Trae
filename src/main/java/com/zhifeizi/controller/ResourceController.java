package com.zhifeizi.controller;

import com.zhifeizi.entity.Resource;
import com.zhifeizi.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public Map<String, Object> listResources() {
        List<Resource> resources = resourceService.listResources();
        return Map.of("success", true, "resources", resources);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getResourceById(@PathVariable Long id) {
        Resource resource = resourceService.getResourceById(id);
        if (resource == null) {
            return Map.of("success", false, "message", "资源不存在");
        }
        return Map.of("success", true, "resource", resource);
    }

    @PostMapping
    public Map<String, Object> saveResource(@RequestBody Resource resource) {
        boolean result = resourceService.saveResource(resource);
        if (result) {
            return Map.of("success", true, "message", "资源保存成功", "resource", resource);
        } else {
            return Map.of("success", false, "message", "资源保存失败");
        }
    }

    @PutMapping
    public Map<String, Object> updateResource(@RequestBody Resource resource) {
        boolean result = resourceService.updateResource(resource);
        if (result) {
            return Map.of("success", true, "message", "资源更新成功", "resource", resource);
        } else {
            return Map.of("success", false, "message", "资源更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteResource(@PathVariable Long id) {
        boolean result = resourceService.deleteResource(id);
        if (result) {
            return Map.of("success", true, "message", "资源删除成功");
        } else {
            return Map.of("success", false, "message", "资源删除失败");
        }
    }

    // 资源下载
    @GetMapping("/download/{id}")
    public Map<String, Object> downloadResource(@PathVariable Long id) {
        Resource resource = resourceService.getResourceById(id);
        if (resource == null) {
            return Map.of("success", false, "message", "资源不存在");
        }
        
        // 增加下载次数
        resourceService.incrementDownloadCount(id);
        
        // 返回下载链接
        return Map.of("success", true, "message", "下载成功", "fileUrl", resource.getFileUrl());
    }

    // 按分类获取资源
    @GetMapping("/category/{category}")
    public Map<String, Object> getResourcesByCategory(@PathVariable String category) {
        List<Resource> resources = resourceService.getResourcesByCategory(category);
        return Map.of("success", true, "resources", resources);
    }

    // 搜索资源
    @GetMapping("/search")
    public Map<String, Object> searchResources(@RequestParam String keyword) {
        List<Resource> resources = resourceService.searchResources(keyword);
        return Map.of("success", true, "resources", resources);
    }
}
