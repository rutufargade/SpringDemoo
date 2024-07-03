package com.example.spring.SpringDemoo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;


@Configuration
public class DynamoDbConfig {

@Bean
public DynamoDbClient dynamoDbClient() {
return DynamoDbClient.builder()
//.region(Region.US_EAST_1)
.build();
}

@Bean
public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder() .dynamoDbClient(dynamoDbClient()) .build();
}
@Bean
public DynamoDbTable<Department> departmentTable() {
return dynamoDbEnhancedClient()
.table(Department.tableName(), TableSchema.fromBean(Department.class));

}

}