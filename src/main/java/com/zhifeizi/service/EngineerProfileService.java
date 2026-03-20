package com.zhifeizi.service;

import com.zhifeizi.entity.EngineerProfile;

public interface EngineerProfileService {
    EngineerProfile createProfile(EngineerProfile engineerProfile);
    EngineerProfile updateProfile(EngineerProfile engineerProfile);
    EngineerProfile getProfileByUserId(Long userId);
    EngineerProfile getProfileById(Long id);
    boolean verifyEngineer(Long profileId, String status);
}