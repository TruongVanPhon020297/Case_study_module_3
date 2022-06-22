DELIMITER $$

CREATE PROCEDURE sp_search_product(
	IN key_value NVARCHAR(255),
)
BEGIN
	SELECT * 
    FROM product 
    WHERE title LIKE key_value OR
		  url_image LIKE key_value OR
          price LIKE key_value OR
          quantity LIKE key_value OR
          created_at LIKE key_value OR
          updated_at LIKE key_value OR
          stop_selling LIKE key_value OR
          id_category LIKE key_value;
END
$$