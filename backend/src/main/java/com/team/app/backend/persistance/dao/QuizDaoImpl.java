package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.dao.QuizDao;
import com.team.app.backend.persistance.dao.mappers.QuizRowMapper;
import com.team.app.backend.persistance.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;


@Repository
public class QuizDaoImpl implements QuizDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QuizRowMapper quizRowMapper;

    public QuizDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Quiz get(Long id) {
        return jdbcTemplate.queryForObject(
                "select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id where Q.id = ?",
                new Object[]{id},
                quizRowMapper
        );
    }



    @Override
    public List<Quiz> getByUserId(Long id) {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id where user_id = ? "
                ,new Object[] { id },
                quizRowMapper);
    }

    @Override
    public List<Quiz> getApproved() {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id where status_id = 2 "
                ,quizRowMapper);
    }


    @Override
    public List<Quiz> getApprovedForUser(Long user_id) {
        return jdbcTemplate.query(
                "SELECT Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id ,Q.id IN (SELECT UQF.quiz_id FROM user_quiz_fav UQF WHERE UQF.user_id = ?) AS favorite FROM quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id WHERE status_id = 2"
                ,new Object[] { user_id }
                ,quizRowMapper);
    }

    @Override
    public List<Quiz> getFavoriteQuizes(Long user_id) {
        return jdbcTemplate.query(
                "SELECT Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id , TRUE as favorite FROM quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id WHERE status_id = 2 AND Q.id IN (SELECT UQF.quiz_id FROM user_quiz_fav UQF WHERE UQF.user_id = ?)"
                ,new Object[] { user_id }
                ,quizRowMapper);
    }

    @Override
    public List<Quiz> getSuggestion(Long user_id) {
        System.out.println(user_id);
        String sql="SELECT QQ.id,QQ.title,QQ.date,QQ.description,QQ.image,QQ.status_id, 'approved' as status_name , QQ.user_id,FALSE AS favorite, COALESCE(QQ1.fav_coef,0)+COALESCE(QQ1.played_coef,0)+FQK.num_friends_played+FQK.num_friends_favorite AS coef\n" +
                "FROM (quiz Q LEFT OUTER JOIN quiz_to_categ QTC ON Q.id = QTC.quiz_id) QQ\n" +
                "  LEFT OUTER JOIN\n" +
                "  (SELECT QTC1.cat_id AS sel_cat_id, COUNT(FQ.fav_quiz_id)*0.4 as fav_coef ,COUNT(SQ.sel_quiz_id)*0.1 as played_coef\n" +
                "    FROM quiz_to_categ QTC1\n" +
                "      LEFT OUTER JOIN \n" +
                "        (SELECT DISTINCT(S.quiz_id) AS sel_quiz_id\n" +
                "        FROM user_to_ses US INNER JOIN session S ON US.ses_id = S.id\n" +
                "        WHERE user_id = ? AND S.status_id = 2) SQ ON SQ.sel_quiz_id = QTC1.quiz_id\n" +
                "      LEFT OUTER JOIN \n" +
                "        (SELECT F.quiz_id AS fav_quiz_id\n" +
                "        FROM user_quiz_fav F\n" +
                "        WHERE F.user_id = ?) FQ ON FQ.fav_quiz_id = QTC1.quiz_id\n" +
                "    GROUP BY QTC1.cat_id) QQ1 ON QQ.cat_id=QQ1.sel_cat_id\n" +
                "  LEFT OUTER JOIN \n" +
                "  (SELECT Q.id AS friend_quiz_id, COALESCE(FPQ.num_friends_played*0.2, 0 ) as num_friends_played,COALESCE(FFQ.num_fav_friend*0.3, 0 ) as num_friends_favorite\n" +
                "  FROM quiz Q LEFT JOIN \n" +
                "          (SELECT DISTINCT(S.quiz_id) AS friend_played_quiz_id ,COUNT(user_id) AS num_friends_played\n" +
                "          FROM user_to_ses US LEFT JOIN session S ON US.ses_id = S.id\n" +
                "          WHERE user_id IN (\n" +
                "                  SELECT id\n" +
                "                    FROM users\n" +
                "                    WHERE id IN (SELECT user_id_from\n" +
                "                          FROM friend_to\n" +
                "                        WHERE user_id_to = ?)\n" +
                "                    OR id IN (SELECT user_id_to\n" +
                "                        FROM friend_to\n" +
                "                        WHERE user_id_from = ?)\n" +
                "            AND S.status_id = 2)  \n" +
                "          GROUP BY S.quiz_id) FPQ ON Q.id=FPQ.friend_played_quiz_id\n" +
                "        LEFT JOIN (SELECT quiz_id AS friends_fav_quiz, COUNT(user_id) AS num_fav_friend\n" +
                "              FROM user_quiz_fav\n" +
                "              WHERE user_id IN (\n" +
                "                      SELECT id\n" +
                "                        FROM users\n" +
                "                        WHERE id IN (SELECT user_id_from\n" +
                "                              FROM friend_to\n" +
                "                            WHERE user_id_to = ?)\n" +
                "                        OR id IN (SELECT user_id_to\n" +
                "                            FROM friend_to\n" +
                "                            WHERE user_id_from = ?)\n" +
                "          )\n" +
                "         GROUP BY quiz_id) FFQ ON Q.id=friends_fav_quiz\n" +
                "   ) FQK ON FQK.friend_quiz_id= QQ.id\n" +
                "WHERE QQ.id NOT IN(\n" +
                "  \t\t\t\tSELECT DISTINCT(S.quiz_id) AS sel_quiz_id\n" +
                "  \t\t\t\tFROM user_to_ses US INNER JOIN session S ON US.ses_id = S.id\n" +
                "\t  \t\t\tWHERE user_id = ? AND S.status_id = 2)\n" +
                "\t\t\t\tAND status_id =2\n" +
                "ORDER BY coef DESC";
        return jdbcTemplate.query(sql
                ,new Object[] { user_id,user_id,user_id,user_id,user_id,user_id,user_id },
                quizRowMapper);
    }

    // to do
    @Override
    public List<Quiz> getCategoryQuizes(String category) {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id INNER JOIN quiz_to_categ QTC ON Q.id = QTC.quiz_id INNER JOIN quiz_category QC ON QTC.cat_id = QC.id where QC.name= ? "
                ,new Object[] { category },
                quizRowMapper);
    }

    @Override
    public List<Quiz> searchQuizes(String[] categories, String searchstring, int dateOption, String user) {
		String search="%"+searchstring+"%";
		String query = "select distinct Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id, Q.user_id, QS.name as status_name from quiz Q INNER JOIN quiz_to_categ QTC ON Q.id = QTC.quiz_id INNER JOIN quiz_category QC ON QC.id = QTC.cat_id INNER JOIN quiz_status QS ON QS.id = Q.status_id INNER JOIN users U ON Q.user_id = U.id where (LOWER(Q.title) LIKE LOWER(?) or LOWER(Q.description) LIKE LOWER(?))";
        if (categories.length != 0) {
			String cat = "";
			for (int i = 0; i < categories.length - 1; i++) {
				cat += "'" + categories[i] + "', ";
			}
			cat += "'" + categories[categories.length - 1] + "'";
			query += " and QC.name IN (" + cat + ")"; 
		}
		switch (dateOption) {
			case 1:
				query += " and Q.date = CURRENT_DATE";
				break;
			case 2:
				query += " and Q.date >= (CURRENT_DATE - INTERVAL '7' DAY)";
				break;
			case 3:
				query += " and Q.date >= (CURRENT_DATE - INTERVAL '1' MONTH)";
				break;
			default:
				break;
		}
		if (user != "") {
			query += " and U.username LIKE '" + user + "'";
		}
        //System.out.println(cat+" "+search);
        return jdbcTemplate.query(query
                ,new Object[] {search, search},
                quizRowMapper);
    }
	
	@Override
    public List<Quiz> searchQuizes(String searchstring) {
        String search="%"+searchstring+"%";
        System.out.println(search);
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id, Q.user_id from quiz Q where Q.title LIKE ? "
                ,new Object[] {search},
                quizRowMapper);
    }

    @Override
    public List<Quiz> getAll() {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id"
                ,quizRowMapper);
    }



    @Override
    public Long save(Quiz quiz) {
        String sql="INSERT INTO quiz( title, date, description, image, status_id, user_id) VALUES (?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
                    ps.setString(1, quiz.getTitle());
                    ps.setDate(2,  quiz.getDate());
                    ps.setString(3, quiz.getDescription());
                    ps.setBytes(4, quiz.getImage());
                    ps.setLong(5,quiz.getStatus().getId());
                    System.out.println( quiz.getUser_id());
                    ps.setLong(6, quiz.getUser_id());

                    return ps;
                },
                keyHolder);
        return  keyHolder.getKey().longValue();
//        jdbcTemplate.update(
//                "INSERT INTO quiz( title, date, description, image, status_id, user_id) VALUES (?, ?, ?, ?, ?, ?);",
//                quiz.getTitle(),
//                quiz.getDate(),
//                quiz.getDescription(),
//                quiz.getImage(),
//                quiz.getStatus().getId(),
//                quiz.getUser()
//            );
    }

    @Override
    public void update(Quiz quiz) {
        jdbcTemplate.update(
                "UPDATE quiz set title = ?, date = ?, desription = ?, image = ?, status_id = ?, user_id = ?  where id = ?",
                quiz.getTitle(),
                quiz.getDate(),
                quiz.getDescription(),
                quiz.getImage(),
                quiz.getStatus().getId(),
                quiz.getUser_id(),
                quiz.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE from quiz_to_categ where quiz_id = ?", id);
        jdbcTemplate.update("DELETE from quiz where id = ?", id);
    }
    @Override
    public void approve(Long id) {
        jdbcTemplate.update(
                "UPDATE quiz set status_id = 2  where id = ?", id);
    }

    @Override
    public List<Quiz> getCreated() {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id where status_id = 1"
                ,quizRowMapper);
    }


}
