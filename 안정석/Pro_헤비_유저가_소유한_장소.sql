-- 집계함수는 WHERE절에 쓸 수 없지만 HAVING절에 쓸 수 있다. 또 서브쿼리로 나오는 ROW가 여러개면 IN과 같은 함수를 사용하여 여러개의 값 체크를 해줘야한다.
SELECT id, name, host_id
FROM places
WHERE host_id IN (SELECT host_id FROM places GROUP BY host_id HAVING COUNT(host_id) > 1)
ORDER BY id