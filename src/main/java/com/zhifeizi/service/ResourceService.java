package com.zhifeizi.service;

import com.zhifeizi.entity.Resource;
import java.util.List;

public interface ResourceService {
    List<Resource> listResources();
    Resource getResourceById(Long id);
    boolean saveResource(Resource resource);
    boolean updateResource(Resource resource);
    boolean deleteResource(Long id);
    boolean incrementDownloadCount(Long id);
    List<Resource> getResourcesByCategory(String category);
    List<Resource> searchResources(String keyword);
}