# Write your MySQL query statement below
select a.score, (select count(distinct b.score) from scores b where b.score >= a.score) as rank 
from scores a 
order by rank


# Write your MySQL query statement below
select a.score, (select count(*) from 
					(select distinct score from scores) as temp 
				where temp.score >= a.score) as rank 
from scores a 
order by rank
