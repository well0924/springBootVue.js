insert into member(user_age,user_email,user_name,password,created_time,updated_time)values(10,'tester1@naver.com','tester','qwer4149!',now(),now());
insert into member(user_age,user_email,user_name,password,created_time,updated_time)values('20','tester2@naver.com','tester','well4149!!',now(),now());
update member set created_time = now(),updated_time = now() where id = 2;
insert into post(author,contents,title,created_time,updated_time)values('test title','test contents','test author',now(),now());
select * from post;
select * from member;