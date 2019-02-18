package kr.ayukawa.hibernate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="user_table")
public class User {
	@Id
	@Column(name="user_id")
	@Getter @Setter
	private int userId;

	@Column(name="user_name")
	@Getter @Setter
	private String userName;

	@Column(name="created_by")
	@Getter @Setter
	private String createdBy;

	@Column(name="created_date")
	@Getter @Setter
	private Date createdDate;
}
