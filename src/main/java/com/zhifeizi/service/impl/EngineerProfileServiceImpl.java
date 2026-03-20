package com.zhifeizi.service.impl;

import com.zhifeizi.entity.EngineerProfile;
import com.zhifeizi.mapper.EngineerProfileMapper;
import com.zhifeizi.service.EngineerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineerProfileServiceImpl implements EngineerProfileService {

    @Autowired
    private EngineerProfileMapper engineerProfileMapper;

    @Override
    public EngineerProfile createProfile(EngineerProfile engineerProfile) {
        engineerProfileMapper.insert(engineerProfile);
        return engineerProfile;
    }

    @Override
    public EngineerProfile updateProfile(EngineerProfile engineerProfile) {
        engineerProfileMapper.update(engineerProfile);
        return engineerProfile;
    }

    @Override
    public EngineerProfile getProfileByUserId(Long userId) {
        return engineerProfileMapper.selectByUserId(userId);
    }

    @Override
    public EngineerProfile getProfileById(Long id) {
        return engineerProfileMapper.selectById(id);
    }

    @Override
    public boolean verifyEngineer(Long profileId, String status) {
        EngineerProfile profile = engineerProfileMapper.selectById(profileId);
        if (profile != null) {
            profile.setVerificationStatus(status);
            engineerProfileMapper.update(profile);
            return true;
        }
        return false;
    }
}