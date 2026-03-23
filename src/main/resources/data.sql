-- 初始化用户数据
INSERT IGNORE INTO `user` (`id`, `username`, `email`, `password`, `phone`, `role`, `status`, `register_time`, `update_time`)
VALUES 
(1, 'admin', 'admin@zhifeizi.com', '123456', '13800138000', 'admin', 'active', NOW(), NOW()),
(2, 'user1', 'user1@zhifeizi.com', '123456', '13800138001', 'user', 'active', NOW(), NOW()),
(3, 'engineer1', 'engineer1@zhifeizi.com', '123456', '13800138002', 'engineer', 'active', NOW(), NOW()),
(4, 'engineer2', 'engineer2@zhifeizi.com', '123456', '13800138003', 'engineer', 'active', NOW(), NOW());

-- 初始化工程师档案数据
INSERT IGNORE INTO `engineer_profile` (`id`, `user_id`, `real_name`, `id_card`, `skills`, `verification_status`, `rating`, `balance`, `service_price`, `status`, `created_at`, `updated_at`)
VALUES 
(1, 3, '张三', '110101199001011234', 'Java, Spring Boot, MySQL', 'verified', 4.8, 1000.0, 100.0, 'active', NOW(), NOW()),
(2, 4, '李四', '110101199001011235', 'Python, Django, MongoDB', 'verified', 4.9, 1500.0, 120.0, 'active', NOW(), NOW());

-- 初始化资源数据
INSERT IGNORE INTO `resource` (`id`, `title`, `description`, `author`, `file_url`, `download_count`, `create_time`, `update_time`)
VALUES 
(1, 'Spring Boot 实战教程', 'Spring Boot 入门到精通', 'admin', 'https://example.com/spring-boot.pdf', 100, NOW(), NOW()),
(2, 'Java 核心技术', 'Java 基础到高级', 'admin', 'https://example.com/java-core.pdf', 200, NOW(), NOW()),
(3, 'MySQL 数据库优化', 'MySQL 性能调优指南', 'admin', 'https://example.com/mysql-optimization.pdf', 150, NOW(), NOW());

-- 初始化订单数据
INSERT IGNORE INTO `order` (`id`, `user_id`, `engineer_id`, `admin_id`, `title`, `description`, `price`, `status`, `create_time`, `update_time`)
VALUES 
(1, 2, 3, 1, 'Spring Boot 项目开发', '开发一个基于 Spring Boot 的后端服务', 500.0, 'completed', NOW(), NOW()),
(2, 2, 4, 1, 'Python 数据分析', '使用 Python 进行数据分析', 300.0, 'in_progress', NOW(), NOW());

-- 初始化消息数据
INSERT IGNORE INTO `message` (`id`, `order_id`, `sender_id`, `content`, `type`, `timestamp`)
VALUES 
(1, 1, 2, '我需要一个 Spring Boot 后端服务', 'text', NOW()),
(2, 1, 3, '好的，我可以帮你开发', 'text', NOW()),
(3, 2, 2, '我需要进行数据分析', 'text', NOW()),
(4, 2, 4, '没问题，我来帮你', 'text', NOW());

-- 初始化交易数据
INSERT IGNORE INTO `transaction` (`id`, `user_id`, `order_id`, `amount`, `type`, `status`, `created_at`, `updated_at`)
VALUES 
(1, 2, 1, 500.0, 'payment', 'completed', NOW(), NOW()),
(2, 3, 1, 450.0, 'receipt', 'completed', NOW(), NOW()),
(3, 2, 2, 300.0, 'payment', 'pending', NOW(), NOW());