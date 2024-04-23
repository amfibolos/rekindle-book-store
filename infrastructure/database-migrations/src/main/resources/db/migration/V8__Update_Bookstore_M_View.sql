Drop trigger IF exists refresh_order_bookstore_products_m_view ON bookstore.products;

CREATE trigger refresh_order_bookstore_products_m_view
    after INSERT OR
          UPDATE OR
          DELETE OR
          TRUNCATE
    ON bookstore.products
    FOR each statement
EXECUTE PROCEDURE bookstore.refresh_order_bookstore_m_view();