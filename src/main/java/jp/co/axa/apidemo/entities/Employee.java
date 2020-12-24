package jp.co.axa.apidemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLOYEE")
@ApiModel(description = "Model Employee")
public class Employee {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Unique employee number", required = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(notes = "Callsign")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(notes = "Salary in ¥")
	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@ApiModelProperty(notes = "The Great Divider")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Getter
	@Setter
	@Column(name = "EMPLOYEE_NAME")
	private String name;

	@Getter
	@Setter
	@Column(name = "EMPLOYEE_SALARY")
	private Integer salary;

	@Getter
	@Setter
	@Column(name = "DEPARTMENT")
	private String department;

	@Override
	public String toString() {
		// Useful for testing
		return "[" + name + ", " + department + ", ¥" + salary + "]";
	}

}
