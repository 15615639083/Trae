package com.zhifeizi.controller;

import com.zhifeizi.entity.EngineerProfile;
import com.zhifeizi.entity.User;
import com.zhifeizi.service.EngineerProfileService;
import com.zhifeizi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/engineer")
public class EngineerController {

    @Autowired
    private EngineerProfileService engineerProfileService;

    @Autowired
    private UserService userService;

    @PostMapping("/apply")
    public Map<String, Object> applyForEngineer(@RequestBody EngineerProfile profile) {
        // 验证用户是否存在
        User user = userService.getById(profile.getUserId());
        if (user == null) {
            return Map.of("success", false, "message", "用户不存在");
        }

        // 检查是否已经提交过申请
        EngineerProfile existingProfile = engineerProfileService.getProfileByUserId(profile.getUserId());
        if (existingProfile != null) {
            return Map.of("success", false, "message", "已经提交过申请");
        }

        // 设置初始状态
        profile.setVerificationStatus("pending");
        profile.setStatus("offline");

        // 创建工程师档案
        EngineerProfile createdProfile = engineerProfileService.createProfile(profile);
        
        // 更新用户角色为工程师
        user.setRole("engineer");
        userService.update(user);

        return Map.of("success", true, "message", "申请提交成功，等待审核", "profile", createdProfile);
    }

    @PutMapping("/update")
    public Map<String, Object> updateProfile(@RequestBody EngineerProfile profile) {
        EngineerProfile existingProfile = engineerProfileService.getProfileById(profile.getId());
        if (existingProfile == null) {
            return Map.of("success", false, "message", "工程师档案不存在");
        }

        // 只有待审核状态可以修改资料
        if (!"pending".equals(existingProfile.getVerificationStatus())) {
            return Map.of("success", false, "message", "只有待审核状态可以修改资料");
        }

        EngineerProfile updatedProfile = engineerProfileService.updateProfile(profile);
        return Map.of("success", true, "message", "资料更新成功", "profile", updatedProfile);
    }

    @PutMapping("/status")
    public Map<String, Object> updateStatus(@RequestBody Map<String, Object> params) {
        Long userId = Long.valueOf(params.get("userId").toString());
        String status = params.get("status").toString();

        EngineerProfile profile = engineerProfileService.getProfileByUserId(userId);
        if (profile == null) {
            return Map.of("success", false, "message", "工程师档案不存在");
        }

        // 只有已认证的工程师可以修改状态
        if (!"approved".equals(profile.getVerificationStatus())) {
            return Map.of("success", false, "message", "只有已认证的工程师可以修改状态");
        }

        profile.setStatus(status);
        EngineerProfile updatedProfile = engineerProfileService.updateProfile(profile);
        return Map.of("success", true, "message", "状态更新成功", "profile", updatedProfile);
    }

    @GetMapping("/profile/{userId}")
    public Map<String, Object> getProfile(@PathVariable Long userId) {
        EngineerProfile profile = engineerProfileService.getProfileByUserId(userId);
        if (profile == null) {
            return Map.of("success", false, "message", "工程师档案不存在");
        }
        return Map.of("success", true, "profile", profile);
    }
}
