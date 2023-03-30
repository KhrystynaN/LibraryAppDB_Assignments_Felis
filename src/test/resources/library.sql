select count(id) from users;

select distinct count(id) from users;



select * FROM books
where author='Noor';




select * FROM books
where isbn ='The Scrum Field Guide';

select * FROM books
where name='Head First Java  ';


select id,name,author from books
where name = 'Head First Java';


select id,name,author from books
where name = 'The Scrum Field Guide' and author='Mitch Lacey '
order by id desc;
