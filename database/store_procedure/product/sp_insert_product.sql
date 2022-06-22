
DELIMITER $$
CREATE PROCEDURE sp_insert_product(
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
    IF(price_in < 0 OR price_in > 999999999999)
		THEN
			SET message = "Giá Tiền Nhập Vào Không Hợp Lệ";
	ELSE
		IF(quantity_in < 0)
			THEN
				SET message = "Số Lượng Nhập Vào Không Hợp Lệ";
	ELSE
		IF(NOT EXISTS (SELECT * FROM category WHERE id = id_category_in))
			THEN
				SET message = "Danh Mục Không Tồn Tại";
	ELSE
		INSERT INTO `db_case_md3`.`product` (`title`, `url_image`, `price`, `quantity`, `created_at`, `stop_selling`, `id_category`) 
        VALUES (title_in, url_image_in, price_in, quantity_in, NOW(), '0', id_category_in);
        SET message = "Thêm Sản Phẩm Thành Công";
        SET success = TRUE;
					END IF;
			END IF;
	END IF;
END
$$