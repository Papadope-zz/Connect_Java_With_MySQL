create database cb9_part2;

use cb9_part2;

create table students (
student_id int not null auto_increment,
first_name varchar(50) not null,
last_name varchar(50) not null,
date_of_birth date not null,
tuition_fees int not null,
primary key (student_id)
);

create table courses (
course_id int not null auto_increment,
title varchar(100) not null,
stream varchar(20) not null,
type varchar(30) not null,
start_date date not null,
end_date date not null,
primary key (course_id)
);

create table trainers (
trainer_id int not null auto_increment,
first_name varchar(50) not null,
last_name varchar(50) not null,
subject varchar(50) not null,
primary key (trainer_id)
);

create table assignments (
course_id int not null,
assignment_id int not null auto_increment,
title varchar(100) not null,
description varchar(100) not null,
sub_date_time date not null,
oral_mark int not null,
total_mark int not null,
foreign key (course_id) references courses (course_id),
primary key (assignment_id)
);

create table trainers_per_course (
course_id int,
trainer_id int,
foreign key (trainer_id) references trainers (trainer_id),
foreign key (course_id) references courses (course_id),
primary key(trainer_id, course_id)
);

create table students_per_course (
course_id int,
student_id int,
foreign key (student_id) references students (student_id),
foreign key (course_id) references courses (course_id),
primary key(student_id, course_id)
);

create table assignment_per_student_per_course (
assignment_id int,
student_id int,
sub_date date,
oral_mark int,
total_mark int,
foreign key (assignment_id) references assignments (assignment_id),
foreign key (student_id) references students (student_id),
primary key (assignment_id, student_id)
);