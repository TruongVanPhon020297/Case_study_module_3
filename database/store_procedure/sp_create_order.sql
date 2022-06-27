CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_create_order`(
    IN inUserId INT,
    IN inFullName VARCHAR(100),
    IN inEmail VARCHAR(100),
    IN inMobile VARCHAR(100),
    OUT success BOOLEAN,
    OUT message VARCHAR(255)
)
BEGIN
    DECLARE last_id_in_orders INT DEFAULT 0;

    DECLARE _rollback BOOL DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET _rollback = 1;

    SET success = false;

    START TRANSACTION;

    INSERT INTO orders (full_name, email, mobile, order_date, delivery_date, delivery_address, grand_total, created_at, updated_at, user_id)
    VALUES (inFullName, inEmail, inMobile, NOW(), NOW(), '', 0, NOW(), NOW(), inUserId);

    SET last_id_in_orders = LAST_INSERT_ID();

    INSERT INTO order_items (order_id, product_id, price, quantity, total_price, created_at)
    SELECT last_id_in_orders, c.product_id, c.price, c.quantity, c.total_price, NOW() FROM carts AS c;

    UPDATE orders AS o
    SET grand_total = (SELECT SUM(total_price) FROM order_items AS oi WHERE oi.order_id = last_id_in_orders)
    WHERE o.id = last_id_in_orders;

    DELETE FROM carts;

    IF _rollback
    THEN
        SET message = 'Tạo đơn hàng không thành công';
        ROLLBACK;
    ELSE
        SET message = 'Tạo đơn hàng thành công';
        SET success = true;
        COMMIT;
    END IF;

END