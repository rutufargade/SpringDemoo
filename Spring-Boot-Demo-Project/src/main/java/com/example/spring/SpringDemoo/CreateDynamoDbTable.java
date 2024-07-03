package com.example.spring.SpringDemoo;

//import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import lombok.extern.slf4j.Slf4j;

//import ch.qos.logback.classic.Logger;
//import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.internal.waiters.ResponseOrException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableResponse;
import software.amazon.awssdk.services.dynamodb.model.TableDescription;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

@Slf4j
public class CreateDynamoDbTable implements ApplicationListener<ApplicationReadyEvent> {
	private static final Logger log =LoggerFactory.getLogger(CreateDynamoDbTable.class);
		@Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
		DynamoDbTable<Department> departmentTable = event.getApplicationContext().getBean(DynamoDbTable.class);
		DynamoDbClient dynamoDbClient = event.getApplicationContext().getBean(DynamoDbClient.class);

		if (!tableExists(dynamoDbClient, Department.tableName())) {
			log.info("Attempting to create table {}", Department.tableName());
	//	log.info("Attempting to create table {}", Department.tableName());
		departmentTable.createTable();

		try(DynamoDbWaiter waiter=DynamoDbWaiter.builder().client(dynamoDbClient).build()) { 

		//DynamoDbWaiter is Autocloseable

		ResponseOrException<DescribeTableResponse> response = waiter.waitUntilTableExists(builder ->builder.tableName(Department.tableName()).build())
				//.waitUntilTableExists(builder -> builder.tableName(Department.tableName()).build())
		
		.matched();

		DescribeTableResponse tableDescription = response.response().orElseThrow(

		() -> new RuntimeException (Department.tableName()+" table was not created."));

		
		log.info("Table {} is created.", Department.tableName());

		}
		} else {

		log.info("Table {} already exists", Department.tableName());

		}

		}
		private boolean tableExists(DynamoDbClient dynamoDbClient, String tableName) {

			DescribeTableRequest request= DescribeTableRequest.builder().tableName(tableName).build();

			try {
			TableDescription tableInfo= dynamoDbClient.describeTable(request).table();
			if (tableInfo != null) {
			return true;

			}

			} catch (Exception e) {

			log.info("error while fetching table details ()", e.getMessage());

			}


			return false;

			}

}

