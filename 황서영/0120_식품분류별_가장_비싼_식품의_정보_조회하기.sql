SELECT CATEGORY
     , PRICE AS MAX_PRICE
     , PRODUCT_NAME
FROM
    (SELECT ROW_NUMBER() OVER (PARTITION BY CATEGORY ORDER BY PRICE DESC) AS RN
            ,CATEGORY, PRICE, PRODUCT_NAME
     FROM FOOD_PRODUCT
     WHERE CATEGORY IN ('과자', '국', '김치', '식용유')
    ) A
WHERE RN = 1
ORDER BY MAX_PRICE DESC