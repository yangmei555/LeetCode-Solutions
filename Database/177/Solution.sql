CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    # M has to be a constant to be used in the 'limit' clause
    declare M int;
    set M = N-1; 
  RETURN (
      # Write your MySQL query statement below.
       select distinct salary from employee order by salary desc limit M , 1
  );
END


CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      select case when count(*) < N then null else min(salary)
                end 
      from 
       (select distinct salary from employee order by salary desc limit 0, N) as temp
  );
END
