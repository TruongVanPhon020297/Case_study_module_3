CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_cart`(
    IN inProductId INT,
    IN inQuantity INT,
    OUT success BOOLEAN,
    OUT message VARCHAR(255)
)
BEGIN
    DECLARE productTitle VARCHAR(255) DEFAULT NULL;
    DECLARE productPrice INT DEFAULT 0;

    DECLARE _rollback BOOL DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET _rollback = 1;

    SET success = false;

    START TRANSACTION;

    IF EXISTS (SELECT 1 FROM products AS p WHERE p.id = inProductId)
    THEN
        IF (inQuantity > 0)
        THEN
            SET productTitle = (SELECT p.title FROM products AS p WHERE p.id = inProductId);
            SET productPrice = (SELECT p.price FROM products AS p WHERE p.id = inProductId);

            IF EXISTS (SELECT 1 FROM carts WHERE product_id = inProductId)
            THEN
                UPDATE carts
                SET
                    quantity = quantity + inQuantity,
                    total_price = quantity * productPrice
                WHERE product_id = inProductId;

                SET message = 'Cập nhật sản phẩm trong giỏ hàng thành công';
            ELSE
                INSERT INTO carts (product_id, title, quantity, price, total_price)
                VALUES (inProductId, productTitle, inQuantity, productPrice, (inQuantity * productPrice));

                SET message = 'Thêm sản phẩm vào giỏ hàng thành công';
            END IF;

            IF _rollback
            THEN
                SET message = 'Thêm sản phẩm vào giỏ hàng không thành công';
                ROLLBACK;
            ELSE
                SET success = true;
                COMMIT;
            END IF;
        ELSE
            SET message = 'Số lượng sản phẩm không hợp lệ';
        END IF;
    ELSE
        SET message = 'Sản phẩm không tồn tại trong hệ thống';
    END IF;


END