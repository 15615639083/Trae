package com.zhifeizi.service.impl;

import com.zhifeizi.entity.Resource;
import com.zhifeizi.mapper.ResourceMapper;
import com.zhifeizi.service.ResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> listResources() {
        return resourceMapper.selectList(null);
    }

    @Override
    public Resource getResourceById(Long id) {
        return resourceMapper.selectById(id);
    }

    @Override
    public boolean saveResource(Resource resource) {
        return resourceMapper.insert(resource) > 0;
    }

    @Override
    public boolean updateResource(Resource resource) {
        return resourceMapper.updateById(resource) > 0;
    }

    @Override
    public boolean deleteResource(Long id) {
        return resourceMapper.deleteById(id) > 0;
    }

    @Override
    public boolean incrementDownloadCount(Long id) {
        Resource resource = resourceMapper.selectById(id);
        if (resource != null) {
            int currentCount = resource.getDownloadCount() != null ? resource.getDownloadCount() : 0;
            resource.setDownloadCount(currentCount + 1);
            return resourceMapper.updateById(resource) > 0;
        }
        return false;
    }

    @Override
    public List<Resource> getResourcesByCategory(String category) {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.like("description", category);
        return resourceMapper.selectList(wrapper);
    }

    @Override
    public List<Resource> searchResources(String keyword) {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.like("title", keyword).or().like("description", keyword);
        return resourceMapper.selectList(wrapper);
    }
}
