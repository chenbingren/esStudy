本工程是要在于mybatisplus 多表分页

多表其实就是重写mysql

 @Select("SELECT t_question.*,t_student.`name` FROM t_question,t_student WHERE t_question.student_id=t_student.id")
 List<QuestionStudentVO> getQuestionStudent(Pagination page);