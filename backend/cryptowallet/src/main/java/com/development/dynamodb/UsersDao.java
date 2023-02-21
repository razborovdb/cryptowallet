package com.development.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import com.development.dynamodb.models.Users;



import javax.inject.Inject;

public class UsersDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * CryptocurrencyDao.
     * @param dynamoDbMapper - DynamoDBMapper
     */
    @Inject
    public UsersDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * getProjects.
     * @return projectsList
     */
    public Users createUser(Users user) {

        try {
            dynamoDbMapper.save(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    public Users updateUser(Users user) {

        try {
            dynamoDbMapper.save(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    public Users getUser(String userName) {
        Users user = new Users();
        user.setEmail(userName);


        DynamoDBQueryExpression<Users> queryExpression = new DynamoDBQueryExpression<Users>()
                .withHashKeyValues(user);

        try {
            PaginatedQueryList<Users> usersList = dynamoDbMapper.query(Users.class, queryExpression);

            if (usersList.size() > 0) {
                return usersList.get(0);
            }
        } catch (Exception e) {

        }



        return null;

    }
}
