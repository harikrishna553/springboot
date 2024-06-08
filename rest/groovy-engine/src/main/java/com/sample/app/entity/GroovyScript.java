package com.sample.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GROOVYSCRIPT")
public class GroovyScript {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "script", columnDefinition = "nvarchar(10000)")
	private String script;

	// Getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public static GroovyScript fromScriptAndName(String script, String name) {
		GroovyScript groovySript = new GroovyScript();
		groovySript.setName(name);
		groovySript.setScript(script);
		return groovySript;
	}
}
