select count(id) from users;

select distinct count(id) from users;

select bc.name, count(*)
from book_borrow bb join books b on bb.book_id = b.id
join book_categories bc on bc.id = b.book_category_id
group by bc.name
order by count(*) desc;
