package com.example.demo.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserJDBCRepository {

	private final JdbcTemplate jdbcTemplate;


	public boolean update(User entity) {

		List<Object> o=new ArrayList<>(0);

		StringBuilder builder = new StringBuilder(0);
		builder.append("UPDATE user SET ");
		if (Objects.nonNull(entity.getDateOfBirth())) {
			builder.append("dateOfBirth = ?,");
			o.add(entity.getDateOfBirth());
		}
		if (Objects.nonNull(entity.getAdress())) {
			builder.append("adress = ?,");
			o.add(entity.getAdress());
		}
		if (Objects.nonNull(entity.getUserProperties())) {
			if (Objects.nonNull(entity.getUserProperties().getName())) {
				builder.append("name = ?,");
				o.add(entity.getUserProperties().getName());
			}
			if (Objects.nonNull(entity.getUserProperties().getSurName())) {
				builder.append("surName = ?,");
				o.add(entity.getUserProperties().getSurName());
			}
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(" WHERE id = ?");
		o.add(entity.getId());

		return jdbcTemplate.update(builder.toString(), o.toArray())==1?true:false;
	}
}
