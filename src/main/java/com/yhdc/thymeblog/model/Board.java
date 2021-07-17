package com.yhdc.thymeblog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(min = 5, max = 50, message = "Title must be between 5 to 50 characters.")
	private String title;

	@NotEmpty(message = "Contents cannot be blank.")
	@Column(columnDefinition = "text")
	private String content;

	@ColumnDefault("0")
	private int count;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	private List<Comment> comments;

	@CreationTimestamp
	private Timestamp regDate;

	@UpdateTimestamp
	private Timestamp modDate;
}
