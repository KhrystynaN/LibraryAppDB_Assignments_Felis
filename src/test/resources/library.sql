select count(id) from library2.users;

select distinct count(id) from users;



select * FROM books
where author='Noor';




select * FROM books
where isbn ='The Scrum Field Guide';

select * FROM books
where name='Head First Java';


select id,name,author,isbn,year from books
where name = 'Head First Java';


select id,name,author,isbn,year from books
where name = 'The Scrum Field Guide';



select count(*)from book_borrow where is_returned=0;


select distinct count(id) from users;

select * from book_categories;


select * from books
where name='Agile Testing';

select isbn, b.name as book_name, author, bc.name as category, year, u.full_name as user_name
from books b join book_categories bc on bc.id = b.book_category_id
join book_borrow bb on b.id = bb.book_id
join users u on u.id = bb.user_id
where b.name='Agile Testing';

select * from book_borrow;

select * from users;


