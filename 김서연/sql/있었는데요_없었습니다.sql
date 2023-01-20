SELECT OUTS.ANIMAL_ID
     , OUTS.NAME
FROM ANIMAL_INS INS
   , ANIMAL_OUTS OUTS
WHERE INS.ANIMAL_ID = OUTS.ANIMAL_ID
  AND INS.DATETIME > OUTS.DATETIME
ORDER BY INS.DATETIME;