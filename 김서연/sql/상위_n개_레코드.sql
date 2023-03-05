/*
    프로그래머스 Level1 SQL - SELECT
    상위 n개 레코드
*/
SELECT NAME
FROM
    (
        SELECT NAME
        FROM ANIMAL_INS
        ORDER BY DATETIME
    )
WHERE ROWNUM = 1