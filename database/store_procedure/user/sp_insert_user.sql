DELIMITER $$

CREATE PROCEDURE sp_insert_user(
	IN full_name_in NVARCHAR(255),
    IN mobile_in NVARCHAR(10),
    IN email_in NVARCHAR(255),
    IN password_in NVARCHAR(255),
    IN admin_in INT,
    IN address_in NVARCHAR(255),
    OUT success TINYINT(1),
    OUT message NVARCHAR(255)
)
BEGIN
	SET success = FALSE;
    IF(CHAR_LENGTH(mobile_in) != 10 )
		THEN
			SET message = "Số Điện Thoại Không Hợp Lệ ";
	ELSE
		IF(EXISTS(SELECT email FROM _user WHERE email = email_in))
			THEN
				SET message = "Email Bị Trùng";
	ELSE
		IF(admin_in > 1 OR admin_in < 0)
			THEN
				SET message = "Loại Tài Khoản Không Hợp Lệ";
	ELSE
		INSERT INTO `db_case_md3`.`_user` (`full_name`, `mobile`, `email`, `password_user`, `registered_at`, `_admin`, `_status`, `url_image`,`address`) 
        VALUES (full_name_in, mobile_in, email_in, password_in, NOW(), admin_in, 1, '64-646593_thamali-k-i-s-user-default-image-jpg.png',address_in);
		SET message = "Thêm Tài Khoản Thành Công";
        SET success = TRUE;
					END IF;
			END IF;
	END IF;
END
$$