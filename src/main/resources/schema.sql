-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100),
  `password` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(20),
  `role` VARCHAR(20) NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `register_time` DATETIME,
  `update_time` DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建工程师档案表
CREATE TABLE IF NOT EXISTS `engineer_profile` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `real_name` VARCHAR(50),
  `id_card` VARCHAR(20),
  `skills` TEXT,
  `verification_status` VARCHAR(20),
  `rating` DOUBLE,
  `balance` DOUBLE,
  `service_price` DOUBLE,
  `status` VARCHAR(20),
  `created_at` DATETIME,
  `updated_at` DATETIME,
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建订单表
CREATE TABLE IF NOT EXISTS `order` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `engineer_id` BIGINT,
  `admin_id` BIGINT,
  `title` VARCHAR(100) NOT NULL,
  `description` TEXT,
  `price` DOUBLE NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `create_time` DATETIME,
  `update_time` DATETIME,
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`engineer_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`admin_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建资源表
CREATE TABLE IF NOT EXISTS `resource` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `description` TEXT,
  `author` VARCHAR(50),
  `file_url` VARCHAR(255),
  `download_count` INT DEFAULT 0,
  `create_time` DATETIME,
  `update_time` DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建消息表
CREATE TABLE IF NOT EXISTS `message` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `sender_id` BIGINT NOT NULL,
  `content` TEXT NOT NULL,
  `type` VARCHAR(20),
  `timestamp` DATETIME,
  FOREIGN KEY (`order_id`) REFERENCES `order`(`id`),
  FOREIGN KEY (`sender_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建交易表
CREATE TABLE IF NOT EXISTS `transaction` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL,
  `amount` DOUBLE NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `created_at` DATETIME,
  `updated_at` DATETIME,
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`order_id`) REFERENCES `order`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;