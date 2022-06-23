DELIMITER $$

CREATE PROCEDURE sp_update_user(
	IN id_in INT,
	IN full_name_in NVARCHAR(255),
    IN email_in NVARCHAR(255),
    IN mobile_in NVARCHAR(10),
    IN address_in NVARCHAR(255),
    OUT success TINYINT(1),
    OUT message NVARCHAR(255)
)
BEGIN
	SET success = FALSE;
    IF(CHAR_LENGTH(mobile_in) > 10 OR CHAR_LENGTH(mobile_in) < 9)
		THEN
			SET message = "Số Điện Thoại Không Hợp Lệ";
	ELSE
		UPDATE `db_case_md3`.`_user` 
		SET `full_name` = full_name_in, 
		`mobile` = mobile_in, 
		`email` = email_in, 
		`updated_at` = NOW(), 
		`address` = address_in 
		WHERE id = id_in;
		SET success = TRUE;
		SET message = "Cập Nhập Thành Công";
    END IF;
END

$$