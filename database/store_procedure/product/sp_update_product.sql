
DELIMITER $$
CREATE PROCEDURE sp_update_product(
	IN id_in INT,
	IN title_in NVARCHAR(255),
    IN price_in DECIMAL(12,0),
    IN url_image_in NVARCHAR(255),
    IN quantity_in INT,
    IN id_category_in INT,
	OUT success TINYINT(1),
    OUT message NVARCHAR(255)
)
BEGIN
	SET success = FALSE;
    IF(price_in < 0)
		THEN
			SET message = "Giá Tiền Nhập Vào Không Hợp Lệ";
	ELSE
		IF(price_in > 999999999999)
			THEN
				SET  message = "Giá Tiền Nhập Vào Không Hợp Lệ";
	ELSE
		IF(quantity_in < 0)
			THEN
				SET message = "Số Lượng Nhập Vào Không Hợp Lệ";
	ELSE
		IF(NOT EXISTS (SELECT * FROM product WHERE id = id_in))
			THEN
				SET message = "Sản Phẩm Không Tồn Tại";
	ELSE
		IF(NOT EXISTS (SELECT * FROM category WHERE id = id_category_in))
			THEN
				SET message = "Danh Mục Không Tồn Tại";
	ELSE
		UPDATE `db_case_md3`.`product` 
        SET `title` = title_in, 
			`url_image` = url_image_in, 
			`price` = price_in, 
			`quantity` = quantity_in,
			`updated_at` = NOW(), 
			`id_category` = id_category_in
        WHERE (`id` = id_in);
        SET message = "Sửa Thông Tin Sản Phẩm Thành Công";
        SET success = TRUE;
									END IF;
							END IF;
					END IF;
			END IF;
	END IF;
END
$$