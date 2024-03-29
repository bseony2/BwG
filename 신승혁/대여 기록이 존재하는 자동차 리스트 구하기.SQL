SELECT DISTINCT A.CAR_ID
FROM CAR_RENTAL_COMPANY_CAR A
JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY B
    ON A.CAR_ID = B.CAR_ID
WHERE 1=1
    AND CAR_TYPE = '세단'
    AND TO_CHAR(START_DATE, 'YYYYMMDD') BETWEEN '20221001' AND '20221101'
ORDER BY A.CAR_ID DESC
;
