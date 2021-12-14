package com.restServicesSpring.restService.Employee.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.restServicesSpring.restService.Employee.exception.RecordNotFoundException;
import com.restServicesSpring.restService.Employee.model.EmployeeEntity;
import com.restServicesSpring.restService.Employee.repository.EmployeeRepository;
 
@Service
public class EmployeeService {

    // private final String FETCH_SQL_BY_ID = "select * from users where record_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeeEntity create(EmployeeEntity employee) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO TBL_EMPLOYEES (id, first_name,last_name,email) values(?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1,employee.getId());
                ps.setString(2, employee.getFirstName());
                ps.setString(3, employee.getLastName());
                ps.setString(4, employee.getEmail());
                return ps;
            }
        }, holder);

        return employee;
    }

    @Autowired
    private EmployeeRepository repository;
     
    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }
     
    public EmployeeEntity getEmployeeById(Integer id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException
    {
        repository = new EmployeeRepository() {
            @Override
            public Optional<EmployeeEntity> findById(Integer id) {
                return Optional.empty();
            }

            @Override
            public List<EmployeeEntity> findAll() {
                return null;
            }

            @Override
            public List<EmployeeEntity> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<EmployeeEntity> findAllById(Iterable<Integer> integers) {
                return null;
            }

            @Override
            public <S extends EmployeeEntity> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends EmployeeEntity> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends EmployeeEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<EmployeeEntity> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> integers) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public EmployeeEntity getOne(Integer integer) {
                return null;
            }

            @Override
            public EmployeeEntity getById(Integer integer) {
                return null;
            }

            @Override
            public <S extends EmployeeEntity> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends EmployeeEntity> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<EmployeeEntity> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends EmployeeEntity> S save(S entity) {
                return null;
            }

            @Override
            public boolean existsById(Integer integer) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Integer integer) {

            }

            @Override
            public void delete(EmployeeEntity entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> integers) {

            }

            @Override
            public void deleteAll(Iterable<? extends EmployeeEntity> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends EmployeeEntity> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends EmployeeEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends EmployeeEntity> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends EmployeeEntity> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends EmployeeEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
        Optional<EmployeeEntity> employee = repository.findById((int) entity.getId());
         
        if(employee.isPresent())
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setId((int) entity.getId());
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(new EmployeeEntity((int) entity.getId(), entity.getFirstName(), entity.getLastName(),entity.getEmail() ));
            return entity;
        }
    }

     
    public void deleteEmployeeById(Integer id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}