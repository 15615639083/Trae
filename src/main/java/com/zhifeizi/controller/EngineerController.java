package com.zhifeizi.controller;

import com.zhifeizi.entity.EngineerProfile;
import com.zhifeizi.entity.User;
import com.zhifeizi.service.EngineerProfileService;
import com.zhifeizi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

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
        User user = userService.getUserById(profile.getUserId());
        if (user == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "用户不存在");
            return map;
        }

        // 检查是否已经提交过申请
        EngineerProfile existingProfile = engineerProfileService.getProfileByUserId(profile.getUserId());
        if (existingProfile != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "已经提交过申请");
            return map;
        }

        // 设置初始状态
        profile.setVerificationStatus("pending");
        profile.setStatus("offline");

        // 创建工程师档案
        EngineerProfile createdProfile = engineerProfileService.createProfile(profile);
        
        // 更新用户角色为工程师
        user.setRole("engineer");
        userService.updateUser(user);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "申请提交成功，等待审核");
        map.put("profile", createdProfile);
        return map;
    }

    @PutMapping("/update")
    public Map<String, Object> updateProfile(@RequestBody EngineerProfile profile) {
        EngineerProfile existingProfile = engineerProfileService.getProfileById(profile.getId());
        if (existingProfile == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "工程师档案不存在");
            return map;
        }

        // 只有待审核状态可以修改资料
        if (!"pending".equals(existingProfile.getVerificationStatus())) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "只有待审核状态可以修改资料");
            return map;
        }

        EngineerProfile updatedProfile = engineerProfileService.updateProfile(profile);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "资料更新成功");
        map.put("profile", updatedProfile);
        return map;
    }

    @PutMapping("/status")
    public Map<String, Object> updateStatus(@RequestBody Map<String, Object> params) {
        Long userId = Long.valueOf(params.get("userId").toString());
        String status = params.get("status").toString();

        EngineerProfile profile = engineerProfileService.getProfileByUserId(userId);
        if (profile == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "工程师档案不存在");
            return map;
        }

        // 只有已认证的工程师可以修改状态
        if (!"approved".equals(profile.getVerificationStatus())) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "只有已认证的工程师可以修改状态");
            return map;
        }

        profile.setStatus(status);
        EngineerProfile updatedProfile = engineerProfileService.updateProfile(profile);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("message", "状态更新成功");
        map.put("profile", updatedProfile);
        return map;
    }

    @GetMapping("/profile/{userId}")
    public Map<String, Object> getProfile(@PathVariable Long userId) {
        EngineerProfile profile = engineerProfileService.getProfileByUserId(userId);
        if (profile == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("success", false);
            map.put("message", "工程师档案不存在");
            return map;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("profile", profile);
        return map;
    }
}
