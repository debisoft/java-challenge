package jp.co.axa.apidemo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.Employee;

// Due to the lack of time, we will keep it simple.
// Cache all reads, and evict the whole cache after any changes.
@CacheConfig(cacheNames = "employee")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Cacheable
	Page<Employee> findAll(Pageable pageable);

	@CacheEvict(allEntries = true)
	<S extends Employee> S save(S entity);

	@Cacheable
	Optional<Employee> findById(Long id);

	@Cacheable
	boolean existsById(Long id);

	@Cacheable
	long count();

	@CacheEvict(allEntries = true)
	void deleteById(Long id);

	@CacheEvict(allEntries = true)
	void delete(Employee entity);

	@CacheEvict(allEntries = true)
	void deleteAll(Iterable<? extends Employee> entities);

	@CacheEvict(allEntries = true)
	void deleteAll();

	@Cacheable
	<S extends Employee> Optional<S> findOne(Example<S> example);

	@Cacheable
	<S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable);

	@Cacheable
	<S extends Employee> long count(Example<S> example);

	@Cacheable
	<S extends Employee> boolean exists(Example<S> example);

	@Cacheable
	List<Employee> findAll();

	@Cacheable
	List<Employee> findAll(Sort sort);

	@Cacheable
	List<Employee> findAllById(Iterable<Long> ids);

	@CacheEvict(allEntries = true)
	<S extends Employee> List<S> saveAll(Iterable<S> entities);

	@CacheEvict(allEntries = true)
	void flush();

	@CacheEvict(allEntries = true)
	<S extends Employee> S saveAndFlush(S entity);

	@Cacheable
	<S extends Employee> List<S> findAll(Example<S> example);

	@Cacheable
	<S extends Employee> List<S> findAll(Example<S> example, Sort sort);

}
