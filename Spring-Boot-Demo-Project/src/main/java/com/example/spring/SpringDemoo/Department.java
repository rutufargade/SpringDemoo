package com.example.spring.SpringDemoo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

//Class
@DynamoDbBean
public class Department {

 
@Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long departmentId;
 private String departmentName;
 private String departmentAddress;
 private String departmentCode;
 
  
 //public Department() {
	//	super();
		// TODO Auto-generated constructor stub
//	}
 @DynamoDbPartitionKey
 @DynamoDbAttribute("id")
public Long getDepartmentId() {
	return departmentId;
}
public void setDepartmentId(Long departmentId) {
	this.departmentId = departmentId;
}
@DynamoDbAttribute("departmentName")
public String getDepartmentName() {
	return departmentName;
}
public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}
@DynamoDbAttribute("departmentAddress")
public String getDepartmentAddress() {
	return departmentAddress;
}
public void setDepartmentAddress(String departmentAddress) {
	this.departmentAddress = departmentAddress;
}
@DynamoDbAttribute("departmentCode")
public String getDepartmentCode() {
	return departmentCode;
}
public void setDepartmentCode(String departmentCode) {
	this.departmentCode = departmentCode;
}
@Override
public String toString() {
	return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", departmentAddress="
			+ departmentAddress + ", departmentCode=" + departmentCode + "]";
}
public static String tableName() {
	return "Department";
}
}
